package com.example.demo.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demo.MainActivity;
import com.example.demo.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivityCreateNewPassword extends AppCompatActivity {

    EditText editText_newPassword, editText_confirmPassword;
    CheckBox checkBox_showPassword;
    Button button_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_create_new_password);

        /*---find view by id---*/
        editText_newPassword = findViewById(R.id.editText_newPassword);
        editText_confirmPassword = findViewById(R.id.editText_confirmPassword);
        checkBox_showPassword = findViewById(R.id.checkBox_showPassword);
        button_submit = findViewById(R.id.button_submit);

        /*---hide and show password---*/
        HideAndShowPassword();

        /*---event button submit---*/
        EventButtonSubmit();
    }

    ///*---method | hide and show password---*///
    private void HideAndShowPassword() {
        checkBox_showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    editText_newPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    editText_confirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    editText_newPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    editText_confirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    ///*---method | check is valid password---*///
    private boolean CheckIsValidPassword(String password) {
        Pattern pattern;
        Matcher matcher;

        String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    ///*---method | hide and show password---*///
    private void EventButtonSubmit() {
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String new_password_value = editText_newPassword.getText().toString();
                String confirm_password_value = editText_confirmPassword.getText().toString();

                if(TextUtils.isEmpty(new_password_value)) {
                    editText_newPassword.requestFocus();
                    editText_newPassword.setError("Please enter your new password ! \nYour password much have : \n+ 8 or more characters \n+ Upper and lowercase letters \n+ At least one number \n+ No white spaces");
                } else if(!CheckIsValidPassword(new_password_value)) {
                    editText_newPassword.requestFocus();
                    editText_newPassword.setError("Your password much have : \n+ 8 or more characters \n+ Upper and lowercase letters \n+ At least one number \n+ No white spaces");
                } else if(TextUtils.isEmpty(confirm_password_value)) {
                    editText_confirmPassword.requestFocus();
                    editText_confirmPassword.setError("Please confirm your new password !");
                } else if(confirm_password_value.equals(new_password_value)) {
                    editText_newPassword.setError(null);
                    editText_confirmPassword.setError(null);

                    Intent intent = new Intent(MainActivityCreateNewPassword.this, MainActivity.class);
                    startActivity(intent);

                    Toast.makeText(MainActivityCreateNewPassword.this, "SUCCESSFULLY !!!", Toast.LENGTH_SHORT).show();
                } else {
                    editText_confirmPassword.requestFocus();
                    editText_confirmPassword.setError("Your confirm password is not correct !");
                }
            }
        });
    }
}