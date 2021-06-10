package com.project.home_services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Details extends AppCompatActivity {

    private EditText address;
    private EditText city;
    private EditText pincode;

    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getWindow().setNavigationBarColor(getResources().getColor(R.color.ColorAccent));

        address = findViewById(R.id.TXTaddress);
        city = findViewById(R.id.TXTcity);
        pincode = findViewById(R.id.TXTpincode);

        next = findViewById(R.id.BTNDetailNext);

    }

    private boolean validateAddress(){
        String addr = address.getEditableText().toString();

        if(addr.isEmpty()){
            address.setError("Field can't be Empty!");
            return true;
        }else{
            address.setError(null);
            return false;
        }
    }

    private boolean validateCity(){
        String cty = city.getEditableText().toString();

        if(cty.isEmpty()){
            city.setError("Field can't be Empty!");
            return  true;
        }else {          //if(cty.isNumeric())
            city.setError(null);
            return false;
        }
    }

    private boolean validatePincode(){
        String pin = pincode.getEditableText().toString().trim();
        if(pin.isEmpty()){
            pincode.setError("Field can't be Empty!");
            return true;
        }else if(pin.length()!= 6){
            pincode.setError("Please enter Valid PINCODE!");
            return true;
        }else{
            pincode.setError(null);
            return false;
        }
    }

    public void onClick(View v){
        if(validateAddress() | validateCity() | validatePincode()){
            return;
        }

        Toast.makeText(this, "Registeration in progress...", Toast.LENGTH_SHORT).show();
        Intent passwordsetup = new Intent(this, PasswordSetup.class);
//
        startActivity(passwordsetup);
    }
}