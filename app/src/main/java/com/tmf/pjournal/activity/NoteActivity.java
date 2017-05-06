package com.tmf.pjournal.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tmf.pjournal.R;

import butterknife.ButterKnife;

public class NoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        ButterKnife.bind(this);
    }
}
