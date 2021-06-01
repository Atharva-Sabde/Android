package com.example.temperature;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText editText;
    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.submit);
        editText =findViewById(R.id.input);
        textview = findViewById(R.id.output);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                if(!s.isEmpty()){
                double celcius = Double.parseDouble(s);
                double fahrenheit = (celcius* 9/5) + 32 ;
                textview.setText("Temperature in *f is : "+ fahrenheit);
                }
                else{
                    textview.setText("Please enter the Number");
                }
            }
        });







    }
}