package com.project.home_services;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Verification extends AppCompatActivity implements View.OnClickListener{
    private Intent passwordsetup;
    private Button verifyreqq;
    private Button verifyy;
    private TextView TXTVnumber;
    private  String finalNumber;
    private PinView OTP;
    private ProgressBar progressbar;
    private ProgressDialog pd;

    private FirebaseAuth mAuth;// ...


    private String codebySystem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        getWindow().setNavigationBarColor(getResources().getColor(R.color.ColorAccent));

        pd = new ProgressDialog(this);
        pd.setTitle("Please Wait...");
        pd.setCanceledOnTouchOutside(false);

        verifyreqq = findViewById(R.id.BTNsendOTP);
        verifyreqq.setOnClickListener(this);

        verifyy = findViewById(R.id.BTNverify);
        verifyy.setOnClickListener(this);

        TXTVnumber = findViewById(R.id.TXTVnumber);
        OTP = findViewById(R.id.OTP);
        progressbar = findViewById(R.id.VerifyProgressbar);

        mAuth = FirebaseAuth.getInstance();
        passwordsetup = new Intent(this, HomePage.class);
    }

    private boolean validateNumber() {
        String number = TXTVnumber.getEditableText().toString().trim();
        if (number.isEmpty()) {
            TXTVnumber.setError("Field can't be Empty!");
            return true;
        } else if (number.length()<10) {
            TXTVnumber.setError("Enter Valid Number!");
            return true;
        } else {
            TXTVnumber.setError(null);
            return false;
        }
    }

    private boolean validateOTP() {
        String number = OTP.getEditableText().toString().trim();
        if (number.isEmpty()) {
            OTP.setError("Field can't be Empty!");
            return true;
        } else if (number.length() != 6) {
            OTP.setError("Enter Valid Number!");
            return true;
        } else {
            OTP.setError(null);
            return false;
        }
    }
//    public void sendOTP(View v1){
//        if(validateNumber()){ return; }else{
//            Toast.makeText(this, "Sending OTP...", Toast.LENGTH_SHORT).show();
//            System.out.println("TEssting...");
//            verifyreqq.setVisibility(View.INVISIBLE);
//            progressbar.setVisibility(View.VISIBLE);
////            sendVerificationCodeToUser(finalNumber);
//        }
//    }

    public void onClick(View V) {
        finalNumber = TXTVnumber.getEditableText().toString().trim();
//        if (validateNumber()) {
//            return;
//        } else {
            switch (V.getId()) {
                case R.id.BTNsendOTP:                        // AUTOMATIC VERIFICATION
                    Toast.makeText(this, "Sending OTP...", Toast.LENGTH_SHORT).show();
                    System.out.println("TEssting...");
                    verifyreqq.setVisibility(View.INVISIBLE);
                    progressbar.setVisibility(View.VISIBLE);
                    sendVerificationCodeToUser(finalNumber);
                    break;

                case R.id.BTNverify:                           // MANUAL OTP VERIFICATION
                    Toast.makeText(this, "Testing....", Toast.LENGTH_SHORT).show();
//                    if (validateOTP()) {
//                        return;
//                    }else{
                        Toast.makeText(this, "Verifying...", Toast.LENGTH_SHORT).show();
                        progressbar.setVisibility(View.VISIBLE);
                        String code = OTP.getText().toString().trim();
                        if(!code.isEmpty()){
                            verifyOTP(code , codebySystem);           // ----------- MANUAL VERIFICATION ------------
                        }else{
//                            Toast.makeText(this, "Empty OTP", Toast.LENGTH_SHORT).show();
                            Toast.makeText(this, finalNumber, Toast.LENGTH_SHORT).show();
                        }
//                    }
                    break;

//                default:
//                    Toast.makeText(this, "____", Toast.LENGTH_SHORT).show();
//                    break;
            }               //switch close
//        }                   //else close
    }                        //method close

    private void sendVerificationCodeToUser(String phoneNumber) {
//        finalNumber = "+918237571769";
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity((Activity) TaskExecutors.MAIN_THREAD)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        Toast.makeText(this, "Reached method 1", Toast.LENGTH_SHORT).show();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }


    //     =========================   PHONE AUTH PROVIDER ==============================
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    codebySystem = s;
                }
                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                    String code = phoneAuthCredential.getSmsCode();
                    if(code!=null){
                        OTP.setText(code);
                        Toast.makeText(Verification.this, "Verifying...", Toast.LENGTH_SHORT).show();
                        progressbar.setVisibility(View.VISIBLE);
                        verifyOTP(code , codebySystem);                             // ----------- AUTOMATIC VERIFICATION --------
                    }
                }
                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    Toast.makeText(Verification.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            };      // PHONE AUTH PROVIDE COMPLETE

    private void verifyOTP(String xcode , String xcodebySystem) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(xcodebySystem, xcode);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithCredential:success");
//                            FirebaseUser user = task.getResult().getUser();
//                            // Update UI
                        Toast.makeText(Verification.this, "Verification SUCCESSFULLLL...", Toast.LENGTH_SHORT).show();
                        startActivity(passwordsetup);

                    } else {
//                            // Sign in failed, display a message and update the UI
                            pd.setTitle("SignUP failed...");
//                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
                            Toast.makeText(Verification.this, "SignupFailed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}   // class close