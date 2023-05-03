package com.example.mtvi_ver2.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.mtvi_ver2.R;
import com.example.mtvi_ver2.admin.adapter.AdapterFragmentAdmin;
import com.example.mtvi_ver2.user.adapters.AdapterFragmentUser;
import com.google.android.material.tabs.TabLayout;

public class MainActivityUser extends AppCompatActivity {

    TabLayout main_tabLayout_user;
    ViewPager main_viewPager_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        /*---find view by id---*/
        main_tabLayout_user = findViewById(R.id.main_tabLayout_user);
        main_viewPager_user = findViewById(R.id.main_viewPager_user);

        /*---adapter fragment admin---*/
        AdapterFragmentUser adapterFragmentUser = new AdapterFragmentUser(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        main_viewPager_user.setAdapter(adapterFragmentUser);
        main_tabLayout_user.setupWithViewPager(main_viewPager_user);
    }
}