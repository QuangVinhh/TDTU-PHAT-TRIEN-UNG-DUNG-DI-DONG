package com.example.demo.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.demo.R;

public class MainActivitySignUp extends AppCompatActivity {

    EditText editText_fullName, editText_email, editText_phoneNumber;
    CheckBox checkBox_agree;
    Button button_signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sign_up);

        /*---find view by id---*/
        editText_fullName = findViewById(R.id.editText_fullName);
        editText_email = findViewById(R.id.editText_email);
        editText_phoneNumber = findViewById(R.id.editText_phoneNumber);
        checkBox_agree = findViewById(R.id.checkBox_agree);
        button_signUp = findViewById(R.id.button_signUp);

        /*---event button sign up---*/
        EventButtonSignUp();

    }

    ///*---method | event button sign up---*///
    private void EventButtonSignUp() {
        button_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_value = editText_fullName.getText().toString();
                String email_value = editText_email.getText().toString();
                String phone_value = editText_phoneNumber.getText().toString();

                if(TextUtils.isEmpty(name_value)) {
                    editText_fullName.requestFocus();
                    editText_fullName.setError("Please enter your full name !");
                } else if(TextUtils.isEmpty(email_value)) {
                    editText_email.requestFocus();
                    editText_email.setError("Please enter your email address !");
                } else if(!Patterns.EMAIL_ADDRESS.matcher(email_value).matches()) {
                    editText_email.requestFocus();
                    editText_email.setError("Please enter a valid email address !");
                } else if(TextUtils.isEmpty(phone_value)) {
                    editText_phoneNumber.requestFocus();
                    editText_phoneNumber.setError("Please enter your phone number !");
                } else if(!Patterns.PHONE.matcher(phone_value).matches()) {
                    editText_phoneNumber.requestFocus();
                    editText_phoneNumber.setError("Please enter a valid phone number !");
                } else if(checkBox_agree.isChecked() == false) {
                    checkBox_agree.requestFocus();
                    checkBox_agree.setError("Please confirm our conditions !");
                } else {
                    editText_fullName.setError(null);
                    editText_email.setError(null);
                    editText_phoneNumber.setError(null);
                    checkBox_agree.setError(null);

                    Intent intent = new Intent(MainActivitySignUp.this, MainActivityVerify.class);
                    startActivity(intent);
                }
            }
        });
    }
}