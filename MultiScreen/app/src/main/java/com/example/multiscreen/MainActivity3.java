package com.example.multiscreen;

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

//        startActivity(intent);

    }

    public void openActivity(View v){
        Toast.makeText(this, "Switching to 24d Screen", Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "dummy", Toast.LENGTH_SHORT);

        Intent intent = new Intent(this , MainActivity4.class);


        startActivity(intent);

    }


}