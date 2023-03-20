package com.example.exercise02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    /*---random number---*/
    Random r = new Random();
    int numberItems = r.nextInt((10-1)+1)+1;

    /*---list view---*/
    ListView listView;
    List<String> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*---find id---*/
        listView = findViewById(R.id.listView);

        /*---random item---*/
        for(int i = 0; i < numberItems; i++){
            items.add("Item " + i);
        }

        /*---set adapter view---*/
        CustomList array = new CustomList(this, R.layout.custom_list, items);
        listView.setAdapter(array);

    }
}