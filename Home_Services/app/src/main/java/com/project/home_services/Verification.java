package com.project.home_services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Verification extends AppCompatActivity implements View.OnClickListener {

    private Button  verifyreqq;
    private Button verifyy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        getWindow().setNavigationBarColor(getResources().getColor(R.color.ColorAccent));

        verifyreqq = findViewById(R.id.BTNverifyreq);
        verifyreqq.setOnClickListener(this);

        verifyy = findViewById(R.id.BTNverify);
        verifyy.setOnClickListener(this);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.BTNverifyreq:
                Toast.makeText(this, "Sending OTP...", Toast.LENGTH_SHORT).show();
                break;

            case R.id.BTNverify:
                Toast.makeText(this, "Verifying...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, Details.class));


                break;

        }

//        Intent Details = new Intent(this, Details.class);
//
//        startActivity(Details);
    }

}