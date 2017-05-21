package com.tmf.pjournal.activity;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tmf.pjournal.MainApplication;
import com.tmf.pjournal.NoteFragmentPager;
import com.tmf.pjournal.R;
import com.tmf.pjournal.data.Note;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

import static com.tmf.pjournal.data.Note.KEY_DATE;

public class NoteActivity extends AppCompatActivity {

    private String date;
    private Note note;
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
        note = getRealm().where(Note.class).equalTo(KEY_DATE, date).findFirst();
        if (note == null) {
            getRealm().beginTransaction();
            note = getRealm().createObject(Note.class);
            note.setDate(date);
            getRealm().commitTransaction();
        }
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

    @NonNull
    public Note getNote() {
        return note;
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
