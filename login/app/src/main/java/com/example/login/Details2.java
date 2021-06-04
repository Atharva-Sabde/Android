package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Details2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details2);
    }

    public void openActivityHome(View v) {
        Toast.makeText(this, "switching to home...", Toast.LENGTH_SHORT).show();
        Intent home = new Intent(this, MainActivity.class);

        startActivity(home);

    }
}