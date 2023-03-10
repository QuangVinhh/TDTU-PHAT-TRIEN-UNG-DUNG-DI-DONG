package com.example.exercise05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    RadioButton radioButton_android;
    RadioButton radioButton_ios;
    RadioButton radioButton_windows;
    RadioButton radioButton_rim;

    Button button_click_radio;
    Button button_checkboxes;

    TextView textView_radio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        radioButton_android = findViewById(R.id.radioButton_android);
        radioButton_ios = findViewById(R.id.radioButton_ios);
        radioButton_windows = findViewById(R.id.radioButton_windows);
        radioButton_rim = findViewById(R.id.radioButton_rim);

        button_click_radio = findViewById(R.id.button_click_radio);
        button_checkboxes = findViewById(R.id.button_checkboxes);

        textView_radio = findViewById(R.id.textView_radio);

        button_checkboxes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivityForResult(intent, 1234);
            }
        });

        button_click_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(radioButton_android.isChecked()){
                    textView_radio.setText("Android");
                } else if (radioButton_ios.isChecked()) {
                    textView_radio.setText("iOS");
                } else if (radioButton_windows.isChecked()) {
                    textView_radio.setText("Windows");
                } else if (radioButton_rim.isChecked()) {
                    textView_radio.setText("RIM");
                }
            }
        });
    }
}