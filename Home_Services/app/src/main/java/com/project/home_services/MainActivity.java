package com.project.home_services;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView forgotpass;
    private Button loginbtn;
    private Button signupbtn;
    private Button googlebtn;

    private TextView TXTLemail;
    private TextView TXTLpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);   // forces to remove dark mode

        //----disable titlebar code
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        getWindow().setNavigationBarColor(getResources().getColor(R.color.ColorAccent));   // changes the navigation bar color

        setContentView(R.layout.activity_main);


        signupbtn = findViewById(R.id.BTNsignup);
        signupbtn.setOnClickListener(this);

        loginbtn = findViewById(R.id.BTNlogin);
        loginbtn.setOnClickListener(this);

        googlebtn = findViewById(R.id.BTNgoogle);
        googlebtn.setOnClickListener(this);

        forgotpass = findViewById(R.id.TVBTNforgot);
        forgotpass.setOnClickListener(this);


        TXTLemail = findViewById(R.id.TXTemailnum);
        TXTLpassword = findViewById(R.id.TXTpassword);
    }

    private boolean validateEmail() {
        String email = TXTLemail.getEditableText().toString().trim();
        if (email.isEmpty()) {
            TXTLemail.setError("Field can't be empty!");
            return true;
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                TXTLemail.setError("Please Enter Valid Email-Id");
                return true;
            }
            TXTLemail.setError(null);
            return false;
        }
    }

    private boolean validatePassword() {
        String password = TXTLpassword.getEditableText().toString().trim();
        if (password.length() < 6) {
            TXTLpassword.setError("Enter Complete Password!");
            return true;
        } else {
            TXTLpassword.setError(null);
            return false;
        }
    }

    @Override
    public void onClick(View v) {

            switch (v.getId()) {
                case R.id.BTNsignup:
                    startActivity(new Intent(this, Signup.class));
                    break;

                case R.id.BTNlogin:
                    if (validateEmail() | validatePassword()) {
                        return;
                    } else {
                        startActivity(new Intent(this, HomePage.class));
                    }
                    break;

                case R.id.BTNgoogle:
                    startActivity(new Intent(this, Signup.class));
                    break;

                case R.id.TVBTNforgot:
                    startActivity(new Intent(this, ForgotPassword.class));
                    break;
            }

    }
}