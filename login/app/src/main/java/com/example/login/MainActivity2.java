package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void openActivitydetail(View v){
        Toast.makeText(this, "switching to Details...", Toast.LENGTH_SHORT).show();


        Intent details = new Intent(this , MainActivity3.class);

        startActivity(details);
    }
}

