package com.example.exercise03;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Phone> {

    Context context;
    int layoutToBeInflated;
    List<Phone> phones_temp;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Phone> phones_temp) {
        super(context, resource, phones_temp);
        this.phones_temp = phones_temp;
        this.layoutToBeInflated = resource;
        this.context = context;
    }

    private class phonesViewHolder {
        TextView textView_phone;
        CheckBox checkBox_phone;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        phonesViewHolder holder;
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutToBeInflated, null);
            holder = new phonesViewHolder();
            holder.textView_phone = row.findViewById(R.id.textView_phone);
            holder.checkBox_phone = row.findViewById(R.id.checkBox_phone);
            row.setTag(holder);
        } else {
            // row was already created- no need to inflate and invoke findViewById
            // getTag() returns the object originally stored in this view
            holder = (phonesViewHolder) row.getTag();
        }

        Phone phone = phones_temp.get(position);
        holder.textView_phone.setText(phone.getName());
        holder.checkBox_phone.setChecked(phone.isSelected());

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Phone temp = phones_temp.get(position);
                Toast.makeText(context,"PHONE CLICKED - " + temp.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        return row;
    }
}
