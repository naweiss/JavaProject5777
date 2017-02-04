package com.example.jeremie.javaproject5777.controller;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.jeremie.javaproject5777.controller.Fragments.FragmentBusinessActivities;
import com.example.jeremie.javaproject5777.controller.Fragments.details;
import com.example.jeremie.javaproject5777.R;
import com.example.jeremie.javaproject5777.model.datasource.ListDB_manager;
import com.example.jeremie.javaproject5777.model.entities.Business;

/**
 * Created by jerem on 25.01.17.
 * Package: ${PACKAGE_NAME}
 */

public class Business_details extends AppCompatActivity {


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    public int id;
    private Business business = new Business();
    private ViewPager mViewPager;
    TabLayout tabLayout;
    CollapsingToolbarLayout collapsingToolbar;

    private ListDB_manager db_manager = ListDB_manager.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_detail);
        collapsingToolbar = (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);

        ImageView pict = (ImageView)findViewById(R.id.backgroundImageView);
        Bitmap bit = getIntent().getParcelableExtra("Image");
        pict.setImageBitmap(bit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        id = getIntent().getIntExtra("ID",-1);
        if(id == -1)
            finish();
        business = db_manager.getBusiness(id);



        collapsingToolbar.setTitle(business.getName());

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new details();
                    break;
                case 1:
                    fragment = new FragmentBusinessActivities().newInstance(id);
                    break;

            }
            return fragment;


        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "details";
                case 1:
                    return "activities";
            }
            return null;
        }
    }

}