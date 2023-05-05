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

import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import com.example.mtvi_ver2.main.MainActivity;
import com.example.mtvi_ver2.myInterface.InterfaceClickItemMovies;
import com.example.mtvi_ver2.user.adapters.AdapterHomeUser;


import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FragmentHomeUser extends Fragment {

    Random random = new Random();
    ArrayList<DataMovies> dataMovies = new ArrayList<>();
    ArrayList<DataAccounts> dataAccounts = new ArrayList<>();

    ArrayList<DataAccounts> dataAccountsTemp = new ArrayList<>();
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
        Intent intent = getActivity().getIntent();

        String get_intent_email = intent.getStringExtra("account_email");
        dataAccountsTemp = AccountsDAO.getAccountByEmail(getActivity(), get_intent_email);

        String name_value = String.valueOf(dataAccountsTemp.get(0).getAccount_name());
        String password_value = dataAccountsTemp.get(0).getAccount_password();
        int id_value = dataAccountsTemp.get(0).getAccount_id();
        String check_value = dataAccountsTemp.get(0).getAccount_check();

        VAU_editText_name.setText(name_value);
        VAU_textView_email.setText(get_intent_email);

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
                    String account_name = VAU_editText_name.getText().toString();
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("LOGOUT THIS ACCOUNT");
                    builder.setMessage("Are you sure want to logout, " + "\"" + account_name + "\"" + " ?");

                    /*---logout---*/
                    builder.setPositiveButton("LOGOUT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            dialogInterface.dismiss();
                            getActivity().finish();
                            startActivity(intent);
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

                            if(TextUtils.isEmpty(account_name)){
                                VAU_editText_name.requestFocus();
                                VAU_editText_name.setError("Please enter your name !");
                            } else{
                                VAU_editText_name.setError(null);

                                accounts = new DataAccounts(id_value, account_name, get_intent_email, password_value, check_value);

                                if(AccountsDAO.updateAccounts(getActivity(), accounts)){
                                    Toast.makeText(getActivity(), "UPDATE ACCOUNT SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                                    dataAccounts.clear();
                                    dataAccountsTemp.clear();
                                    dataAccounts.addAll(AccountsDAO.readAccounts(getActivity()));
                                    dataAccountsTemp.addAll(AccountsDAO.readAccounts(getActivity()));
                                    dialog.dismiss();
                                } else{
                                    Toast.makeText(getActivity(), "UPDATE ACCOUNT FAILED", Toast.LENGTH_SHORT).show();
                                }
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

            /*---event button change password---*/
            VAU_button_changePassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builderViewMovie = new AlertDialog.Builder(getActivity());
                    View viewChangePassword = inflater.inflate(R.layout.dialog_view_change_password, null);
                    builderViewMovie.setView(viewChangePassword);
                    builderViewMovie.setCancelable(false);
                    Dialog dialog = builderViewMovie.create();
                    dialog.show();

                    /*---find id---*/
                    EditText FHU_current_password = viewChangePassword.findViewById(R.id.FHU_current_password);
                    EditText FHU_new_password = viewChangePassword.findViewById(R.id.FHU_new_password);
                    CheckBox FHU_show_password = viewChangePassword.findViewById(R.id.FHU_show_password);
                    CheckBox FHU_agree_change = viewChangePassword.findViewById(R.id.FHU_agree_change);
                    Button FHU_changePassword_change = viewChangePassword.findViewById(R.id.FHU_changePassword_change);
                    Button FHU_changePassword_cancel = viewChangePassword.findViewById(R.id.FHU_changePassword_cancel);

                    /*------*/
                    FHU_show_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if(b) {
                                FHU_current_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                                FHU_new_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            } else {
                                FHU_current_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                                FHU_new_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            }
                        }
                    });

                    /*------*/
                    FHU_changePassword_cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    /*------*/
                    FHU_changePassword_change.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String FHU_current_password_value = FHU_current_password.getText().toString();
                            String FHU_new_password_value = FHU_new_password.getText().toString();

                            if (TextUtils.isEmpty(FHU_current_password_value)) {
                                FHU_current_password.requestFocus();
                                FHU_current_password.setError("Please enter your current password !");
                            } else if (TextUtils.isEmpty(FHU_new_password_value)) {
                                FHU_new_password.requestFocus();
                                FHU_new_password.setError("Please enter your new password !");
                            } else if(!CheckIsValidPassword(FHU_new_password_value)) {
                                FHU_new_password.requestFocus();
                                FHU_new_password.setError("Your password much have : \n+ 8 or more characters \n+ Upper and lowercase letters \n+ At least one number \n+ No white spaces");
                            } else if(FHU_agree_change.isChecked() == false){
                                FHU_agree_change.requestFocus();
                                FHU_agree_change.setError("Please confirm our conditions !");
                            } else if (FHU_current_password_value.equals(password_value)) {

                                accounts = new DataAccounts(id_value, name_value, get_intent_email, FHU_new_password_value, check_value);

                                if(AccountsDAO.updateAccounts(getActivity(), accounts)){
                                    Toast.makeText(getActivity(), "UPDATE ACCOUNT SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                                    dataAccounts.clear();
                                    dataAccountsTemp.clear();
                                    dataAccounts.addAll(AccountsDAO.readAccounts(getActivity()));
                                    dataAccountsTemp.addAll(AccountsDAO.readAccounts(getActivity()));
                                    getActivity().finish();
                                } else{
                                    Toast.makeText(getActivity(), "UPDATE ACCOUNT FAILED", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                FHU_current_password.requestFocus();
                                FHU_current_password.setError("Your current password is not correct !");

                                FHU_agree_change.setError(null);
                                FHU_new_password.setError(null);
                            }
                        }
                    });

                }
            });

            /*---event button service---*/

    }

    /*=============================================================================================*/
    /*---method || check valid password---*/
    /*=============================================================================================*/

    private boolean CheckIsValidPassword(String password) {
        Pattern pattern;
        Matcher matcher;

        String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }
}