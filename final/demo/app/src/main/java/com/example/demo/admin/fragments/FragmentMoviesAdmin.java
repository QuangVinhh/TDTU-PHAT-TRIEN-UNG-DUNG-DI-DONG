package com.example.demo.admin.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demo.R;
import com.example.demo.admin.adapter.AdapterMovies;
import com.example.demo.database.DataMovies;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentMoviesAdmin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMoviesAdmin extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentMoviesAdmin() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentMoviesAdmin.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentMoviesAdmin newInstance(String param1, String param2) {
        FragmentMoviesAdmin fragment = new FragmentMoviesAdmin();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    List<DataMovies> dataMovies;
    AdapterMovies adapterMovies;
    RecyclerView recyclerView_movies;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies_admin, container, false);
        recyclerView_movies = view.findViewById(R.id.recyclerView_movies);

        /*---data movie---*/
        dataMovies = new ArrayList<>();
        dataMovies.add(new DataMovies("Stand By Me I", "Anime, Kid", "Doraemon", R.mipmap.image_movie_01_foreground));
        dataMovies.add(new DataMovies("Stand By Me II", "Anime, Kid", "Doraemon", R.mipmap.image_movie_02_foreground));
        dataMovies.add(new DataMovies("Harry Potter and the Sorcerer's Stone", "Adventure, Family", "Harry Potter", R.mipmap.image_movie_03_foreground));
        dataMovies.add(new DataMovies("Harry Potter and the Chamber of Secrets", "Adventure, Family", "Harry Potter", R.mipmap.image_movie_04_foreground));

        /*---set adapter---*/
        adapterMovies = new AdapterMovies(getActivity(), dataMovies);
        recyclerView_movies.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView_movies.setAdapter(adapterMovies);
        return view;
    }
}