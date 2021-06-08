package com.project.home_services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    private TextView TXTname;
    private TextView TXTemail;
    private TextView TXTnumber;
    private TextView TXTage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //----disable titlebar code
//        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
//        getSupportActionBar().hide(); //hide the title bar
//
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        getWindow().setNavigationBarColor(getResources().getColor(R.color.ColorAccent));
        setContentView(R.layout.activity_signup);

        TXTname= findViewById(R.id.TXTname);
        TXTemail = findViewById(R.id.TXTemail);
        TXTnumber = findViewById(R.id.TXTnumber);
        TXTage = findViewById(R.id.TXTage);
    }
    private boolean validateEmail() {
        String email =  TXTemail.getEditableText().toString().trim();
        if(email.isEmpty()){
            TXTemail.setError("Field can't be empty!");
            return true;
        }else{
            if(! Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                TXTemail.setError("Please Enter Valid Email-Id");
                return true;
            }
            TXTemail.setError(null);
            return false;
        }
    }

    private boolean validateName(){
        String name = TXTname.getEditableText().toString().trim();
        if(name.isEmpty()){
            TXTname.setError("Fuck you bitch!");
            return true;
        }else if (name.length()<=15) {
            TXTname.setError("Too short , that's what she said...");
            return true;
        } else{
                TXTname.setError(null);
                return false;
            }

    }
    private boolean validateNumber(){
        String number = TXTnumber.getEditableText().toString().trim();
        if(number.isEmpty()){
            TXTnumber.setError("Field can't be Empty!");
            return true;
        }else if(number.length()!=10){
            TXTnumber.setError("Enter Valid Number!");
            return true;
        }else{
            TXTnumber.setError(null);
            return false;
        }
    }

    private boolean validateAge(){
        String age = TXTage.getEditableText().toString().trim();
        if(age.isEmpty()){
            TXTage.setError("Field can't be Empty!");
            return true;
        }else if(age.length()!=2){
            TXTage.setError("Yo too young , suck some titties...");
            return true;
        }else{
            TXTage.setError(null);
            return false;
        }
    }

    public void onClick(View v){
        if(validateEmail() | validateName() | validateNumber() | validateAge()){
            return;
        }
        else{

        Toast.makeText(this, "Switching to Verification", Toast.LENGTH_SHORT).show();
        Intent verification = new Intent(this, Verification.class);

        startActivity(verification);
        }
    }
}