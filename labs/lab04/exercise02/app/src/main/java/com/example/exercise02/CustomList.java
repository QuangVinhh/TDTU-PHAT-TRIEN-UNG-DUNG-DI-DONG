package com.example.exercise02;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomList extends ArrayAdapter<String> {

    Context context;
    int layoutToBeInflated;
    List<String> items;

    public CustomList(@NonNull Context context, int resource, @NonNull List<String> items){
        super(context, resource, items);
        this.items = items;
        this.context = context;
        layoutToBeInflated = resource;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ItemViewHolder {
        TextView textView_item;
        Button button_remove;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ItemViewHolder holder;

        View row = convertView;

        if (row == null) {

            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutToBeInflated, null);

            holder = new ItemViewHolder();
            holder.textView_item = row.findViewById(R.id.textView_item);
            holder.button_remove = row.findViewById(R.id.button_remove);

            row.setTag(holder);

        } else {
            holder = (ItemViewHolder) row.getTag();
        }

        String item = items.get(position);
        holder.textView_item.setText(item);

        holder.button_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.remove(position);
                notifyDataSetChanged();
            }
        });

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "ROW CLICKED - " + position, Toast.LENGTH_SHORT).show();
            }
        });

        return row;
    }
}
