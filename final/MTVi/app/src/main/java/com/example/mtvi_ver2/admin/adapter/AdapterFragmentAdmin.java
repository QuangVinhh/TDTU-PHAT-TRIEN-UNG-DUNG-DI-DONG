package com.example.mtvi_ver2.admin.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.mtvi_ver2.admin.fragments.FragmentAccountsAdmin;
import com.example.mtvi_ver2.admin.fragments.FragmentMoviesAdmin;
import com.example.mtvi_ver2.admin.fragments.FragmentServicesAdmin;

public class AdapterFragmentAdmin extends FragmentStatePagerAdapter {

    public AdapterFragmentAdmin(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentMoviesAdmin();
            case 1:
                return new FragmentServicesAdmin();
            case 2:
                return new FragmentAccountsAdmin();
            default:
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
        switch (position){
            case 0:
                title = "Movies";
                break;
            case 1:
                title = "Services";
                break;
            case 2:
                title = "Accounts";
                break;
        }
        return title;
    }
}
