package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demo.admin.MainActivityAdmin;
import com.example.demo.user.MainActivityHome;
import com.example.demo.user.MainActivitySignUp;
import com.example.demo.user.MainActivityVerify;

public class MainActivity extends AppCompatActivity {

    EditText editText_email, editText_password;
    CheckBox checkBox_showPassword;
    Button button_signIn, button_signUp, button_recoverPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*---find view by id---*/
        editText_email = findViewById(R.id.editText_email);
        editText_password = findViewById(R.id.editText_password);
        checkBox_showPassword = findViewById(R.id.checkBox_showPassword);
        button_signIn = findViewById(R.id.button_signIn);
        button_signUp = findViewById(R.id.button_signUp);
        button_recoverPassword = findViewById(R.id.button_recoverPassword);

        /*---hide and show password---*/
        HideAndShowPassword();

        /*---event button sign in---*/
        EventButtonSignIn();

        /*---event button sign up---*/
        EventButtonSignUp();

        /*---event button recover password---*/
        EventRecoverPassword();
    }

    ///*---method | hide and show password---*///
    private void HideAndShowPassword() {
        checkBox_showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    editText_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    editText_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    ///*---method | event validation input---*///
    private void EventButtonSignIn() {
        button_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_value = editText_email.getText().toString();
                String password_value = editText_password.getText().toString();

                if(TextUtils.isEmpty(email_value)) {
                    editText_email.requestFocus();
                    editText_email.setError("Please enter your email address !");
                } else if(!Patterns.EMAIL_ADDRESS.matcher(email_value).matches()) {
                    editText_email.requestFocus();
                    editText_email.setError("Please enter a valid email address !");
                } else if(TextUtils.isEmpty(password_value)) {
                    editText_password.requestFocus();
                    editText_password.setError("Please enter your password !");
                } else if(email_value.equals("admin@gmail.com") && password_value.equals("Admin123")) {
                    editText_email.setError(null);
                    editText_password.setError(null);

                    Intent intent = new Intent(MainActivity.this, MainActivityAdmin.class);
                    startActivity(intent);
                } else {
                    editText_email.setError(null);
                    editText_password.setError(null);
                    Toast.makeText(MainActivity.this, "SIGN IN FAILED !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    ///*---method | event button sign up---*///
    private void EventButtonSignUp() {
        button_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivitySignUp.class);
                startActivity(intent);
            }
        });
    }

    ///*---method | event button recover password---*///
    private void EventRecoverPassword() {
        button_recoverPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_value = editText_email.getText().toString();

                if(TextUtils.isEmpty(email_value)) {
                    editText_email.requestFocus();
                    editText_email.setError("Please enter your email address !");
                } else if(!Patterns.EMAIL_ADDRESS.matcher(email_value).matches()) {
                    editText_email.requestFocus();
                    editText_email.setError("Please enter a valid email address !");
                } else {
                    editText_email.setError(null);

                    Intent intent = new Intent(MainActivity.this, MainActivityVerify.class);
                    startActivity(intent);
                }
            }
        });
    }
}
