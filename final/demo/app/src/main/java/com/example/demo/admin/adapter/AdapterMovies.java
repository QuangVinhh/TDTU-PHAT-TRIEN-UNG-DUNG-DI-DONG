package com.example.demo.admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.R;
import com.example.demo.admin.fragments.FragmentMoviesAdmin;
import com.example.demo.database.DataMovies;

import java.util.List;

public class AdapterMovies extends RecyclerView.Adapter<AdapterMovies.MyViewHolder> {

    private Context mContext;
    private List<DataMovies> mData;

    public AdapterMovies(Context mContext, List<DataMovies> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.admin_movies_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.imageView_movie.setImageResource(mData.get(position).getMovie_image());
        holder.cardView_movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView_movie;
        CardView cardView_movies;
        public MyViewHolder(View itemView) {
            super(itemView);

            imageView_movie = itemView.findViewById(R.id.imageView_movie);
            cardView_movies = itemView.findViewById(R.id.cardView_movies);
        }
    }
}
