package com.example.mtvi_ver2.user.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.example.mtvi_ver2.R;
import com.example.mtvi_ver2.database.data.DataMovies;
import com.example.mtvi_ver2.database.sqlite.MoviesDAO;
import com.example.mtvi_ver2.myInterface.InterfaceClickItemMovies;
import com.example.mtvi_ver2.user.adapters.AdapterMoviesUser;


import java.util.ArrayList;

public class FragmentMoviesUser extends Fragment {

    ArrayList<DataMovies> dataMovies = new ArrayList<>();

    AdapterMoviesUser adapterMoviesUser;
    RecyclerView FMU_recyclerView;

    SearchView searchView;

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
        adapterMoviesUser = new AdapterMoviesUser(getActivity(), dataMovies, new InterfaceClickItemMovies() {
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
        GlideUrl url = new GlideUrl(movie_image, new LazyHeaders.Builder()
                .addHeader("User-Agent", WebSettings.getDefaultUserAgent(getActivity()))
                .build());

        Glide.with(getActivity())
                .load(url)
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

    /*=============================================================================================*/
    /*---method || menu search---*/
    /*=============================================================================================*/

    @Override
    public void onCreate(@NonNull Bundle saveInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(saveInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_user_movies, menu);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(getActivity().SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapterMoviesUser.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterMoviesUser.getFilter().filter(newText);
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.avatar:
                showViewAccountUser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*=============================================================================================*/
    /*---method || show view account user---*/
    /*=============================================================================================*/

    private void showViewAccountUser() {
        AlertDialog.Builder builderViewMovie = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_view_accounts_user, null);
        builderViewMovie.setView(view);
//        builderViewMovie.setCancelable(false);
        Dialog dialog = builderViewMovie.create();
        dialog.show();

    }
}