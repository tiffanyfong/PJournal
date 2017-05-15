package com.tmf.pjournal.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tmf.pjournal.NoteFragmentPager;
import com.tmf.pjournal.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoteActivity extends AppCompatActivity {

    private String date;

    @BindView(R.id.notePager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        ButterKnife.bind(this);

        // TODO back button in toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        NoteFragmentPager noteFragmentPager = new NoteFragmentPager(getSupportFragmentManager());
        viewPager.setAdapter(noteFragmentPager);

        date = retrieveDate();
        getSupportActionBar().setTitle(date);

    }

    @OnClick(R.id.btnSaveNote) public void saveNote() {

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
        // TODO dialog unsaved changes?
        super.onBackPressed();
        return true;
    }
}
