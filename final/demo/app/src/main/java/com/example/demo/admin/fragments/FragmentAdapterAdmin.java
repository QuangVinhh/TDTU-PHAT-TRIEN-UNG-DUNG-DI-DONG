package com.example.demo.admin.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FragmentAdapterAdmin extends FragmentStatePagerAdapter {

    public FragmentAdapterAdmin(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 :
                return new FragmentMoviesAdmin();
            case 1 :
                return new FragmentServicesAdmin();
            case 2 :
                return new FragmentAccountsAdmin();
            default :
                return new FragmentMoviesAdmin();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0 :
                title = "Movies";
                break;
            case 1 :
                title = "Service";
                break;
            case 2 :
                title = "Account";
                break;
        }
        return title;
    }
}
