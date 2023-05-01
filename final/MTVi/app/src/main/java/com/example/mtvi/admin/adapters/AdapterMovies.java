package com.example.mtvi.admin.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mtvi.R;
import com.example.mtvi.admin.views.ViewMoviesAdmin;
import com.example.mtvi.database.DataMovies;

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
        view = mInflater.inflate(R.layout.adapter_movies_admin, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.imageView_movie.setImageResource(mData.get(position).getMovie_image());
        holder.cardView_movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ViewMoviesAdmin.class);
                intent.putExtra("movie_id", mData.get(position).getMovie_id());
                intent.putExtra("movie_name", mData.get(position).getMovie_name());
                intent.putExtra("movie_genres", mData.get(position).getMovie_genres());
                intent.putExtra("movie_description", mData.get(position).getMovie_description());
                intent.putExtra("movie_image", mData.get(position).getMovie_image());
                intent.putExtra("movie_linked", mData.get(position).getMovie_linked());
                mContext.startActivity(intent);
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

            imageView_movie = itemView.findViewById(R.id.imageView_movies);
            cardView_movies = itemView.findViewById(R.id.cardView_movies);
        }
    }
}
