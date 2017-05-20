package com.tmf.pjournal.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.tmf.pjournal.MainApplication;
import com.tmf.pjournal.NoteFragmentPager;
import com.tmf.pjournal.R;
import com.tmf.pjournal.noteFragment.FragmentNote;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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

    @OnClick(R.id.btnSaveNote) public void saveNote() {
        FragmentNote fragmentNote = (FragmentNote) noteFragmentPager.registeredFragments.get(0);
        fragmentNote.updateRealm();

        Toast.makeText(this, R.string.note_saved, Toast.LENGTH_SHORT).show();
        Intent intentResult = new Intent();
        setResult(RESULT_OK, intentResult);
        finish();
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

    public String getDate() {
        return date;
    }

    public Realm getAppRealmNote() {
        return ((MainApplication) getApplication()).getRealmNote();
    }
}
