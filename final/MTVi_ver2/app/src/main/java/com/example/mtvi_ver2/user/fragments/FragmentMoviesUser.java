package com.example.mtvi_ver2.user.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mtvi_ver2.R;
import com.example.mtvi_ver2.admin.adapter.AdapterMoviesAdmin;
import com.example.mtvi_ver2.database.data.DataMovies;
import com.example.mtvi_ver2.database.sqlite.MoviesDAO;
import com.example.mtvi_ver2.myInterface.InterfaceClickItemMovies;
import com.example.mtvi_ver2.user.adapters.AdapterMoviesUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FragmentMoviesUser extends Fragment {

    ArrayList<DataMovies> dataMovies = new ArrayList<>();

    AdapterMoviesUser adapterMoviesUser;
    RecyclerView FMU_recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies_user, container, false);
        FMU_recyclerView = view.findViewById(R.id.FMU_recyclerView);

        /*---data---*/
        dataMovies = MoviesDAO.readMovies(getActivity());

        /*---adapter and recyclerview---*/
        FMU_recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        adapterMoviesUser = new AdapterMoviesUser(dataMovies, new InterfaceClickItemMovies() {
            @Override
            public void onClickItemMovies(DataMovies movies) {
                showDialogViewMovie(movies);
            }
        });
        FMU_recyclerView.setAdapter(adapterMoviesUser);

        return view;
    }

    /*=============================================================================================*/
    /*---method || show dialog view movie---*/
    /*=============================================================================================*/
    private void showDialogViewMovie(DataMovies movies) {
        AlertDialog.Builder builderViewMovie = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_view_movies_user, null);
        builderViewMovie.setView(view);
        builderViewMovie.setCancelable(false);
        Dialog dialog = builderViewMovie.create();
        dialog.show();

        /*---find view by id---*/
        ImageView FMU_editMovie_image = view.findViewById(R.id.FMU_editMovie_image);
        TextView FMU_editMovie_name = view.findViewById(R.id.FMU_editMovie_name);
        TextView FMU_editMovie_genres = view.findViewById(R.id.FMU_editMovie_genres);
        TextView FMU_editMovie_detail = view.findViewById(R.id.FMU_editMovie_detail);
        Button FMU_editMovie_play = view.findViewById(R.id.FMU_editMovie_play);
        Button FMU_editMovie_close = view.findViewById(R.id.FMU_editMovie_close);

        /*---set data---*/
        String movie_image = movies.getMovie_image();
        Picasso.get()
                .load(movie_image)
                .placeholder(R.drawable.baseline_hide_image_24)
                .into(FMU_editMovie_image);
        FMU_editMovie_name.setText(movies.getMovie_name());
        FMU_editMovie_genres.setText(movies.getMovie_genres());
        FMU_editMovie_detail.setText(movies.getMovie_detail());

        /*---event button play---*/
        FMU_editMovie_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(movies.getMovie_link()));
                startActivity(browserIntent);
            }
        });

        /*---event button play---*/
        FMU_editMovie_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}