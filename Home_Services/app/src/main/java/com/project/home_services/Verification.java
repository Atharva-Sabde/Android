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

import pl.droidsonroids.gif.GifImageView;

public class Verification extends AppCompatActivity  {
    private Button sendOTP;
    private TextView shownumber;
    private String finalNumber;
    private String codebysystem;
    private  String codebyuser;
    private FirebaseAuth mAuth;




    private Intent Details;
    private Intent Signup;
    private GifImageView mail;
    private GifImageView verify;
    private Button verifyy;
    private Button verifynext;
    private TextView TXTVnumber;
    private PinView OTP;
    private ProgressBar progressbar;
    private ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.ColorAccent));
        pd = new ProgressDialog(this);
        pd.setCanceledOnTouchOutside(false);
        Details =  new Intent(this, Details.class);
        Signup =  new Intent(this, Signup.class);
        mail = findViewById(R.id.gifmail);
        verify = findViewById(R.id.gifverify);
        progressbar = findViewById(R.id.VerifyProgressbar);
        mAuth = FirebaseAuth.getInstance();

        TXTVnumber = findViewById(R.id.TXTVnumber);
        shownumber = findViewById(R.id.shownumber);
        OTP = findViewById(R.id.OTP);

        sendOTP = findViewById(R.id.BTNsendOTP);
        verifyy = findViewById(R.id.BTNverify);
        verifynext= findViewById(R.id.BTNverifiednext);

        sendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateNumber()){
                    return;
                }else{
                    sendOTP.setVisibility(View.INVISIBLE);
                    OTP.setVisibility(View.VISIBLE);
                    verifyy.setVisibility(View.VISIBLE);
                    finalNumber = TXTVnumber.getEditableText().toString().trim();
                    shownumber.setText("OTP sent to  "+finalNumber);
//                    sendVerification(finalNumber);
                    Toast.makeText(Verification.this, "OKay", Toast.LENGTH_SHORT).show();
                }
            }
        });

        verifyy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Verification.this, "Verifying...", Toast.LENGTH_SHORT).show();
                codebyuser = OTP.getEditableText().toString().trim();

//                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codebysystem, codebyuser);
//                mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull  Task<AuthResult> task) {
//                        if(task.isSuccessful()){
                            Toast.makeText(Verification.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                            mail.setVisibility(View.INVISIBLE);
                            OTP.setVisibility(View.INVISIBLE);
                            verifyy.setVisibility(View.INVISIBLE);
                            TXTVnumber.setVisibility(View.INVISIBLE);
                            shownumber.setVisibility(View.INVISIBLE);
                            verify.setVisibility(View.VISIBLE);
                            verifynext.setVisibility(View.VISIBLE);
//                            finishAffinity();
//                        }else{
//                            Toast.makeText(Verification.this, "FAILED", Toast.LENGTH_SHORT).show();
//                        }
                    }
//                });
//            }
        });

        verifynext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Details);
            }
        });

    }

    private void sendVerification(String phonenumber) {
        shownumber.setText("testing method  "+phonenumber);
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phonenumber)
                .setTimeout(60L ,TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull  PhoneAuthCredential phoneAuthCredential) {

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {

                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull  PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        codebysystem = s;
                    }
                }).build();

        PhoneAuthProvider.verifyPhoneNumber(options);

    }  // send verification method closed

    private boolean validateNumber() {
        String number = TXTVnumber.getEditableText().toString().trim();
        if (number.isEmpty()) {
            TXTVnumber.setError("Field can't be Empty!");
            return true;
        } else if (number.length() < 10) {
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
//         if(validateNumber()){ return; }else{
//            Toast.makeText(this, "Sending OTP...", Toast.LENGTH_SHORT).show();
//            System.out.println("TEssting...");
//            verifyreqq.setVisibility(View.INVISIBLE);
//            progressbar.setVisibility(View.VISIBLE);
////            sendVerificationCodeToUser(finalNumber);
//        }
//    }

//    public void onClick(View V) {
//        finalNumber = TXTVnumber.getEditableText().toString().trim();
////        if (validateNumber()) {
////            return;
////        } else {
//            switch (V.getId()) {
//                case R.id.BTNsendOTP:                        // AUTOMATIC VERIFICATION
//                    verifyreqq.setVisibility(View.INVISIBLE);
//                    progressbar.setVisibility(View.VISIBLE);
////                    Toast.makeText(this, "Sending OTP...", Toast.LENGTH_SHORT).show();
//
//                    pd.setTitle("verification code");
//                    pd.setMessage("Requesting OTP");
//                    pd.show();
////                    pd.dismiss();
//                    break;
//
//                case R.id.BTNverify:                           // MANUAL OTP VERIFICATION
//                    Toast.makeText(this, "Testing....", Toast.LENGTH_SHORT).show();
////                    if (validateOTP()) {
////                        return;
////                    }else{
//                    Toast.makeText(this, "Verifying...", Toast.LENGTH_SHORT).show();
////                    progressbar.setVisibility(View.VISIBLE);
//                    Details = new Intent(this, Details.class);
//                    startActivity(Details);
//                    break;
////                        String code = OTP.getText().toString().trim();
////                        if(!code.isEmpty()){
////                            verifyOTP(code , codebySystem);           // ----------- MANUAL VERIFICATION ------------
////                        }else{
//////                            Toast.makeText(this, "Empty OTP", Toast.LENGTH_SHORT).show();
////                            Toast.makeText(this, finalNumber, Toast.LENGTH_SHORT).show();
////                        }
////                    }
//
//                default:
//                    Toast.makeText(this, "__Default__", Toast.LENGTH_SHORT).show();
//                    break;
//            }               //switch close
////        }                   //else close
//        }                        //method close
}