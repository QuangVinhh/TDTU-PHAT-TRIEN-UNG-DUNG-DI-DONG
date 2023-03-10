package com.example.exercise02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textView_followers;
    ToggleButton button_follow;

    Random number = new Random();
    int number_followers = number.nextInt(10000 - 100) + 100;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_followers = findViewById(R.id.textView_followers);
        button_follow = findViewById(R.id.toggleButton_follow);

        textView_followers.setText(number_followers + "");

        button_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(button_follow.isChecked()){
                    number_followers++;
                    textView_followers.setText(number_followers + "");
                } else {
                    number_followers--;
                    textView_followers.setText(number_followers + "");
                }
            }
        });
    }
}