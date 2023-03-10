package com.example.exercise04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editText_us;
    EditText editText_euro;
    EditText editText_vnd;
    Button button_clear;
    Button button_convert;

    double us, euro, vnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_us = findViewById(R.id.editTextNumber_us);
        editText_euro = findViewById(R.id.editTextNumber_euro);
        editText_vnd = findViewById(R.id.editTextNumber_vnd);
        button_clear = findViewById(R.id.button_clear);
        button_convert = findViewById(R.id.button_convert);

        editText_euro.setEnabled(false);
        editText_vnd.setEnabled(false);

        button_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usText = editText_us.getText().toString();
                us = Integer.parseInt(usText);

                vnd = us * 23.640;
                euro = us * 0.94 ;

                editText_euro.setText(String.format("%.2f", euro));
                editText_vnd.setText(String.format("%.3f", vnd));
            }
        });

        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText_euro.getText().clear();
                editText_vnd.getText().clear();
            }
        });
    }
}