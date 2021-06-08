package com.project.home_services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

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

    }

    public void onClick(View v){
        Toast.makeText(this, "Switching to Verification", Toast.LENGTH_SHORT).show();
        Intent verification = new Intent(this, Verification.class);

        startActivity(verification);
    }
}