package com.example.exercise01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText_textColor, editText_backgroundColor;
    Button button_save;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*---find id---*/
        textView = findViewById(R.id.textView);
        editText_textColor = findViewById(R.id.editText_textColor);
        editText_backgroundColor = findViewById(R.id.editText_backgroundColor);
        button_save = findViewById(R.id.button_save);

        /*---shared preferences---*/
        SharedPreferences shared = getSharedPreferences("my_share", MODE_PRIVATE);

        /*---add parameter to shared---*/
        String textColor = shared.getString("textColor", "#FFFFFF");
        String backgroundColor = shared.getString("backgroundColor", "#2222FF");
        count = shared.getInt("count", 1);

        /*---setting from shared---*/
        textView.setTextColor(Color.parseColor(textColor));
        textView.setBackgroundColor(Color.parseColor(backgroundColor));
        textView.setText("" + count);

        editText_textColor.setText(textColor);
        editText_backgroundColor.setText(backgroundColor);

        /*---button save---*/
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*---get text in editText---*/
                String textColor = editText_textColor.getText().toString();
                String backgroundColor = editText_backgroundColor.getText().toString();

                /*---setting from editText---*/
                textView.setTextColor(Color.parseColor(textColor));
                textView.setBackgroundColor(Color.parseColor(backgroundColor));

                /*---edit shared preferences---*/
                SharedPreferences.Editor editor = shared.edit();

                editor.putString("textColor", textColor);
                editor.putString("backgroundColor", backgroundColor);

                editor.apply();
            }
        });
    }

    /*---setting count---*/
    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity", "onStop");
        count++;

        /*---edit shared preferences---*/
        SharedPreferences shared = getSharedPreferences("my_share", MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();

        editor.putInt("count", count);
        editor.apply();


    }
}