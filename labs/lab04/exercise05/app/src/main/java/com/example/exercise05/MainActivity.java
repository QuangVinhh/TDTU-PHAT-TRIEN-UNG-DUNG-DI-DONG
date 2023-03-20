package com.example.exercise05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int TOTAL_ITEMS = 5;
    Button btnCreate;
    Button btnRemove;
    TextView txtTotalUsers;
    ListView lvUsers;
    List<User> users = new ArrayList<>();
    UserArrayAdapter phoneArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvUsers = findViewById(R.id.lv_users);
        btnCreate = findViewById(R.id.activity_users_btn_create);
        btnRemove = findViewById(R.id.activity_users_btn_remove);
        txtTotalUsers = findViewById(R.id.activity_users_txt_total_users);

        phoneArrayAdapter = new UserArrayAdapter(this, R.layout.custom_list, users);
        lvUsers.setAdapter(phoneArrayAdapter);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                populateUserDetails(5);
                setTotalUsers();
                phoneArrayAdapter.notifyDataSetChanged();
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(users.isEmpty()) {
                    Toast.makeText(MainActivity.this, "List of users is empty", Toast.LENGTH_LONG).show();
                } else {
                    removeUsers(5);
                }
                setTotalUsers();
                phoneArrayAdapter.notifyDataSetChanged();
            }
        });
    }

    private void setTotalUsers() {
        int numberOfUsers = users.size();
        txtTotalUsers.setText("Total users: " + numberOfUsers);
    }

    private void removeUsers(int numberOfUsers) {
        for (int i = 0; i < numberOfUsers; i++) {
            users.remove(users.size() - 1);
        }
    }

    private void populateUserDetails(int numberOfUsers) {
        int indexFrom = users.size() + 1;
        int indexTo = indexFrom + numberOfUsers;
        for (int i = indexFrom; i < indexTo; i++) {
            users.add(new User("User " + i, "user" + i + "@tdtu.edu.vn"));
        }
    }
}

