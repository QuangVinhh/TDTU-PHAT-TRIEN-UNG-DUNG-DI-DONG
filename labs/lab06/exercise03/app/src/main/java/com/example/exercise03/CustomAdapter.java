package com.example.exercise03;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Event> {
    Context context;
    int layout;
    List<Event> data;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Event> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.data = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layout, parent, false);

            holder = new ViewHolder();
            holder.name = convertView.findViewById(R.id.textView_name);
            holder.place = convertView.findViewById(R.id.textView_place);
            holder.datetime = convertView.findViewById(R.id.textView_date);
            holder.eventSwitch = convertView.findViewById(R.id.switch_list);

            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        Event e = data.get(position);

        holder.name.setText(e.getName());
        holder.place.setText(e.getPlace());
        holder.datetime.setText(e.getDate() + " " + e.getName());

        holder.eventSwitch.setChecked(e.getDone());
        holder.eventSwitch.setTag(position); // attach position to checkbox

        holder.eventSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Event p = data.get(position);
                boolean checked = ((CheckBox)v).isChecked();
                p.setDone(checked);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    public static class ViewHolder {
        TextView name;
        TextView place;
        TextView datetime;
        Switch eventSwitch;
    }
}
