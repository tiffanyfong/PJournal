package com.tmf.pjournal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.Toast;

import com.tmf.pjournal.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

// TODO rename to CalendarActivity?
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String KEY_DATE_MILLIS = "KEY_DATE_MILLIS";
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupBackgroundUI();
        fixNavView();
    }

    private void setupBackgroundUI() {
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // TODO how to make all activities have same navigation drawer
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if (item.isEnabled()) {
            switch (item.getItemId()) {
                case R.id.nav_calendar:
                    startActivity(new Intent(this, MainActivity.class));
                    break;

                case R.id.nav_stats:
                    break;

                case R.id.nav_search:
                    break;

                case R.id.nav_profile:
                    break;
            }
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    // FROM HERE ON NOW
    // methods/variables unique to activity

    @BindView(R.id.calendar)
    CalendarView calendar;

    private void fixNavView() {
        // DISABLE CURRENT ACTIVITY IN NAV VIEW
        MenuItem menuItem = navigationView.getMenu().findItem(R.id.nav_calendar);
        menuItem.setEnabled(false);
    }

    @OnClick(R.id.tvCalendarNote) public void notePressed() {
        Intent showNoteActivity = new Intent(MainActivity.this, NoteActivity.class);
        showNoteActivity.putExtra(KEY_DATE_MILLIS,calendar.getDate());
        startActivity(showNoteActivity);
    }



}
