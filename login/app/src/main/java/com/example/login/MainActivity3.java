package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    public void openActivityVerification(View v){
        Toast.makeText(this, "switching to verification...", Toast.LENGTH_SHORT).show();
        Intent verification = new Intent(this , MainActivity2.class);

        startActivity(verification);

    }
}