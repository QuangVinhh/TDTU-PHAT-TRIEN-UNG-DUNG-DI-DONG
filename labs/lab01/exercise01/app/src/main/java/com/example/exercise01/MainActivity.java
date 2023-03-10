package com.example.exercise01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button_toast;
    Button button_count;
    TextView textView_content;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_toast = findViewById(R.id.button_toast);
        button_count = findViewById(R.id.button_count);
        textView_content = findViewById(R.id.textView_content);

        button_toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "You are clicking this TOAST", Toast.LENGTH_SHORT).show();
            }
        });

        button_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                textView_content.setText(count + "");
            }
        });
    }
}