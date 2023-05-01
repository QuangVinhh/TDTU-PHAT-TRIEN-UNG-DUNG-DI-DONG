package com.example.demo.user;

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

import com.example.demo.R;

public class MainActivityVerify extends AppCompatActivity {

    EditText editText_code;
    TextView textView_timeCountdown;
    Button button_resendCode, button_verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_verify);

        /*---find view by id---*/
        editText_code = findViewById(R.id.editText_code);
        button_resendCode = findViewById(R.id.button_resendCode);
        textView_timeCountdown = findViewById(R.id.textView_timeCountdown);
        button_verify = findViewById(R.id.button_verify);

        /*---event count down timer---*/
        EventCountDownTimer();

        /*---event button resend code---*/
        EventResendCode();

        /*---event button verify---*/
        EventButtonVerify();
    }

    ///*---method | event count down timer---*///
    private void EventCountDownTimer() {
        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                textView_timeCountdown.setText("00:" + l/1000);

            }

            @Override
            public void onFinish() {
                button_resendCode.setEnabled(true);
                Drawable drawable = getResources().getDrawable(R.drawable.shape_button_sign_up);
                button_resendCode.setBackground(drawable);
            }
        }.start();
    }

    ///*---method | event button resend code---*///
    private void EventResendCode() {
        button_resendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivityVerify.this, "Resend Code Successfully !", Toast.LENGTH_SHORT).show();
                button_resendCode.setEnabled(false);
                Drawable drawable = getResources().getDrawable(R.drawable.shape_button_resend_code);
                button_resendCode.setBackground(drawable);
                EventCountDownTimer();
            }
        });
    }

    ///*---method | event button button verify---*///
    private void EventButtonVerify() {
        button_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code_value = editText_code.getText().toString();

                if (TextUtils.isEmpty(code_value)) {
                    editText_code.requestFocus();
                    editText_code.setError("Please enter your code number !");
                } else if (code_value.equals("123456")) {
                    editText_code.setError(null);

                    Intent intent = new Intent(MainActivityVerify.this, MainActivityCreateNewPassword.class);
                    startActivity(intent);
                } else {
                    editText_code.requestFocus();
                    editText_code.setError("Code number is not correct !");
                }
            }
        });
    }
}