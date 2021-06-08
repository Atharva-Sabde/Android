package com.project.home_services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getWindow().setNavigationBarColor(getResources().getColor(R.color.ColorAccent));
    }

    public void onClick(View v){
        Toast.makeText(this, "Registeration in progress...", Toast.LENGTH_SHORT).show();
//        Intent verification = new Intent(this, Verification.class);
//
//        startActivity(verification);
    }
}