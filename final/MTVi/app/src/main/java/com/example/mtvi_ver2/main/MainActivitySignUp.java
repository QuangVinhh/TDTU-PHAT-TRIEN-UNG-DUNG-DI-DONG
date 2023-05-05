package com.example.mtvi_ver2.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mtvi_ver2.R;
import com.example.mtvi_ver2.database.data.DataAccounts;
import com.example.mtvi_ver2.database.sqlite.AccountsDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivitySignUp extends AppCompatActivity {

    ArrayList<DataAccounts> dataAccounts = new ArrayList<>();
    DataAccounts accounts;
    EditText main_signUp_name, main_signUp_email;
    CheckBox main_signUp_checkAgree;
    Button main_signUp_button_signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sign_up);

        /*---find view by id---*/
        main_signUp_name = findViewById(R.id.main_signUp_name);
        main_signUp_email = findViewById(R.id.main_signUp_email);
        main_signUp_checkAgree = findViewById(R.id.main_signUp_checkAgree);
        main_signUp_button_signUp = findViewById(R.id.main_signUp_button_signUp);

        /*---event button sign up---*/
        EventButtonSignUp();
    }

    /*=============================================================================================*/
    /*---method || event button sign up---*/
    /*=============================================================================================*/

    private void EventButtonSignUp() {
        main_signUp_button_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_value = main_signUp_name.getText().toString();
                String email_value = main_signUp_email.getText().toString();

                if(TextUtils.isEmpty(name_value)) {
                    main_signUp_name.requestFocus();
                    main_signUp_name.setError("Please enter your full name !");
                } else if(TextUtils.isEmpty(email_value)) {
                    main_signUp_email.requestFocus();
                    main_signUp_email.setError("Please enter your email address !");
                } else if(!Patterns.EMAIL_ADDRESS.matcher(email_value).matches()) {
                    main_signUp_email.requestFocus();
                    main_signUp_email.setError("Please enter a valid email address !");
                } else if(main_signUp_checkAgree.isChecked() == false) {
                    main_signUp_checkAgree.requestFocus();
                    main_signUp_checkAgree.setError("Please confirm our conditions !");
                } else {
                    main_signUp_name.setError(null);
                    main_signUp_email.setError(null);
                    main_signUp_checkAgree.setError(null);

                    /*---step 1 --> Verify.class --> Create.class---*/

                    /*---step 2---*/
                    accounts = new DataAccounts(-1, name_value, email_value, "", "");

                    boolean checkEmail = AccountsDAO.checkEmail(MainActivitySignUp.this, email_value);
                    if(checkEmail){
                        main_signUp_email.requestFocus();
                        main_signUp_email.setError("The email address have existed !");
                    } else {
                        Intent intent = new Intent(MainActivitySignUp.this, MainActivityCreateNewPassword.class);
                        intent.putExtra("account_id", accounts.getAccount_id());
                        intent.putExtra("account_name", accounts.getAccount_name());
                        intent.putExtra("account_email", accounts.getAccount_email());
                        intent.putExtra("account_password", accounts.getAccount_password());
                        intent.putExtra("account_check", accounts.getAccount_check());

                        finish();
                        startActivity(intent);
                    }
                }
            }
        });
    }
}