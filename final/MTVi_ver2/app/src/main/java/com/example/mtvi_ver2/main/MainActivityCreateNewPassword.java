package com.example.mtvi_ver2.main;

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

import com.example.mtvi_ver2.R;
import com.example.mtvi_ver2.database.data.DataAccounts;
import com.example.mtvi_ver2.database.sqlite.AccountsDAO;
import com.example.mtvi_ver2.database.sqlite.ServicesDAO;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivityCreateNewPassword extends AppCompatActivity {

    ArrayList<DataAccounts> dataAccounts = new ArrayList<>();
    DataAccounts accounts = new DataAccounts();
    EditText main_create_newPassword, main_create_confirmPassword;
    CheckBox main_create_showPassword;
    Button main_create_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_create_new_password);

        /*---find view by id---*/
        main_create_newPassword = findViewById(R.id.main_create_newPassword);
        main_create_confirmPassword = findViewById(R.id.main_create_confirmPassword);
        main_create_showPassword = findViewById(R.id.main_create_showPassword);
        main_create_submit = findViewById(R.id.main_create_submit);

        /*---get data DAO and data intent---*/
        dataAccounts = AccountsDAO.readAccounts(MainActivityCreateNewPassword.this);
        Intent intent = getIntent();

        String get_intent_name = intent.getStringExtra("account_name");
        String get_intent_email = intent.getStringExtra("account_email");

        /*---hide and show password---*/
        HideAndShowPassword();

        /*---event button submit---*/
        EventButtonSubmit(accounts, get_intent_name, get_intent_email);
    }

    /*=============================================================================================*/
    /*---method || hide and show password---*/
    /*=============================================================================================*/

    private void HideAndShowPassword() {
        main_create_showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    main_create_newPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    main_create_confirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    main_create_newPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    main_create_confirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    /*=============================================================================================*/
    /*---method || check is valid password---*/
    /*=============================================================================================*/

    private boolean CheckIsValidPassword(String password) {
        Pattern pattern;
        Matcher matcher;

        String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    /*=============================================================================================*/
    /*---method || event button submit---*/
    /*=============================================================================================*/

    private void EventButtonSubmit(DataAccounts accounts, String account_name, String account_email) {
        main_create_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String new_password_value = main_create_newPassword.getText().toString();
                String confirm_password_value = main_create_confirmPassword.getText().toString();

                if(TextUtils.isEmpty(new_password_value)) {
                    main_create_newPassword.requestFocus();
                    main_create_newPassword.setError("Please enter your new password ! \nYour password much have : \n+ 8 or more characters \n+ Upper and lowercase letters \n+ At least one number \n+ No white spaces");
                } else if(!CheckIsValidPassword(new_password_value)) {
                    main_create_newPassword.requestFocus();
                    main_create_newPassword.setError("Your password much have : \n+ 8 or more characters \n+ Upper and lowercase letters \n+ At least one number \n+ No white spaces");
                } else if(TextUtils.isEmpty(confirm_password_value)) {
                    main_create_confirmPassword.requestFocus();
                    main_create_confirmPassword.setError("Please confirm your new password !");
                } else if(confirm_password_value.equals(new_password_value)) {
                    main_create_newPassword.setError(null);
                    main_create_confirmPassword.setError(null);

                    /*------*/
                    accounts.setAccount_name(account_name);
                    accounts.setAccount_email(account_email);
                    accounts.setAccount_password(new_password_value);
                    accounts.setAccount_check("0");

                    dataAccounts.add(accounts);

                    if(AccountsDAO.insertAccounts(MainActivityCreateNewPassword.this, accounts)){
                        Toast.makeText(MainActivityCreateNewPassword.this, "INSERT ACCOUNT SUCCESSFULLY !!!", Toast.LENGTH_SHORT).show();
                        dataAccounts.clear();
                        dataAccounts.addAll(AccountsDAO.readAccounts(MainActivityCreateNewPassword.this));
                        Intent intent = new Intent(MainActivityCreateNewPassword.this, MainActivity.class);
                        finish();
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivityCreateNewPassword.this, "INSERT ACCOUNT FAILED !!!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    main_create_confirmPassword.requestFocus();
                    main_create_confirmPassword.setError("YOUR CONFIRM PASSWORD IS NOT CORRECTLY !");
                }
            }
        });
    }
}