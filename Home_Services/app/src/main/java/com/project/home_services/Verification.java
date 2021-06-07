package com.project.home_services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Verification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
    }
    public void onClick(View v){
        Toast.makeText(this, "Switching to Details", Toast.LENGTH_SHORT).show();
        Intent Details = new Intent(this, Details.class);

        startActivity(Details);
    }

    public void onClickVerification(View v){
        Toast.makeText(this, "Switching to Details", Toast.LENGTH_SHORT).show();
        Intent Details = new Intent(this, Details.class);

        startActivity(Details);
    }

}