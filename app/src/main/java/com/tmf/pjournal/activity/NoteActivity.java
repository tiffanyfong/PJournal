package com.tmf.pjournal.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tmf.pjournal.MainApplication;
import com.tmf.pjournal.NoteFragmentPager;
import com.tmf.pjournal.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class NoteActivity extends AppCompatActivity {

    private String date;
    private NoteFragmentPager noteFragmentPager;

    @BindView(R.id.notePager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        noteFragmentPager = new NoteFragmentPager(getSupportFragmentManager());
        viewPager.setAdapter(noteFragmentPager);

        date = retrieveDate();
        getSupportActionBar().setTitle(date);
    }

    private String retrieveDate() {
        if (getIntent().hasExtra(MainActivity.KEY_DATE_STRING)) {
            return getIntent().getStringExtra(MainActivity.KEY_DATE_STRING);
        }
        else {
            return BaseActivity.getCurrentDateString();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public String getDate() {
        return date;
    }

    public Realm getRealm() {
        return ((MainApplication) getApplication()).getRealm();
    }

    @Override
    public void onBackPressed() {
        for (int i = 0; i < noteFragmentPager.getCount(); i++) {
            noteFragmentPager.updateRealm(i);
        }

        super.onBackPressed();
    }
}
