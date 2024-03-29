package com.example.mtvi_ver2.user.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.mtvi_ver2.user.fragments.FragmentHomeUser;
import com.example.mtvi_ver2.user.fragments.FragmentMoviesUser;
import com.example.mtvi_ver2.user.fragments.FragmentServicesUser;

public class AdapterFragmentUser extends FragmentStatePagerAdapter {

    public AdapterFragmentUser(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentHomeUser();
            case 1:
                return new FragmentMoviesUser();
            case 2:
                return new FragmentServicesUser();
            default:
                return new FragmentHomeUser();
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
                title = "Home";
                break;
            case 1:
                title = "Moives";
                break;
            case 2:
                title = "Services";
                break;
        }
        return title;
    }
}
