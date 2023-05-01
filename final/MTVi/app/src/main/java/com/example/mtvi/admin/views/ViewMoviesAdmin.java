package com.example.mtvi.admin.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mtvi.R;
import com.example.mtvi.admin.adapters.AdapterMovies;
import com.example.mtvi.admin.fragments.FragmentMoviesAdmin;
import com.example.mtvi.database.DataMovies;
import com.example.mtvi.database.MoviesDAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewMoviesAdmin extends AppCompatActivity {
    List<DataMovies> dataMovies = new ArrayList<>();
    AdapterMovies adapterMovies;
    ImageView imageView_movies;
    TextView textView_name, textView_genres, textView_description;
    Button button_play_movies, button_edit_movies, button_delete_movies;
    LinearLayout view_movies_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_movies_admin);

        /*---find view by id---*/
        view_movies_admin = findViewById(R.id.view_movies_admin);
        imageView_movies = findViewById(R.id.imageView_movies);
        textView_name = findViewById(R.id.textView_name);
        textView_genres = findViewById(R.id.textView_genres);
        textView_description = findViewById(R.id.textView_description);
        button_play_movies = findViewById(R.id.button_play_movies);
        button_edit_movies = findViewById(R.id.button_edit_movies);
        button_delete_movies = findViewById(R.id.button_delete_movies);

        /*---event receive data movies---*/
        Intent intent = getIntent();

        int movie_id = intent.getExtras().getInt("movie_id");
        int movie_image = intent.getExtras().getInt("movie_image");
        String movie_name = intent.getExtras().getString("movie_name");
        String movie_genres = intent.getExtras().getString("movie_genres");
        String movie_description = intent.getExtras().getString("movie_description");
        String movie_linked = intent.getExtras().getString("movie_linked");

        imageView_movies.setImageResource(movie_image);
        textView_name.setText(movie_name);
        textView_genres.setText(movie_genres);
        textView_description.setText(movie_description);

        /*---adapter movies---*/
        adapterMovies = new AdapterMovies(ViewMoviesAdmin.this, dataMovies);

        /*---event button play movies---*/
        EventButtonPlayMovies(movie_linked);

        /*---event button delete movies*/
        EventButtonDeleteMovies(movie_id);

        /*---event button edit movies*/
        EventButtonEditMovies(movie_id);
    }

    ///*---method || event button play movies---*///
    private void EventButtonPlayMovies(String movie_linked) {
        button_play_movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(movie_linked));
                startActivity(browserIntent);
            }
        });
    }

    ///*---method || event button edit movies---*///
    private void EventButtonEditMovies(int movie_id) {
        button_edit_movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogEdit();
            }

        });
    }

    ///*---method || event button delete movies---*///
    private void EventButtonDeleteMovies(int movie_id) {
        button_delete_movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builderDelete = new AlertDialog.Builder(ViewMoviesAdmin.this);
                builderDelete.setTitle("WARNING !!!");
                builderDelete.setIcon(R.drawable.baseline_warning_24);
                builderDelete.setMessage("\n Are you sure want to delete this movie ?");
                builderDelete.setCancelable(false);

                /*---delete---*/
                builderDelete.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                        if(MoviesDAO.delete(getApplicationContext(), movie_id)){
                            Toast.makeText(getApplicationContext(), "DELETE MOVIE SUCCESSFULLY !!!", Toast.LENGTH_SHORT).show();
                            dataMovies.clear();
                            dataMovies.addAll(MoviesDAO.getAll(FragmentMoviesAdmin.this));
                            adapterMovies.notifyDataSetChanged();
                            dialog.dismiss();
                            view_movies_admin.setVisibility(View.GONE);
                            fragmentTransaction.replace(R.id.view_movies_admin, new FragmentMoviesAdmin()).commit();

                        } else {
                            Toast.makeText(ViewMoviesAdmin.this, "DELETE MOVIE FAILED !!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                /*---cancel---*/
                builderDelete.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                /*---create---*/
                AlertDialog dialog = builderDelete.create();
                dialog.show();
            }

        });
    }

    ///*---method || dialog edit movies---*///
    private void showDialogEdit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ViewMoviesAdmin.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_edit_movies, null);
        builder.setView(view);
        builder.setCancelable(false);
        Dialog dialog = builder.create();
        dialog.show();

        /*---find view by id---*/
        ImageView imageView_movies_edit = view.findViewById(R.id.imageView_movies_edit);
        TextView textView_genres_edit = view.findViewById(R.id.textView_genres_edit);
        EditText editText_name_edit = view.findViewById(R.id.editText_name_edit);
        EditText editText_description_edit = view.findViewById(R.id.editText_description_edit);
        EditText editText_linked_edit = view.findViewById(R.id.editText_linked_edit);
        Button button_choose_genres_edit = view.findViewById(R.id.button_choose_genres_edit);
        Button button_submit_edit = view.findViewById(R.id.button_submit_edit);
        Button button_cancel_edit = view.findViewById(R.id.button_cancel_edit);

        /*---receive data---*/
        Intent intent = getIntent();

        int movie_image_edit = intent.getExtras().getInt("movie_image");
        String movie_name_edit = intent.getExtras().getString("movie_name");
        String movie_genres_edit = intent.getExtras().getString("movie_genres");
        String movie_description_edit = intent.getExtras().getString("movie_description");
        String movie_linked_edit = intent.getExtras().getString("movie_linked");

        imageView_movies_edit.setImageResource(movie_image_edit);
        editText_name_edit.setText(movie_name_edit);
        textView_genres_edit.setText(movie_genres_edit);
        editText_description_edit.setText(movie_description_edit);
        editText_linked_edit.setText(movie_linked_edit);

        /*---event button choose genres---*/
        button_choose_genres_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builderGenres = new AlertDialog.Builder(ViewMoviesAdmin.this, R.style.AlertDialogTheme);
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
                        textView_genres_edit.setText("");
                        for(int i = 0; i < checkGenres.length; i++){
                            boolean checked = checkGenres[i];

                            if(checked) {
                                textView_genres_edit.setText(textView_genres_edit.getText() + genresList.get(i) + ", ");
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

        /*---event button cancel---*/
        button_cancel_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        /*---event button submit---*/
//        button_submit_edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent intent = getIntent();
////                int movie_id = intent.getExtras().getInt("movie_id");
//                dataMovies.getId()
//
//                movies.setMovie_image(imageView_movies_edit.getImageAlpha());
//                movies.setMovie_name(editText_name_edit.getText().toString());
//                movies.setMovie_genres(textView_genres_edit.getText().toString());
//                movies.setMovie_description(editText_description_edit.getText().toString());
//                movies.setMovie_linked(editText_linked_edit.getText().toString());;
//
//                if(MoviesDAO.update(ViewMoviesAdmin.this, movies)){
//                    Toast.makeText(ViewMoviesAdmin.this, "UPDATE MOVIE SUCCESSFULLY !!!", Toast.LENGTH_SHORT).show();
//                    dataMovies.clear();
//                    dataMovies.addAll(MoviesDAO.getAll(ViewMoviesAdmin.this));
////                    adapterMovies.notifyDataSetChanged();
//                    dialog.dismiss();
//                } else {
//                    Toast.makeText(ViewMoviesAdmin.this, "UPDATE MOVIE FAILED !!!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

    }
}