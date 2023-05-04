package com.example.mtvi_ver2.main;

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

import com.example.mtvi_ver2.R;

public class MainActivityVerify extends AppCompatActivity {

    EditText main_verify_enterCode;
    TextView main_verify_timeCountdown;
    Button main_verify_button_resendCode, main_verify_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_verify);

        /*---find view by id---*/
        main_verify_enterCode = findViewById(R.id.main_verify_enterCode);
        main_verify_timeCountdown = findViewById(R.id.main_verify_timeCountdown);
        main_verify_button_resendCode = findViewById(R.id.main_verify_button_resendCode);
        main_verify_button = findViewById(R.id.main_verify_button);

        /*---event count down timer---*/
        EventCountDownTimer();

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
                main_verify_button_resendCode.setEnabled(false);
                Drawable drawable = getResources().getDrawable(R.drawable.shape_button_resend);
                main_verify_button_resendCode.setBackground(drawable);
                EventCountDownTimer();
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
                String code_value = main_verify_enterCode.getText().toString();

                if (TextUtils.isEmpty(code_value)) {
                    main_verify_enterCode.requestFocus();
                    main_verify_enterCode.setError("Please enter your code number !");
                } else if (code_value.equals("123456")) {
                    main_verify_enterCode.setError(null);

                    Intent intent = new Intent(MainActivityVerify.this, MainActivityCreateNewPassword.class);
                    startActivity(intent);
                } else {
                    main_verify_enterCode.requestFocus();
                    main_verify_enterCode.setError("CODE NUMBER IS NOT CORRECT !");
                }
            }
        });
    }
}