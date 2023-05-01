package com.example.demo.user.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FragmentAdapter extends FragmentStatePagerAdapter {

    public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 :
                return new FragmentHome();
            case 1 :
                return new FragmentMovies();
            case 2 :
                return new FragmentKids();
            case 3 :
                return new FragmentMyList();
            default :
                return new FragmentHome();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0 :
                title = "Home";
                break;
            case 1 :
                title = "Movies";
                break;
            case 2 :
                title = "Kids";
                break;
            case 3 :
                title = "My list";
                break;
        }
        return title;
    }
}
