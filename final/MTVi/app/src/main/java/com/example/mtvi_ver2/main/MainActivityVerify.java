package com.example.mtvi_ver2.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mtvi_ver2.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivityVerify extends AppCompatActivity {

    EditText main_verify_enterCode;
    TextView main_verify_timeCountdown;
    Button main_verify_button_resendCode, main_verify_button;
    String get_intent_email;
    int code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_verify);

        /*---find view by id---*/
        main_verify_enterCode = findViewById(R.id.main_verify_enterCode);
        main_verify_timeCountdown = findViewById(R.id.main_verify_timeCountdown);
        main_verify_button_resendCode = findViewById(R.id.main_verify_button_resendCode);
        main_verify_button = findViewById(R.id.main_verify_button);

        /*---get email---*/
        Intent intent = getIntent();
        get_intent_email = intent.getStringExtra("email_verify");

        /*---event button resend code---*/
        EventResendCode();

        /*---event button verify---*/
        EventButtonVerify();
    }

    /*=============================================================================================*/
    /*---method || event button sign up---*/
    /*=============================================================================================*/

    private void EventCountDownTimer() {
        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                main_verify_timeCountdown.setText("00:" + l/1000);
            }
            @Override
            public void onFinish() {
                main_verify_button_resendCode.setEnabled(true);
                Drawable drawable = getResources().getDrawable(R.drawable.shape_button_sign_up);
                main_verify_button_resendCode.setBackground(drawable);
            }
        }.start();
    }

    /*=============================================================================================*/
    /*---method || event button sign up---*/
    /*=============================================================================================*/

    private void EventResendCode() {
        main_verify_button_resendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivityVerify.this, "Resend Code Successfully !", Toast.LENGTH_SHORT).show();
                sendVerifyEmail();
                /*---event count down timer---*/
                EventCountDownTimer();
                main_verify_button_resendCode.setEnabled(false);
                Drawable drawable = getResources().getDrawable(R.drawable.shape_button_resend);
                main_verify_button_resendCode.setBackground(drawable);
            }
        });

    }

    /*=============================================================================================*/
    /*---method || event button sign up---*/
    /*=============================================================================================*/

    private void EventButtonVerify() {
        main_verify_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputCode;
                inputCode = main_verify_enterCode.getText().toString();

                if (TextUtils.isEmpty(inputCode)) {
                    main_verify_enterCode.requestFocus();
                    main_verify_enterCode.setError("Please enter your code number !");
                } else if (inputCode.equals(code)) {
                    main_verify_enterCode.setError(null);

                    Intent intent = new Intent(MainActivityVerify.this, MainActivityCreateNewPassword.class);
                    intent.putExtra("email_change_password", get_intent_email);
                    finish();
                    startActivity(intent);
                } else {
                    main_verify_enterCode.requestFocus();
                    main_verify_enterCode.setError("CODE NUMBER IS NOT CORRECT !");
                }
            }
        });
    }

    /*=============================================================================================*/
    /*---method || send verify email---*/
    /*=============================================================================================*/

    private void sendVerifyEmail(){
        Random random = new Random();
        code = random.nextInt(899999) + 100000;
        String url = "file:///E:/university/NAM%204/PHAT-TRIEN-UNG-DUNG-DI-DONG/final/MTVi_ver2/phpSendOTP/sendingOTP.php";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivityVerify.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityVerify.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", get_intent_email);
                params.put("code", String.valueOf(code));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    /*=============================================================================================*/
    /*---method || check code---*/
    /*=============================================================================================*/
    private void checkCode(View view){
        String inputCode;
        inputCode = main_verify_enterCode.getText().toString();

        if(inputCode.equals(code)){
            Toast.makeText(this, "SUCCESSFULLY", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "FAILED", Toast.LENGTH_SHORT).show();
        }
    }
}