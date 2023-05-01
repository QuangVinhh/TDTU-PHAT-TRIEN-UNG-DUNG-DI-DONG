package com.example.exercise03;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    CustomAdapter adapter;
    ArrayList<Event> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*---find id---*/
        listView = findViewById(R.id.listView);

        /*---set data for list---*/
        data = new ArrayList<>();
        data.add(new Event("Sinh hoat chu nhiem", "C120", "09/03/2020", "04:43"));
        data.add(new Event("Huong dan luan van", "C120", "09/03/2020", "04:43"));

        adapter = new CustomAdapter(this, R.layout.activity_custom, data);

        /*---set adapter for list---*/
        adapter = new CustomAdapter(
                this,
                R.layout.activity_custom,
                data
        );

        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.miSwitch);
        menuItem.setActionView(R.layout.actionbar_switch);

        Switch switchEvent = menuItem.getActionView().findViewById(R.id.sw_event);

        switchEvent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {

                } else {

                }
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.miDeleteAll) {
            showConfirmDialog();
        } else if (item.getItemId() == R.id.miAbout) {
            showAboutDialog();
        } else if (item.getItemId() == R.id.miCreate) {
            openCreateNewEventActivity();
        }
        return super.onOptionsItemSelected(item);
    }

    private void openCreateNewEventActivity() {
        // create intent to call NewEventActivity
        Intent newEventActivityIntent = new Intent(MainActivity.this, NewEventActivity.class);
        startActivityForResult(newEventActivityIntent, 100);
    }

    private void showAboutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("About");
        builder.setMessage("About information !");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "GOOD LUCK!", Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showConfirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm");
        builder.setMessage("Are you sure to delete all phones?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                data.clear();
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "The selected is deleted!", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // do nothing
            }
        });

        AlertDialog confirmDialog = builder.create();
        confirmDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && resultCode == Activity.RESULT_OK) {
            Bundle returnedBundle = data.getExtras();
            String title = returnedBundle.getString(NewEventActivity.TITLE);
            String place = returnedBundle.getString(NewEventActivity.PLACE);
            String date = returnedBundle.getString(NewEventActivity.DATE);
            String time = returnedBundle.getString(NewEventActivity.TIME);
            Event newEvent = new Event(title, place, date, time);
            data.addCategory(String.valueOf(newEvent));
            adapter.notifyDataSetChanged();
        }
    }
}