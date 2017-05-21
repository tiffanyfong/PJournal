package com.tmf.pjournal.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.tmf.pjournal.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout baseDrawer;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private LinearLayout layoutMainScreen;

    @Override
    public void setContentView(final int layoutResID) {
        bindBaseViews();
        setupBackgroundUI();
        setMainContent(layoutResID);
    }


    private void bindBaseViews() {
        baseDrawer = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        toolbar = (Toolbar) baseDrawer.findViewById(R.id.toolbar);
        navigationView = (NavigationView) baseDrawer.findViewById(R.id.nav_view);
        layoutMainScreen = (LinearLayout) baseDrawer.findViewById(R.id.layout_main_screen);
    }

    private void setupBackgroundUI() {
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, baseDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        baseDrawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setMainContent(int layoutResID) {
        View inflatedLayout = getLayoutInflater().inflate(layoutResID, null, false);
        layoutMainScreen.addView(inflatedLayout);
        super.setContentView(baseDrawer);
    }

    protected void disableNavItem(int navItemID) {
        Menu menu = navigationView.getMenu();

        for (int i = 0; i < menu.size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            menuItem.setEnabled(menuItem.getItemId() != navItemID);
        }
    }

    protected static String getCurrentDateString() {
        return getDateStringFromMillis(System.currentTimeMillis());
    }

    protected static String getDateStringFromMillis(long millis) {
        SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy");
        return formatter.format(new Date(millis));
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        baseDrawer.closeDrawer(GravityCompat.START);

        switch (item.getItemId()) {
            case R.id.nav_calendar:
                startActivity(new Intent(this, CalendarActivity.class));
                break;

            case R.id.nav_stats:
                startActivity(new Intent(this, StatsActivity.class));
                break;

            case R.id.nav_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;

            case R.id.nav_resources:
                startActivity(new Intent(this, ResourcesActivity.class));
                break;

            case R.id.nav_profile:
                startActivity(new Intent(this, ProfileActivity.class));
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        if (baseDrawer.isDrawerOpen(GravityCompat.START)) {
            baseDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}