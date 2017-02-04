package com.example.jeremie.javaproject5777.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.example.jeremie.javaproject5777.controller.Fragments.BlankFragment;
import com.example.jeremie.javaproject5777.controller.Fragments.TabsFragment;
import com.example.jeremie.javaproject5777.R;
import java.util.concurrent.ExecutionException;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Fragment fragment = null;
    private FragmentManager manager = getSupportFragmentManager();
    /**
     * this function onnect the layout to the activity and download all
     * the exsistent data of the first application with use asyncTask and displays them in the tabs
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            new AsyncTaskUpdate().execute(getBaseContext()).get();
            new AsyncTaskUpdateActivities().execute(getBaseContext()).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        fragment= new TabsFragment();
        manager.beginTransaction().replace(R.id.fragment_container,fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * to manage the navigation menu
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_search) {
            fragment = new TabsFragment();
            manager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
            // Handle the camera action
        } else if (id == R.id.nav_empty) {
            fragment = new BlankFragment();
            manager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
        } else if (id == R.id.nav_logout) {
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
