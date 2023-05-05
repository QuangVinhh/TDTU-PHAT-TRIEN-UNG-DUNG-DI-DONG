package com.example.mtvi_ver2.user.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.example.mtvi_ver2.R;
import com.example.mtvi_ver2.database.data.DataMovies;
import com.example.mtvi_ver2.myInterface.InterfaceClickItemMovies;

import java.util.ArrayList;
import java.util.Random;

public class AdapterHomeUser extends RecyclerView.Adapter<AdapterHomeUser.HomeUserViewHolder> {

    ArrayList<DataMovies> mDataMovies;
    InterfaceClickItemMovies interfaceClickItemMovies;
    Context mContext;

    public AdapterHomeUser(Context mContext, ArrayList<DataMovies> mDataMovies, InterfaceClickItemMovies mListener) {
        this.mContext = mContext;
        this.mDataMovies = mDataMovies;
        this.interfaceClickItemMovies = mListener;
    }

    @NonNull
    @Override
    public AdapterHomeUser.HomeUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movies_admin, parent, false);
        return new AdapterHomeUser.HomeUserViewHolder(view);
    }

    public void onBindViewHolder(@NonNull AdapterHomeUser.HomeUserViewHolder holder, int position) {
        DataMovies movies = mDataMovies.get(position);

        /*---false---*/
        if(movies == null){
            return;
        }

        /*---true---*/
        holder.FMA_adapter_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceClickItemMovies.onClickItemMovies(movies);
            }
        });

        /*------*/
        String movie_image = movies.getMovie_image();

        GlideUrl url = new GlideUrl(movie_image, new LazyHeaders.Builder()
                .addHeader("User-Agent", WebSettings.getDefaultUserAgent(mContext))
                .build());

        Glide.with(mContext)
                .load(url)
                .into(holder.FMA_adapter_imageView);
    }

    @Override
    public int getItemCount() {
        if(mDataMovies != null){
            return mDataMovies.size();
        }
        return 0;
    }

    public class HomeUserViewHolder extends RecyclerView.ViewHolder {

        CardView FMA_adapter_cardView;
        ImageView FMA_adapter_imageView, FHU_viewUser_image;

        public HomeUserViewHolder(@NonNull View itemView) {
            super(itemView);

            FMA_adapter_cardView = itemView.findViewById(R.id.FMA_adapter_cardView);
            FMA_adapter_imageView = itemView.findViewById(R.id.FMA_adapter_imageView);
        }

    }
}
