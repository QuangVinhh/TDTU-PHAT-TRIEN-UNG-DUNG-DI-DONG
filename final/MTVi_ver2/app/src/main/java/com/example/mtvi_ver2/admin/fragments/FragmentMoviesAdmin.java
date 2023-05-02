package com.example.mtvi_ver2.admin.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mtvi_ver2.R;
import com.example.mtvi_ver2.admin.adapter.AdapterMoviesAdmin;
import com.example.mtvi_ver2.admin.adapter.AdapterServicesAdmin;
import com.example.mtvi_ver2.database.data.DataMovies;
import com.example.mtvi_ver2.database.data.DataServices;
import com.example.mtvi_ver2.database.sqlite.MoviesDAO;
import com.example.mtvi_ver2.database.sqlite.ServicesDAO;
import com.example.mtvi_ver2.myInterface.InterfaceClickItemMovies;
import com.example.mtvi_ver2.myInterface.InterfaceClickItemServices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentMoviesAdmin extends Fragment {

    ArrayList<DataMovies> dataMovies = new ArrayList<>();
    DataMovies movies = new DataMovies();
    AdapterMoviesAdmin adapterMoviesAdmin;
    RecyclerView FMA_recyclerView;
    Button FMA_button_add;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies_admin, container, false);
        FMA_recyclerView = view.findViewById(R.id.FMA_recyclerView);
        FMA_button_add = view.findViewById(R.id.FMA_button_add);

        /*---data---*/
        dataMovies = MoviesDAO.readMovies(getActivity());

        /*---adapter and recyclerview---*/
        FMA_recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        adapterMoviesAdmin = new AdapterMoviesAdmin(dataMovies, new InterfaceClickItemMovies() {
            @Override
            public void onClickItemMovies(DataMovies movies) {
                showDialogViewMovie(movies);
            }
        });
        FMA_recyclerView.setAdapter(adapterMoviesAdmin);

        /*---event button add movie---*/
        EventButtonAddMovie();

        return view;
    }

    /*=============================================================================================*/
    /*---method || event button add movie---*/
    /*=============================================================================================*/

    private void EventButtonAddMovie() {
        FMA_button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogAddMovie();
            }
        });
    }

    /*=============================================================================================*/
    /*---method || show dialog add movie---*/
    /*=============================================================================================*/

    private void showDialogAddMovie() {
        AlertDialog.Builder builderAddService = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_movies_admin, null);
        builderAddService.setView(view);
        builderAddService.setCancelable(false);
        Dialog dialog = builderAddService.create();
        dialog.show();

        /*---find view by id---*/
        ImageView FMA_addMovie_image = view.findViewById(R.id.FMA_addMovie_image);
        TextView FMA_addMovie_genres = view.findViewById(R.id.FMA_addMovie_genres);
        EditText FMA_addMovie_name = view.findViewById(R.id.FMA_addMovie_name);
        EditText FMA_addMovie_detail = view.findViewById(R.id.FMA_addMovie_detail);
        EditText FMA_addMovie_link = view.findViewById(R.id.FMA_addMovie_link);
        Button FMA_addMovie_add = view.findViewById(R.id.FMA_addMovie_add);
        Button FMA_addMovie_cancel = view.findViewById(R.id.FMA_addMovie_cancel);

        /*---event choose genres movie---*/
        FMA_addMovie_genres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogGenresMovie();
            }

            /*---method || show dialog genres movie---*/
            private void showDialogGenresMovie() {
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
                        FMA_addMovie_genres.setText("");
                        for(int i = 0; i < checkGenres.length; i++){
                            boolean checked = checkGenres[i];

                            if(checked) {
                                FMA_addMovie_genres.setText(FMA_addMovie_genres.getText() + genresList.get(i) + ", ");
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

        /*---event button add movie---*/
        FMA_addMovie_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String FMA_addMovie_image_value = "0";
                String FMA_addMovie_name_value = FMA_addMovie_name.getText().toString();
                String FMA_addMovie_genres_value = FMA_addMovie_genres.getText().toString();
                String FMA_addMovie_detail_value = FMA_addMovie_detail.getText().toString();
                String FMA_addMovie_link_value = FMA_addMovie_link.getText().toString();

                movies.setMovie_image(FMA_addMovie_image_value);
                movies.setMovie_name(FMA_addMovie_name_value);
                movies.setMovie_genres(FMA_addMovie_genres_value);
                movies.setMovie_detail(FMA_addMovie_detail_value);
                movies.setMovie_link(FMA_addMovie_link_value);

                dataMovies.add(movies);

                if(MoviesDAO.insertMovies(getActivity(), movies)){
                    Toast.makeText(getActivity(), "INSERT MOVIE SUCCESSFULLY !!!", Toast.LENGTH_SHORT).show();
                    dataMovies.clear();
                    dataMovies.addAll(MoviesDAO.readMovies(getActivity()));
                    adapterMoviesAdmin.notifyDataSetChanged();
                    dialog.dismiss();
                } else {
                    Toast.makeText(getActivity(), "INSERT SERVICE FAILED !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*---event button cancel movie---*/
        FMA_addMovie_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    /*=============================================================================================*/
    /*---method || show dialog view (edit / delete) movie---*/
    /*=============================================================================================*/

    private void showDialogViewMovie(DataMovies movies) {
        AlertDialog.Builder builderAddService = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_view_movies_admin, null);
        builderAddService.setView(view);
        builderAddService.setCancelable(false);
        Dialog dialog = builderAddService.create();
        dialog.show();

        /*---find view by id---*/
        ImageView FMA_editMovie_image = view.findViewById(R.id.FMA_editMovie_image);
        TextView FMA_editMovie_genres = view.findViewById(R.id.FMA_editMovie_genres);
        EditText FMA_editMovie_name = view.findViewById(R.id.FMA_editMovie_name);
        EditText FMA_editMovie_detail = view.findViewById(R.id.FMA_editMovie_detail);
        EditText FMA_editMovie_link = view.findViewById(R.id.FMA_editMovie_link);
        Button FMA_editMovie_play = view.findViewById(R.id.FMA_editMovie_play);
        Button FMA_editMovie_edit = view.findViewById(R.id.FMA_editMovie_edit);
        Button FMA_editMovie_cancel = view.findViewById(R.id.FMA_editMovie_cancel);
        Button FMA_editMovie_delete = view.findViewById(R.id.FMA_editMovie_delete);

        /*---set data---*/
        FMA_editMovie_image.setImageResource(Integer.parseInt(movies.getMovie_image()));
        FMA_editMovie_name.setText(movies.getMovie_name());
        FMA_editMovie_genres.setText(movies.getMovie_genres());
        FMA_editMovie_detail.setText(movies.getMovie_detail());
        FMA_editMovie_link.setText(movies.getMovie_link());

        /*---event button play---*/
        FMA_editMovie_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(movies.getMovie_link()));
                startActivity(browserIntent);
            }
        });

        /*---event button edit---*/
        FMA_editMovie_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogEditMovies();
            }

            private void showDialogEditMovies() {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("EDIT MOVIE " + "\"" + movies.getMovie_name() + "\"");
                builder.setMessage("Are you sure want to edit movie " + "\"" + movies.getMovie_name() + "\"" + " ?");

                /*---edit---*/
                builder.setPositiveButton("EDIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        movies.setMovie_image("0");
                        movies.setMovie_name(FMA_editMovie_name.getText().toString());
                        movies.setMovie_genres(FMA_editMovie_genres.getText().toString());
                        movies.setMovie_detail(FMA_editMovie_detail.getText().toString());
                        movies.setMovie_link(FMA_editMovie_link.getText().toString());

                        if(MoviesDAO.updateMovies(getActivity(), movies)){
                            Toast.makeText(getActivity(), "UPDATE MOVIE SUCCESSFULLY !!!", Toast.LENGTH_SHORT).show();
                            dataMovies.clear();
                            dataMovies.addAll(MoviesDAO.readMovies(getActivity()));
                            adapterMoviesAdmin.notifyDataSetChanged();
                            dialog.dismiss();
                        } else{
                            Toast.makeText(getActivity(), "UPDATE MOVIE FAILED !!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                /*---cancel---*/
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });

        /*---event button delete---*/
        FMA_editMovie_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogDeleteMovie();
            }

            private void showDialogDeleteMovie() {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("DELETE SERVICE " + "\"" + movies.getMovie_name() + "\"");
                builder.setMessage("Are you sure want to delete service " + "\"" + movies.getMovie_name() + "\"" + " ?");

                /*---delete---*/
                builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(MoviesDAO.deleteMovies(getActivity(), movies.getMovie_id())){
                            Toast.makeText(getActivity(), "DELETE MOVIE SUCCESSFULLY !!!", Toast.LENGTH_SHORT).show();
                            dataMovies.clear();
                            dataMovies.addAll(MoviesDAO.readMovies(getActivity()));
                            adapterMoviesAdmin.notifyDataSetChanged();
                            dialog.dismiss();
                        } else{
                            Toast.makeText(getActivity(), "DELETE MOVIE FAILED !!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                /*---cancel---*/
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });

        /*---event button cancel---*/
        FMA_editMovie_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        /*---event choose genres---*/
        FMA_editMovie_genres.setOnClickListener(new View.OnClickListener() {
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
                        FMA_editMovie_genres.setText("");
                        for(int i = 0; i < checkGenres.length; i++){
                            boolean checked = checkGenres[i];

                            if(checked) {
                                FMA_editMovie_genres.setText(FMA_editMovie_genres.getText() + genresList.get(i) + ", ");
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