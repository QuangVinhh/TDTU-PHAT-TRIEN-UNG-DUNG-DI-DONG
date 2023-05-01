package com.example.mtvi.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.mtvi.R;
import com.example.mtvi.admin.adapters.AdapterFragmentAdmin;
import com.google.android.material.tabs.TabLayout;

public class MainActivityAdmin extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        /*---find view by id---*/
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        /*---fragment adapter admin---*/
        AdapterFragmentAdmin adapterFragmentAdmin = new AdapterFragmentAdmin(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapterFragmentAdmin);
        tabLayout.setupWithViewPager(viewPager);
    }
}