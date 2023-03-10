package com.example.exercise02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText_content;
    Button button_clickMe;
    TextView textView_display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_content = findViewById(R.id.editText_content);
        button_clickMe = findViewById(R.id.button_clickMe);
        textView_display = findViewById(R.id.textView_display);

        button_clickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText_content.getText().toString();
                if(TextUtils.isEmpty(text)){
                    Toast.makeText(MainActivity.this, "vui long nhap thong tin", Toast.LENGTH_SHORT).show();;
                } else {
                    textView_display.setText(text);
                    editText_content.setText("");
                }
            }
        });

        editText_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = editText_content.getText().toString();
                if(text.toLowerCase().equals("on")){
                    button_clickMe.setEnabled(true);
                } else if(text.toLowerCase().equals("off")) {
                    button_clickMe.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}