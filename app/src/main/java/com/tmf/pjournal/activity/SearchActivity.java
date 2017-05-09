package com.tmf.pjournal.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.tmf.pjournal.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity {

    @BindView(R.id.autotvSearch)
    AutoCompleteTextView autotvSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_search);
        ButterKnife.bind(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, MOODS);
        autotvSearch.setAdapter(adapter);
    }

    private static final String[] MOODS = new String[] {
            "angry", "happy", "sad", "anxious", "silly", "awesome"
    };
}
