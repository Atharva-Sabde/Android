package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView  forgotpass ;
    private Button loginbtn ;
    private Button signupbtn;
    private Button googlebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);   // forces to remove dark mode

        //----disable titlebar code
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        // -----disable titlebar code end

        setContentView(R.layout.activity_main);

        signupbtn = findViewById(R.id.signupbtn);
        signupbtn.setOnClickListener(this);

        loginbtn = findViewById(R.id.loginbtn);
        loginbtn.setOnClickListener(this);

        googlebtn = findViewById(R.id.googlebtn);
        googlebtn.setOnClickListener(this);

        forgotpass = findViewById(R.id.googlebtn);
        forgotpass.setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch( v.getId()){
            case R.id.signupbtn:
                startActivity(new Intent(this ,MainActivity3.class));
                break;

            case R.id.loginbtn:
                startActivity(new Intent(this, Landing_Page.class));
                break;

            case R.id.googlebtn:
                startActivity(new Intent(this ,MainActivity2.class ));
                break;

            case R.id.forgotbtn:
                startActivity(new Intent(this, Forgot_Password.class));
                break;
        }
    }
}