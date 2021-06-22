package com.project.home_services;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;



public class PasswordSetup extends AppCompatActivity {


    private static final Pattern PASSWORD_PATTERN =  Pattern.compile("^" +
            //"(?=.*[0-9])" +         //at least 1 digit
            //"(?=.*[a-z])" +         //at least 1 lower case letter
            //"(?=.*[A-Z])" +         //at least 1 upper case letter
            "(?=.*[a-zA-Z])" +      //any letter
//            "(?=.*[@#$%^&+=])" +    //at least 1 special character
            "(?=\\S+$)" +           //no white spaces
            ".{6,}" +               //at least  x characters
            "$");

    private EditText email;
    private TextInputEditText password;
    private TextInputEditText confirmpassword;
    private ProgressDialog pd;
    private Button register;
    private ProgressBar prgrsbr;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_setup);
        pd = new ProgressDialog(this);
        pd.setCanceledOnTouchOutside(true);

        email = findViewById(R.id.TXTUserEmail);
        password= findViewById(R.id.TXTUserPassword);
        confirmpassword = findViewById(R.id.TXTUserConfirm);

        register = findViewById(R.id.BTNregister);
//        register.setOnClickListener(this);

        prgrsbr = findViewById(R.id.RegisterProgress);
        mAuth = FirebaseAuth.getInstance();
    }
    public void onClick(View v){
        if(validatePassword() | validateConfirm()){
            return ;
        }else{
        Toast.makeText(this, "Registeration in progress...", Toast.LENGTH_SHORT).show();
//        prgrsbr.setVisibility(View.VISIBLE);

        Intent mainpage  = new Intent(this, MainActivity.class);
        pd.setTitle("Registeration");
        pd.setMessage("You have been successfully registered , login using your email and password to continue...");
        startActivity(mainpage);
        }
    }

//    mAuth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//        @Override
//        public void onComplete(@NonNull Task<AuthResult> task) {
//            if (task.isSuccessful()) {
//                // Sign in success, update UI with the signed-in user's information
//                Log.d(TAG, "createUserWithEmail:success");
//                FirebaseUser user = mAuth.getCurrentUser();
//                updateUI(user);
//            } else {
//                // If sign in fails, display a message to the user.
//                Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
//                        Toast.LENGTH_SHORT).show();
//                updateUI(null);
//            }
//        }
//    });

    private boolean validatePassword(){
        String passwrd =password.getEditableText().toString().trim();
        String confirm = confirmpassword.getEditableText().toString().trim();

        if (passwrd.isEmpty()) {
            password.setError("Field can't be empty");
            return true;
        } else if (!PASSWORD_PATTERN.matcher(passwrd).matches()) {
            password.setError("Password too weak");
            return true;
        } else {
            password.setError(null);
           return false;
        }
    }

    private boolean validateConfirm(){
        String passwrd =password.getEditableText().toString().trim();
        String confirm = confirmpassword.getEditableText().toString().trim();
        if(passwrd.equals(confirm)){
            confirmpassword.setError(null);
            return false;
        }else{
            confirmpassword.setError("Passwords do not Match!");
            return true;
        }
    }
}
