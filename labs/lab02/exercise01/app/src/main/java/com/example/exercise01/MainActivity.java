package com.example.exercise01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText_username;
    EditText editText_password;
    CheckBox checkBox_signed;
    Button button_reset;
    Button button_sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_username = findViewById(R.id.editText_username);
        editText_password = findViewById(R.id.editText_password);
        checkBox_signed = findViewById(R.id.checkBox_signed);
        button_reset = findViewById(R.id.button_reset);
        button_sign = findViewById(R.id.button_sign);

        button_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textUsername = editText_username.getText().toString();
                String textPassword = editText_password.getText().toString();

                if(TextUtils.isEmpty(textUsername) || TextUtils.isEmpty(textPassword)) {
                    Toast.makeText(MainActivity.this, "vui long nhap username hoac password", Toast.LENGTH_SHORT).show();
                } else {
                    if(textPassword.length() < 6 || textPassword.toLowerCase().equals(textPassword) || textPassword.toUpperCase().equals(textPassword)){
                        Toast.makeText(MainActivity.this, "mat khau khong dung yeu cau", Toast.LENGTH_SHORT).show();
                    } else if(textUsername.equals("admin") && textPassword.equals("Admin1234")){
                        Toast.makeText(MainActivity.this, "dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "dang nhap that bai", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textUsername = editText_username.getText().toString();

                if(TextUtils.isEmpty(textUsername)){
                    Toast.makeText(MainActivity.this, "vui long nhap username", Toast.LENGTH_SHORT).show();
                } else if(textUsername.equals("admin")){
                    Toast.makeText(MainActivity.this, "reset mat khau thanh cong", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "reset mat khau that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}