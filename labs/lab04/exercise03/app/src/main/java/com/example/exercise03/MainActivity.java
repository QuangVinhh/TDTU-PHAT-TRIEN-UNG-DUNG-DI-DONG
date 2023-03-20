package com.example.exercise03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<Phone> phones = new ArrayList<>();

    Button button_remove_selected;
    Button button_remove_all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*---find id---*/
        listView = findViewById(R.id.listView);
        button_remove_selected = findViewById(R.id.button_remove_selected);
        button_remove_all = findViewById(R.id.button_remove_all);

        /*---add items to list phones---*/
        phones.add(new Phone("Apple", true));
        phones.add(new Phone("Samsung", true));
        phones.add(new Phone("Nokia", true));
        phones.add(new Phone("Oppo", true));

        /*---set view adapter---*/
        CustomAdapter array = new CustomAdapter(this, R.layout.custom_list, phones);
        listView.setAdapter(array);

        /*---event button---*/
        button_remove_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phones.clear();
                array.notifyDataSetChanged();
            }
        });

        button_remove_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Phone phone : phones) {
                    if (phone.isSelected()) {
                        phone.setSelected(false);
                    }
                }
                array.notifyDataSetChanged();
            }
        });
    }
}