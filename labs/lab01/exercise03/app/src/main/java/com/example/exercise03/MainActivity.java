package com.example.exercise03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText billText;
    TextView percentText;
    Button subText;
    Button addText;
    TextView tipText;
    TextView totalText;

    int percentNumber = 10;
    int billNumber;
    double tipTotal;
    double totalTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billText = findViewById(R.id.editTextNumber_money);
        percentText = findViewById(R.id.textView_percent);
        subText = findViewById(R.id.button_sub);
        addText = findViewById(R.id.button_add);
        tipText = findViewById(R.id.textView_tip);
        totalText = findViewById(R.id.textView_total);

        subText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                percentNumber--;
                percentText.setText(percentNumber + "%");
                if (percentNumber < 6){
                    subText.setEnabled(false);
                }
            }
        });

        addText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                percentNumber++;
                percentText.setText(percentNumber + "%");
                if (percentNumber > 5){
                    subText.setEnabled(true);
                }
            }
        });

        billText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String bill = billText.getText().toString();
                if(TextUtils.isEmpty(bill)){
                    Toast.makeText(MainActivity.this, "Please enter your money", Toast.LENGTH_SHORT).show();
                    tipText.setText("$0");
                    totalText.setText("$0");
                } else {
                    billNumber = Integer.parseInt(bill);

                    tipTotal = billNumber * (percentNumber / (100*1.0));
                    tipText.setText(String.format("$%.2f", tipTotal));

                    totalTotal = billNumber + tipTotal;
                    totalText.setText(String.format("$%.2f", totalTotal));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        percentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String bill = billText.getText().toString();
                if(TextUtils.isEmpty(bill)){
                    Toast.makeText(MainActivity.this, "Please enter your money", Toast.LENGTH_SHORT).show();
                    tipText.setText("$0");
                    totalText.setText("$0");
                } else {
                    billNumber = Integer.parseInt(bill);

                    tipTotal = billNumber * (percentNumber / (100*1.0));
                    tipText.setText(String.format("$%.2f", tipTotal));

                    totalTotal = billNumber + tipTotal;
                    totalText.setText(String.format("$%.2f", totalTotal));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}