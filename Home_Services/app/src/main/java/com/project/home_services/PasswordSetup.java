package com.project.home_services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

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

    private Button register;
    private ProgressBar prgrsbr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_setup);

        email = findViewById(R.id.TXTUserEmail);
        password= findViewById(R.id.TXTUserPassword);
        confirmpassword = findViewById(R.id.TXTUserConfirm);

        register = findViewById(R.id.BTNregister);
//        register.setOnClickListener(this);

        prgrsbr = findViewById(R.id.RegisterProgress);

    }

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

    public void onClick(View v){
        if(validatePassword() | validateConfirm()){
            return ;
        }else{
        Toast.makeText(this, "Registeration in progress...", Toast.LENGTH_SHORT).show();
        prgrsbr.setVisibility(View.VISIBLE);

        Intent passwordsetup = new Intent(this, MainActivity.class);

        startActivity(passwordsetup);

        }


    }
}