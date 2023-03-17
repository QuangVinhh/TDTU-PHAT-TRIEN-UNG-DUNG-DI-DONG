package com.example.exercise04;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView textView_ava2;
    EditText editText_major;
    EditText editText_name;
    EditText editText_phone;
    EditText editText_email;
    EditText editText_address;
    EditText editText_homepage;
    Button button_save;
    ImageButton imageView_avatar_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        /*---khai báo ID---*/
        imageView_avatar_2 = findViewById(R.id.imageView_avatar_2);
        textView_ava2 = findViewById(R.id.textView_ava2);
        editText_major = findViewById(R.id.editText_major);
        editText_name = findViewById(R.id.editText_name);
        editText_phone = findViewById(R.id.editText_phone);
        editText_email = findViewById(R.id.editText_email);
        editText_address = findViewById(R.id.editText_address);
        editText_homepage = findViewById(R.id.editText_homepage);
        button_save = findViewById(R.id.button_save);

        /*---nhận data từ main 1---*/
        Intent intent = getIntent();

        Bitmap get_avatar = (Bitmap) intent.getExtras().get("avatar");
        String get_textView_ava2 = intent.getStringExtra("name_ava");
        String get_editText_major = intent.getStringExtra("major");
        String get_editText_name = intent.getStringExtra("name");
        String get_editText_phone = intent.getStringExtra("phone");
        String get_editText_email = intent.getStringExtra("email");
        String get_editText_address = intent.getStringExtra("address");
        String get_editText_homepage = intent.getStringExtra("homepage");

        /*---set lại data khi nhận từ main 1---*/
        imageView_avatar_2.setImageBitmap(get_avatar);
        textView_ava2.setText(get_textView_ava2);
        editText_major.setText(get_editText_major);
        editText_name.setText(get_editText_name);
        editText_phone.setText(get_editText_phone);
        editText_email.setText(get_editText_email);
        editText_address.setText(get_editText_address);
        editText_homepage.setText(get_editText_homepage);

        /*---event open camera---*/
        imageView_avatar_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
                startActivityForResult(cameraIntent, 1234);
            }
        });

        /*---button save---*/
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*---lấy data từ main 2 gửi về main 1---*/
                Intent resultIntent = new Intent();
                resultIntent.putExtra("avatar", imageView_avatar_2.getDrawingCache());
                resultIntent.putExtra("major", editText_major.getText().toString());
                resultIntent.putExtra("name", editText_name.getText().toString());
                resultIntent.putExtra("phone", editText_phone.getText().toString());
                resultIntent.putExtra("email", editText_email.getText().toString());
                resultIntent.putExtra("address", editText_address.getText().toString());
                resultIntent.putExtra("homepage", editText_homepage.getText().toString());
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap image = (Bitmap) data.getExtras().get("data");
        imageView_avatar_2.setImageBitmap(image);
    }
}