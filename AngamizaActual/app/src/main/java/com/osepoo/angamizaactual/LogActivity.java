package com.osepoo.angamizaactual;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.osepoo.angamizaactual.adapters.ViewPagerAdapter;

public class LogActivity extends AppCompatActivity {

    TabItem tabItem1,tabItem2;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    AppBarLayout appBarLayout;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        setupToolbar();

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tabs);
        tabItem1 = findViewById(R.id.tabitemperson);
        tabItem2 = findViewById(R.id.tabitemcar);

        assert getFragmentManager() != null;
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setTabTextColors(getResources().getColor(R.color.md_grey_500), getResources().getColor(R.color.colorPrimary));
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimary));


        // It is used to join TabLayout with ViewPager.
        tabLayout.setupWithViewPager(viewPager);
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
        params.setScrollFlags(0);  // clear all scroll flags
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setBackgroundColor(getColor(R.color.ghaliclassic));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}