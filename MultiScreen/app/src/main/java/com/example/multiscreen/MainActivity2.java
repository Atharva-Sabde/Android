package com.example.multiscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView = findViewById(R.id.textView2);
        Intent intent = getIntent();

        String name = intent.getStringExtra(MainActivity.EXTRA_NAME);
        textView.setText("Your Entered : "+ name);

//        Intent intent1 = new Intent(Packagecontext : this , )
//        Intent intent1 = new Intent(this , MainActivity3.class);

//        Intent intent2 = new Intent(packageContext: this , MainActivity3.class)

    }
}