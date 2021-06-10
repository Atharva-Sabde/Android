package com.project.home_services;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {
    private TextView TXTRemail;

    private Button TBTNrecoveryreq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        getWindow().setNavigationBarColor(getResources().getColor(R.color.ColorAccent));

        TXTRemail = findViewById(R.id.TXTRemail);
        TBTNrecoveryreq = findViewById(R.id.BTNrecoveryreq);
    }
    private boolean validateEmail() {
        String email =  TXTRemail.getEditableText().toString().trim();
        if(email.isEmpty()){
            TXTRemail.setError("Field can't be empty!");
            return true;
        }else{
            if(! Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                TXTRemail.setError("Please Enter Valid Email-Id");
                return true;
            }
            TXTRemail.setError(null);
            return false;
        }
    }

    public void onClick(View v){
        if(validateEmail()){
            return;
        }else{

        Toast.makeText(this, "Sending Verification Link...", Toast.LENGTH_SHORT).show();
        Intent newpassword = new Intent(this, MainActivity.class);
        startActivity(newpassword);
        }
    }
}