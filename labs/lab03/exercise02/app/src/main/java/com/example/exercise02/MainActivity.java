package com.example.exercise02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText_link;
    Button button_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_link = findViewById(R.id.editText_link);
        button_link = findViewById(R.id.button_link);

        button_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link = editText_link.getText().toString();

                if(TextUtils.isEmpty(link)){
                    Toast.makeText(MainActivity.this, "vui long nhap duong link", Toast.LENGTH_SHORT).show();
                } else {
                    String url = editText_link.getText().toString();
                    Intent urlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(editText_link.getText().toString() + url));
                    startActivity(urlIntent);
                }
            }
        });
    }
}