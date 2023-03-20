package com.example.exercise04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    /*---random number---*/
    Random r = new Random();
    int numberItems = r.nextInt((100-10)+1)+10;
    GridView gridView_pc;
    List<Computer> computers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView_pc = findViewById(R.id.gridView_pc);

        for (int i = 1; i < numberItems; i++) {
            String computerName = "PC " + i;
            Computer newComputer = new Computer(computerName, true);
            computers.add(newComputer);
        }

        ComputerArrayAdapter array = new ComputerArrayAdapter(this, R.layout.custom_list, computers);
        gridView_pc.setAdapter(array);
    }
}