package com.example.exercise05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CheckBox checkBox_android;
    CheckBox checkBox_ios;
    CheckBox checkBox_windows;
    CheckBox checkBox_rim;

    Button button_click;
    Button button_radio;

    TextView textView_android;
    TextView textView_ios;
    TextView textView_windows;
    TextView textView_rim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBox_android = findViewById(R.id.checkBox_android);
        checkBox_ios = findViewById(R.id.checkBox_ios);
        checkBox_windows = findViewById(R.id.checkBox_windows);
        checkBox_rim = findViewById(R.id.checkBox_rim);

        button_click = findViewById(R.id.button_click_checkboxes);
        button_radio = findViewById(R.id.button_radio);

        textView_android = findViewById(R.id.textView_android);
        textView_ios = findViewById(R.id.textView_ios);
        textView_windows = findViewById(R.id.textView_windows);
        textView_rim = findViewById(R.id.textView_rim);

        button_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox_android.isChecked()){
                    textView_android.setText("true");
                } else{
                    textView_android.setText("false");
                }

                if(checkBox_ios.isChecked()){
                    textView_ios.setText("true");
                } else{
                    textView_ios.setText("false");
                }

                if(checkBox_windows.isChecked()){
                    textView_windows.setText("true");
                } else{
                    textView_windows.setText("false");
                }

                if(checkBox_rim.isChecked()){
                    textView_rim.setText("true");
                } else{
                    textView_rim.setText("false");
                }
            }
        });

        button_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivityForResult(intent, 1234);
            }
        });
    }
}