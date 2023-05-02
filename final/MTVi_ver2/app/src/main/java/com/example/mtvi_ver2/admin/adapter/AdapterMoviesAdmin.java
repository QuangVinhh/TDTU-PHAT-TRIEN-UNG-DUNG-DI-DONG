package com.example.mtvi_ver2.admin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mtvi_ver2.R;
import com.example.mtvi_ver2.database.data.DataMovies;
import com.example.mtvi_ver2.database.data.DataServices;
import com.example.mtvi_ver2.myInterface.InterfaceClickItemMovies;

import java.util.ArrayList;

public class AdapterMoviesAdmin extends RecyclerView.Adapter<AdapterMoviesAdmin.MoviesAdminViewHolder> {
    ArrayList<DataMovies> mDataMovies;
    InterfaceClickItemMovies interfaceClickItemMovies;

    public AdapterMoviesAdmin(ArrayList<DataMovies> mDataMovies, InterfaceClickItemMovies mListener) {
        this.mDataMovies = mDataMovies;
        this.interfaceClickItemMovies = mListener;
    }

    @NonNull
    @Override
    public MoviesAdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movies_admin, parent, false);
        return new MoviesAdminViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMoviesAdmin.MoviesAdminViewHolder holder, int position) {
        DataMovies movies = mDataMovies.get(position);

        /*---false---*/
        if(movies == null){
            return;
        }

        /*---true---*/
        holder.FMA_adapter_imageView.setImageResource(Integer.parseInt(movies.getMovie_image()));
        holder.FMA_adapter_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceClickItemMovies.onClickItemMovies(movies);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mDataMovies != null){
            return mDataMovies.size();
        }
        return 0;
    }

    public class MoviesAdminViewHolder extends RecyclerView.ViewHolder {

        CardView FMA_adapter_cardView;
        ImageView FMA_adapter_imageView;

        public MoviesAdminViewHolder(@NonNull View itemView) {
            super(itemView);

            FMA_adapter_cardView = itemView.findViewById(R.id.FMA_adapter_cardView);
            FMA_adapter_imageView = itemView.findViewById(R.id.FMA_adapter_imageView);

        }

    }
}
