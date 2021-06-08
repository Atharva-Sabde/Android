package com.project.home_services;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        getWindow().setNavigationBarColor(getResources().getColor(R.color.ColorAccent));
    }

    public void onClick(View v){
        Toast.makeText(this, "Sending Verification Link...", Toast.LENGTH_SHORT).show();
        Intent newpassword = new Intent(this, MainActivity.class);

        startActivity(newpassword);
    }
}