package com.example.mtvi_ver2.main;

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

import com.example.mtvi_ver2.R;
import com.example.mtvi_ver2.admin.MainActivityAdmin;
import com.example.mtvi_ver2.database.data.DataAccounts;
import com.example.mtvi_ver2.database.sqlite.AccountsDAO;
import com.example.mtvi_ver2.user.MainActivityUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<DataAccounts> dataAccounts = new ArrayList<>();
    DataAccounts accounts;
    EditText main_login_email, main_login_password;
    CheckBox main_login_showPassword;
    Button main_login_button_signIn, main_login_button_signUp, main_login_button_recover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*---find view by id---*/
        main_login_email = findViewById(R.id.main_login_email);
        main_login_password = findViewById(R.id.main_login_password);
        main_login_showPassword = findViewById(R.id.main_login_showPassword);
        main_login_button_signIn = findViewById(R.id.main_login_button_signIn);
        main_login_button_signUp = findViewById(R.id.main_login_button_signUp);
        main_login_button_recover = findViewById(R.id.main_login_button_recover);

        /*---set data---*/
        dataAccounts = AccountsDAO.readAccounts(this);

        /*---hide and show password---*/
        HideAndShowPassword();

        /*---event button sign in---*/
        EventButtonSignIn();

        /*---event button sign up---*/
        EventButtonSignUp();

        /*---event button recover password---*/
        EventRecoverPassword();
    }

    /*=============================================================================================*/
    /*---method || hide and show password---*/
    /*=============================================================================================*/

    private void HideAndShowPassword() {
        main_login_showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    main_login_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    main_login_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    /*=============================================================================================*/
    /*---method || event button sign in---*/
    /*=============================================================================================*/

    private void EventButtonSignIn() {
        main_login_button_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_value = main_login_email.getText().toString();
                String password_value = main_login_password.getText().toString();

                if(TextUtils.isEmpty(email_value)) {
                    main_login_email.requestFocus();
                    main_login_email.setError("Please enter your email address !");
                } else if(!Patterns.EMAIL_ADDRESS.matcher(email_value).matches()) {
                    main_login_email.requestFocus();
                    main_login_email.setError("Please enter a valid email address !");
                } else if(TextUtils.isEmpty(password_value)) {
                    main_login_password.requestFocus();
                    main_login_password.setError("Please enter your password !");
                } else if(email_value.equals("admin@gmail.com") && password_value.equals("Admin123")) {
                    main_login_email.setError(null);
                    main_login_password.setError(null);

                    Intent intent = new Intent(MainActivity.this, MainActivityAdmin.class);
                    finish();
                    startActivity(intent);
                } else if(email_value.equals(accounts.getAccount_email()) && password_value.equals(accounts.getAccount_password())){
                    main_login_email.setError(null);
                    main_login_password.setError(null);

                    Intent intent = new Intent(MainActivity.this, MainActivityUser.class);
                    finish();
                    startActivity(intent);
                } else {
                    main_login_email.setError(null);
                    main_login_password.setError(null);
                    Toast.makeText(MainActivity.this, "SIGN IN FAILED !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*=============================================================================================*/
    /*---method || event button sign up---*/
    /*=============================================================================================*/

    private void EventButtonSignUp() {
        main_login_button_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivitySignUp.class);
                startActivity(intent);
            }
        });
    }

    /*=============================================================================================*/
    /*---method || event button recover password---*/
    /*=============================================================================================*/

    private void EventRecoverPassword() {
        main_login_button_recover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_value = main_login_email.getText().toString();

                if(TextUtils.isEmpty(email_value)) {
                    main_login_email.requestFocus();
                    main_login_email.setError("Please enter your email address !");
                } else if(!Patterns.EMAIL_ADDRESS.matcher(email_value).matches()) {
                    main_login_email.requestFocus();
                    main_login_email.setError("Please enter a valid email address !");
                } else {
                    main_login_email.setError(null);

                    Intent intent = new Intent(MainActivity.this, MainActivityVerify.class);
                    startActivity(intent);
                }
            }
        });
    }
}