package com.example.exercise01;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Profile profile;
    TextView textView_login;
    EditText editText_email;
    Button button_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_login = findViewById(R.id.textView_login);
        editText_email = findViewById(R.id.editText_email);
        button_login = findViewById(R.id.button_login);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editText_email.getText().toString();

                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(MainActivity.this, "vui long nhap email", Toast.LENGTH_SHORT).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(MainActivity.this, "email khong hop le", Toast.LENGTH_SHORT).show();
                } else{
                    profile = new Profile(email, "");
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("email", profile.getEmail());
                    startActivityForResult(intent, 1234);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String name = data.getStringExtra("name");
        editText_email.setText(name);
        textView_login.setText("Hen gap lai");
        button_login.setEnabled(false);
    }
}