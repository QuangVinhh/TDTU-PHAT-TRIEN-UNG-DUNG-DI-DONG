package com.example.mtvi.admin.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mtvi.R;
import com.example.mtvi.admin.adapters.AdapterMovies;
import com.example.mtvi.database.DBHelper;
import com.example.mtvi.database.DataMovies;
import com.example.mtvi.database.MoviesDAO;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentMoviesAdmin extends Fragment {

    /*---main---*/
    List<DataMovies> dataMovies;
    AdapterMovies adapterMovies;
    RecyclerView recyclerView_movies;
    Button button_add_movies;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies_admin, container, false);
        recyclerView_movies = view.findViewById(R.id.recyclerView_movies);
        button_add_movies = view.findViewById(R.id.button_add_movies);

        /*---data movies---*/
        dataMovies = new ArrayList<>();
        dataMovies = MoviesDAO.getAll(getActivity());

        /*---adapter movies---*/
        adapterMovies = new AdapterMovies(getActivity(), dataMovies);
        recyclerView_movies.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView_movies.setAdapter(adapterMovies);

        /*---event button add movies---*/
        EventButtonAddMovies();

        return view;
    }

    ///*---method || event button add movies---*///
    private void EventButtonAddMovies() {
        button_add_movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogAdd();
            }
        });
    }

    ///*---method || event show dialog button add movies---*///
    private void showDialogAdd(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_movies, null);
        builder.setView(view);
        builder.setCancelable(false);
        Dialog dialog = builder.create();
        dialog.show();

        /*---find view by id---*/
        TextView textView_genres = view.findViewById(R.id.textView_genres);
        EditText editText_name = view.findViewById(R.id.editText_name);
        EditText editText_description = view.findViewById(R.id.editText_description);
        EditText editText_linked = view.findViewById(R.id.editText_linked);
        Button button_add = view.findViewById(R.id.button_add);
        Button button_cancel = view.findViewById(R.id.button_cancel);
        Button button_choose_genres = view.findViewById(R.id.button_choose_genres);

        /*---event button cancel---*/
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        /*---event button add movie---*/
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_value = editText_name.getText().toString();
                String genres_value = textView_genres.getText().toString();
                String description_value = editText_description.getText().toString();
                String linked_value = editText_linked.getText().toString();

                if(MoviesDAO.insert(getActivity(), name_value, genres_value, description_value, 0, linked_value)){
                    Toast.makeText(getActivity(), "ADD NEW MOVIE SUCCESSFULLY !!!", Toast.LENGTH_SHORT).show();
                    dataMovies.clear();
                    dataMovies.addAll(MoviesDAO.getAll(getActivity()));
                    adapterMovies.notifyDataSetChanged();
                    dialog.dismiss();
                } else {
                    Toast.makeText(getActivity(), "ADD NEW MOVIE FAILED !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*---event button choose genres---*/
        button_choose_genres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builderGenres = new AlertDialog.Builder(getActivity(), R.style.AlertDialogTheme);
                builderGenres.setTitle("CHOOSE THE GENRES OF MOVIE");
                builderGenres.setIcon(R.drawable.baseline_movie_filter_24);
                builderGenres.setCancelable(false);
                /*---list value genres---*/
                String[] genres = new String[]{
                        "Action",
                        "Anime",
                        "Comedies",
                        "Crime",
                        "Document",
                        "Dramas",
                        "Family",
                        "Horror",
                        "Kids",
                        "Romance",
                        "Science",
                        "Sci-Fi and Fantasy"
                };

                /*---boolean select genres---*/
                final boolean[] checkGenres = new boolean[]{
                        false,
                        false,
                        false,
                        false,
                        false,
                        false,
                        false,
                        false,
                        false,
                        false,
                        false,
                        false
                };

                /*---set list checked genres---*/
                final List<String> genresList = Arrays.asList(genres);

                /*---multichoice---*/
                builderGenres.setMultiChoiceItems(genres, checkGenres, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        checkGenres[i] = b;
                    }
                });

                /*---set submit---*/
                builderGenres.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        for(int i = 0; i < checkGenres.length; i++){
                            boolean checked = checkGenres[i];

                            if(checked) {
                                textView_genres.setText(textView_genres.getText() + genresList.get(i) + ", ");
                            }
                        }
                    }
                });

                /*---set cancel---*/
                builderGenres.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog dialogGenres = builderGenres.create();
                dialogGenres.show();
            }
        });

    }
}

//        dataMovies.add(new DataMovies("Stand By Me I", "Anime, Kid", "Doraemon", R.mipmap.image_movie_01_foreground));
//        dataMovies.add(new DataMovies("Stand By Me II", "Anime, Kid", "Doraemon", R.mipmap.image_movie_02_foreground));
//        dataMovies.add(new DataMovies("Harry Potter and the Sorcerer's Stone", "Adventure, Family", "Harry Potter", R.mipmap.image_movie_03_foreground));
//        dataMovies.add(new DataMovies("Harry Potter and the Chamber of Secrets", "Adventure, Family", "Harry Potter", R.mipmap.image_movie_04_foreground));
