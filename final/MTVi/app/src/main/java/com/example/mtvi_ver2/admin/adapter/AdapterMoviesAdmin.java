package com.example.mtvi_ver2.admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.Filter;
import android.widget.Filterable;
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

public class AdapterMoviesAdmin extends RecyclerView.Adapter<AdapterMoviesAdmin.MoviesAdminViewHolder> implements Filterable {
    ArrayList<DataMovies> mDataMovies;
    ArrayList<DataMovies> mDataMoviesOld;
    InterfaceClickItemMovies interfaceClickItemMovies;
    Context mContext;

    public AdapterMoviesAdmin(Context mContext, ArrayList<DataMovies> mDataMovies, InterfaceClickItemMovies mListener) {
        this.mContext = mContext;
        this.mDataMovies = mDataMovies;
        this.interfaceClickItemMovies = mListener;
        this.mDataMoviesOld = mDataMovies;
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

    /*---view holder---*/
    public class MoviesAdminViewHolder extends RecyclerView.ViewHolder {

        CardView FMA_adapter_cardView;
        ImageView FMA_adapter_imageView;

        public MoviesAdminViewHolder(@NonNull View itemView) {
            super(itemView);

            FMA_adapter_cardView = itemView.findViewById(R.id.FMA_adapter_cardView);
            FMA_adapter_imageView = itemView.findViewById(R.id.FMA_adapter_imageView);

        }

    }

    /*---search---*/
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if(strSearch.isEmpty()){
                    mDataMovies = mDataMoviesOld;
                } else {
                    ArrayList<DataMovies> listMoviesSearch = new ArrayList<>();
                    for(DataMovies movies : mDataMoviesOld){
                        if(movies.getMovie_name().toLowerCase().contains(strSearch.toLowerCase())){
                            listMoviesSearch.add(movies);
                        }
                    }

                    mDataMovies = listMoviesSearch;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mDataMovies;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mDataMovies = (ArrayList<DataMovies>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
