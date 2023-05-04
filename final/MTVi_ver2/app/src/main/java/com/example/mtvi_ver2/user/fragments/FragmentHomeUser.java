package com.example.mtvi_ver2.user.fragments;

import android.app.AlertDialog;
import android.app.Dialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

import com.example.mtvi_ver2.R;
import com.example.mtvi_ver2.database.data.DataAccounts;
import com.example.mtvi_ver2.database.data.DataMovies;
import com.example.mtvi_ver2.database.sqlite.AccountsDAO;
import com.example.mtvi_ver2.database.sqlite.MoviesDAO;
import com.example.mtvi_ver2.myInterface.InterfaceClickItemMovies;
import com.example.mtvi_ver2.user.adapters.AdapterHomeUser;


import java.util.ArrayList;
import java.util.Random;

public class FragmentHomeUser extends Fragment {

    Random random = new Random();
    ArrayList<DataMovies> dataMovies = new ArrayList<>();
    ArrayList<DataAccounts> dataAccounts = new ArrayList<>();
    DataAccounts accounts;
    AdapterHomeUser adapterHomeUser;
    RecyclerView FHU_recyclerView;
    ImageView FHU_viewUser_image;
    TextView FHU_viewUser_name;
    Button FHU_viewUser_play, FHU_viewUser_detail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_user, container, false);
        FHU_recyclerView = view.findViewById(R.id.FHU_recyclerView);
        FHU_viewUser_image = view.findViewById(R.id.FHU_viewUser_image);
        FHU_viewUser_name = view.findViewById(R.id.FHU_viewUser_name);
        FHU_viewUser_play = view.findViewById(R.id.FHU_viewUser_play);
        FHU_viewUser_detail = view.findViewById(R.id.FHU_viewUser_detail);

        /*---data---*/
        dataMovies = MoviesDAO.readMovies(getActivity());

        /*---adapter and recyclerview---*/
        FHU_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapterHomeUser = new AdapterHomeUser(getActivity(), dataMovies, new InterfaceClickItemMovies() {
            @Override
            public void onClickItemMovies(DataMovies movies) {
                showDialogViewMovie(movies);
            }
        });
        FHU_recyclerView.setAdapter(adapterHomeUser);

        int movieRandom = random.nextInt( dataMovies.size() - 1) + 1;
        DataMovies moviesRandom = dataMovies.get(movieRandom);

        /*---set random movie banner---*/
        showViewMovieBanner(moviesRandom);

        /*---event button play---*/
        EventButtonPlay(moviesRandom);

        /*---event button detail---*/
        EventButtonDetail(moviesRandom);

        return view;
    }

    /*=============================================================================================*/
    /*---method || event button detail---*/
    /*=============================================================================================*/
    private void EventButtonDetail(DataMovies moviesRandom) {
        FHU_viewUser_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogViewMovie(moviesRandom);
            }
        });
    }

    /*=============================================================================================*/
    /*---method || event button play---*/
    /*=============================================================================================*/
    private void EventButtonPlay(DataMovies moviesRandom) {
        FHU_viewUser_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(moviesRandom.getMovie_link()));
                startActivity(browserIntent);
            }
        });
    }

    /*=============================================================================================*/
    /*---method || show view movie banner---*/
    /*=============================================================================================*/
    private void showViewMovieBanner(DataMovies moviesRandom) {

        /*---set image---*/
        String movie_image = moviesRandom.getMovie_image();

        GlideUrl url = new GlideUrl(movie_image, new LazyHeaders.Builder()
                .addHeader("User-Agent", WebSettings.getDefaultUserAgent(getActivity()))
                .build());

        Glide.with(getActivity())
                .load(url)
                .into(FHU_viewUser_image);

        /*---set name---*/
        FHU_viewUser_name.setText(moviesRandom.getMovie_name());

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
    /*---method || menu home---*/
    /*=============================================================================================*/
    @Override
    public void onCreate(@NonNull Bundle saveInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(saveInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_user_home, menu);

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
        builderViewMovie.setCancelable(false);
        Dialog dialog = builderViewMovie.create();
        dialog.show();

        /*---find view by id---*/
        EditText VAU_editText_name = view.findViewById(R.id.VAU_editText_name);
        TextView VAU_textView_email = view.findViewById(R.id.VAU_textView_email);
        TextView VAU_textView_nameService = view.findViewById(R.id.VAU_textView_nameService);
        TextView VAU_textView_priceService = view.findViewById(R.id.VAU_textView_priceService);
        Button VAU_button_edit = view.findViewById(R.id.VAU_button_edit);
        Button VAU_button_changePassword = view.findViewById(R.id.VAU_button_changePassword);
        Button VAU_button_changeService = view.findViewById(R.id.VAU_button_changeService);
        Button VAU_button_close = view.findViewById(R.id.VAU_button_close);
        Button VAU_button_logout = view.findViewById(R.id.VAU_button_logout);

        /*---set data---*/
//        VAU_editText_name.setText(accounts.getAccount_name());
//        VAU_textView_email.setText(accounts.getAccount_name());
//        VAU_textView_nameService.setText(accounts.getAccount_name());
//        VAU_textView_priceService.setText(accounts.getAccount_name());

        /*=============================================================================================*/
        /*---method || event button---*/
        /*=============================================================================================*/

            /*---event button close---*/
            VAU_button_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            /*---event button logout---*/
            VAU_button_logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("LOGOUT THIS ACCOUNT");
                    builder.setMessage("Are you sure want to logout, " + "\"" + accounts.getAccount_name() + "\"" + " ?");

                    /*---edit---*/
                    builder.setPositiveButton("LOGOUT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            /*---intent---*/
                            dialog.dismiss();
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

            /*---event button edit---*/
            VAU_button_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String account_name = VAU_editText_name.getText().toString();

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("EDIT NAME ACCOUNT");
                    builder.setMessage("Are you sure want to edit account's name to " + "\"" + account_name + "\"" + " ?");

                    /*---edit---*/
                    builder.setPositiveButton("EDIT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            accounts.setAccount_name(account_name);

                            if(AccountsDAO.updateAccounts(getActivity(), accounts)){
                                Toast.makeText(getActivity(), "UPDATE ACCOUNT SUCCESSFULLY !!!", Toast.LENGTH_SHORT).show();
                                dataAccounts.clear();
                                dataAccounts.addAll(AccountsDAO.readAccounts(getActivity()));
                                dialogInterface.dismiss();
                            } else{
                                Toast.makeText(getActivity(), "UPDATE MOVIE FAILED !!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    /*---cancel---*/
                    builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.create().show();
                }
            });

            /*---event button password---*/

            /*---event button service---*/

    }



}