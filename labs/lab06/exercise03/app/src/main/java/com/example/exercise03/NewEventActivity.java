package com.example.exercise03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.accessibilityservice.GestureDescription;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class NewEventActivity extends AppCompatActivity { 
    
    static String TITLE = "TITLE";
    static String PLACE = "PLACE";
    static String DATE = "DATE";
    static String TIME = "TIME";
    TextView etName;
    TextView etPlace;
    TextView etDate;
    TextView etTime;
    String[] places = new String[] {"C201", "C202", "C203", "C204"};
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        // bind ui controls
        etName = findViewById(R.id.et_name);
        etPlace = findViewById(R.id.et_place);
        etDate = findViewById(R.id.et_date);
        etTime = findViewById(R.id.et_time);

        etPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new Builder(this);
                builder.setTitle("Select place");
                builder.setSingleChoiceItems(places, 2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        etPlace.setText(places[i]);
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

        });

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get current date
                Calendar c = Calendar.getInstance();
                int startYear = c.get(Calendar.YEAR);
                int startMonth = c.get(Calendar.MONTH);
                int startDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        etDate.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
                    }
                }, startYear, startMonth, startDay);
                datePickerDialog.show();
            }
        });

        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get current time
                int mHourOfDay = 0;
                int mMinute = 0;

                TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        etTime.setText(hourOfDay + ":" + minute);
                    }
                }, mHourOfDay, mMinute, false);
                timePickerDialog.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.createn_screen_option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.miSave) {
            Intent exercise02ActivityIntent = new Intent();

            Bundle bundle = new Bundle();
            bundle.putString(TITLE, etName.getText().toString());
            bundle.putString(PLACE, etPlace.getText().toString());
            bundle.putString(DATE, etDate.getText().toString());
            bundle.putString(TIME, etTime.getText().toString());

            exercise02ActivityIntent.putExtras(bundle);

            setResult(Activity.RESULT_OK, exercise02ActivityIntent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}