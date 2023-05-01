package com.example.demo.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.demo.MainActivity;
import com.example.demo.R;

public class MainActivityAccount extends AppCompatActivity {

    Button button_edit, button_add_new_payment, button_billing_details, button_change, button_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_account);

        /*---find view by id---*/
        button_edit = findViewById(R.id.button_edit);
        button_add_new_payment = findViewById(R.id.button_add_new_payment);
        button_billing_details = findViewById(R.id.button_billing_details);
        button_change = findViewById(R.id.button_change);
        button_logout = findViewById(R.id.button_logout);

        /*---event button billing details---*/
        EventButtonBillingDetails();

        /*---event button change---*/
        EventButtonChange();

        /*---event button logout---*/
        EventButtonLogout();
    }

    ///*---method | event button logout---*///
    private void EventButtonLogout() {
        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityAccount.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    ///*---method | event button change---*///
    private void EventButtonChange() {
        button_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityAccount.this, MainActivityService.class);
                startActivity(intent);
            }
        });
    }

    ///*---method | event button billing details---*///
    private void EventButtonBillingDetails() {
        button_billing_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityAccount.this, MainActivityBilling.class);
                startActivity(intent);
            }
        });
    }
}
