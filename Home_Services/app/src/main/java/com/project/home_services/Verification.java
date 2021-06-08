package com.project.home_services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;

public class Verification extends AppCompatActivity implements View.OnClickListener {

    private Button  verifyreqq;
    private Button verifyy;

    private TextView TXTVnumber;
    private PinView OTP;

    private ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        getWindow().setNavigationBarColor(getResources().getColor(R.color.ColorAccent));

        verifyreqq = findViewById(R.id.BTNverifyreq);
        verifyreqq.setOnClickListener(this);

        verifyy = findViewById(R.id.BTNverify);
        verifyy.setOnClickListener(this);

        TXTVnumber = findViewById(R.id.TXTVnumber);
        OTP = findViewById(R.id.OTP);

        progressbar = findViewById(R.id.VerifyProgressbar);
    }


    private boolean validateNumber(){
        String number = TXTVnumber.getEditableText().toString().trim();
        if(number.isEmpty()){
            TXTVnumber.setError("Field can't be Empty!");
            return true;
        }else if(number.length()!=10){
            TXTVnumber.setError("Enter Valid Number!");
            return true;
        }else{
            TXTVnumber.setError(null);
            return false;
        }
    }

    private boolean validateOTP(){
        String number = OTP.getEditableText().toString().trim();
        if(number.isEmpty()){
            OTP.setError("Field can't be Empty!");
            return true;
        }else if(number.length()!=10){
            OTP.setError("Enter Valid Number!");
            return true;
        }else{
            OTP.setError(null);
            return false;
        }
    }

    public void onClick(View v){
        if(validateNumber() | validateOTP()){
            return;
        }
        else{
            switch (v.getId()){
                case R.id.BTNverifyreq:
                    Toast.makeText(this, "Sending OTP...", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.BTNverify:
                    if(!validateOTP()){
                        Toast.makeText(this, "Verifying...", Toast.LENGTH_SHORT).show();
                        progressbar.setVisibility(View.VISIBLE);
                        startActivity(new Intent(this, Details.class));
                    }
                    break;

            }
        }


//        Intent Details = new Intent(this, Details.class);
//
//        startActivity(Details);
    }

}