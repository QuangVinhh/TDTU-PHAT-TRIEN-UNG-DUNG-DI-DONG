package com.example.exercise05;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class UserArrayAdapter extends ArrayAdapter<User> {
    Context context;
    int layoutToBeInflated;
    List<User> users;

    public UserArrayAdapter(@NonNull Context context, int resource, @NonNull List<User> users) {
        super(context, resource, users);
        this.users = users;
        this.layoutToBeInflated = resource;
        this.context = context;
    }

    private class UserViewHolder {
        TextView tvUsername;
        TextView tvEmail;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        UserViewHolder holder;
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutToBeInflated, null);
            holder = new UserViewHolder();
            holder.tvUsername = row.findViewById(R.id.tv_username);
            holder.tvEmail = row.findViewById(R.id.tv_email);
            row.setTag(holder);
        } else {
            // row was already created- no need to inflate and invoke findViewById
            // getTag() returns the object originally stored in this view
            holder = (UserViewHolder) row.getTag();
        }

        User user = users.get(position);
        holder.tvUsername.setText(user.getUsername());
        holder.tvEmail.setText(user.getEmail());

        // row listener (user clicks on any other part of the row)
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = users.get(position);
                Toast.makeText(context,
                        "USER CLICKED - " + user.getUsername(), Toast.LENGTH_SHORT).show();
            }
        });

        return row;
    }
}
