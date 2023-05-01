package com.example.exercise02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button_readInternal, button_readExternal, button_writeInternal, button_writeExternal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*---find id---*/
        editText = findViewById(R.id.editText);
        button_readInternal = findViewById(R.id.button_readInternal);
        button_readExternal = findViewById(R.id.button_readExternal);
        button_writeInternal = findViewById(R.id.button_writeInternal);
        button_writeExternal = findViewById(R.id.button_writeExternal);

        /*---shared preferences---*/
        SharedPreferences shared = getSharedPreferences("my_shared", MODE_PRIVATE);

        /*---add parameter to shared---*/
        String file = shared.getString("file", "");

        /*---button read internal---*/
        button_readInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(file);
            }
        });

        /*---button write internal---*/
        button_writeInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();

                /*---edit shared preferences---*/
                SharedPreferences.Editor editor = shared.edit();
                editor.putString("file", text);
                editor.apply();

                editText.setText("");

                Toast.makeText(MainActivity.this, "Writing Success !", Toast.LENGTH_SHORT).show();
            }
        });
    }
}