package com.example.exercise01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    TextView textView_login_1;
    EditText editText_ten;
    Button button_login_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView_login_1 = findViewById(R.id.textView_login_1);
        editText_ten = findViewById(R.id.editText_ten);
        button_login_2 = findViewById(R.id.button_login_2);

        Intent intent = getIntent();

        String email = intent.getStringExtra("email");

        textView_login_1.setText("Xin chao, " + email);

        button_login_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(editText_ten.getText().toString())){
                    Toast.makeText(MainActivity2.this, "vui long nhap ten", Toast.LENGTH_SHORT).show();
                }else{
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("name", editText_ten.getText().toString());
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
    }
}