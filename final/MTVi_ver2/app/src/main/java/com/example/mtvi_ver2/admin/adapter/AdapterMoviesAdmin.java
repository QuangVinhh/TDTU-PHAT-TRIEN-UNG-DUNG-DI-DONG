package com.example.mtvi_ver2.admin.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mtvi_ver2.R;
import com.example.mtvi_ver2.database.data.DataMovies;
import com.example.mtvi_ver2.myInterface.InterfaceClickItemMovies;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class AdapterMoviesAdmin extends RecyclerView.Adapter<AdapterMoviesAdmin.MoviesAdminViewHolder> {
    ArrayList<DataMovies> mDataMovies;
    InterfaceClickItemMovies interfaceClickItemMovies;
    Context mContext;

    public AdapterMoviesAdmin(Context mContext, ArrayList<DataMovies> mDataMovies, InterfaceClickItemMovies mListener) {
        this.mContext = mContext;
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
    public void onBindViewHolder(@NonNull MoviesAdminViewHolder holder, int position) {
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

        /*---*/
        String image_movie = movies.getMovie_image();
        URL url = null;
        try {
            url = new URL(image_movie);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        Bitmap bmp = null;
        try {
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        holder.FMA_adapter_imageView.setImageBitmap(bmp);

        Log.d(">>>IMAGE : ", movies.getMovie_image());
//        Picasso.get()
//                .load(movies.getMovie_image())
//                .placeholder(R.drawable.baseline_hide_image_24)
//                .into(holder.FMA_adapter_imageView, new Callback() {
//                    @Override
//                    public void onSuccess() {
//
//                    }
//
//                    @Override
//                    public void onError(Exception e) {
//                        Log.d(">>>ERR : ", e + "");
//                    }
//                });
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
