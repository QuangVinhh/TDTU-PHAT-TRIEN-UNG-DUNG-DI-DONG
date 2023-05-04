package com.example.mtvi_ver2.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.mtvi_ver2.R;
import com.example.mtvi_ver2.admin.adapter.AdapterFragmentAdmin;
import com.google.android.material.tabs.TabLayout;

public class MainActivityAdmin extends AppCompatActivity {

    AdapterFragmentAdmin adapterFragmentAdmin;
    TabLayout main_tabLayout_admin;
    ViewPager main_viewPager_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        /*---find view by id---*/
        main_tabLayout_admin = findViewById(R.id.main_tabLayout_admin);
        main_viewPager_admin = findViewById(R.id.main_viewPager_admin);

        /*---adapter fragment admin---*/
        AdapterFragmentAdmin adapterFragmentAdmin = new AdapterFragmentAdmin(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        main_viewPager_admin.setAdapter(adapterFragmentAdmin);
        main_tabLayout_admin.setupWithViewPager(main_viewPager_admin);

    }
}