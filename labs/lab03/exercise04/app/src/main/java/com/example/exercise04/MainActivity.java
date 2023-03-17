package com.example.exercise04;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Profile profile;
    ImageView imageView_avatar;
    ImageButton imageButton_edit;
    TextView textView_ava1;
    TextView textView_major;
    TextView textView_name;
    TextView textView_phone;
    TextView textView_email;
    TextView textView_address;
    TextView textView_homepage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_ava1 = findViewById(R.id.textView_ava1);
        textView_major = findViewById(R.id.textView_major);
        textView_name = findViewById(R.id.textView_name);
        textView_phone = findViewById(R.id.textView_phone);
        textView_email = findViewById(R.id.textView_email);
        textView_address = findViewById(R.id.textView_address);
        textView_homepage = findViewById(R.id.textView_homepage);

        imageView_avatar = findViewById(R.id.imageView_avatar_1);
        imageButton_edit = findViewById(R.id.imageButton_edit);

        /*---event---*/
        imageButton_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap avatar = imageView_avatar.getDrawingCache();
                String name_ava = textView_ava1.getText().toString();
                String major = textView_major.getText().toString();
                String name = textView_name.getText().toString();
                String phone = textView_phone.getText().toString();
                String email = textView_email.getText().toString();
                String address = textView_address.getText().toString();
                String homepage = textView_homepage.getText().toString();

                /*---tạo chuyển layout qua main 2---*/
                profile = new Profile(avatar, name_ava, major, name, phone, email, address, homepage);
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                /*---lấy data từ main 1 sang main 2---*/
                intent.putExtra("avatar", profile.getAvatar());
                intent.putExtra("name_ava", profile.getName_ava());
                intent.putExtra("major", profile.getMajor());
                intent.putExtra("name", profile.getName());
                intent.putExtra("phone", profile.getPhone());
                intent.putExtra("email", profile.getEmail());
                intent.putExtra("address", profile.getAddress());
                intent.putExtra("homepage", profile.getHomepage());

                /*---lệnh trả về main 1 từ main 2---*/
                startActivityForResult(intent, 1234);
            }
        });
    }

    /*---lấy data từ main 2 về main 1---*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*---lấy data từ main 2 set lại cho main 1---*/
        Bitmap avatar = (Bitmap) data.getExtras().get("data");
        String name_avatar = data.getStringExtra("name");
        String major = data.getStringExtra("major");
        String name = data.getStringExtra("name");
        String phone = data.getStringExtra("phone");
        String email = data.getStringExtra("email");
        String address = data.getStringExtra("address");
        String homepage = data.getStringExtra("homepage");

        imageView_avatar.setImageBitmap(avatar);
        textView_ava1.setText(name_avatar);
        textView_major.setText(major);
        textView_name.setText(name);
        textView_phone.setText(phone);
        textView_email.setText(email);
        textView_address.setText(address);
        textView_homepage.setText(homepage);
    }
}

/*---ERROR
* Lỗi set avatar
---*/