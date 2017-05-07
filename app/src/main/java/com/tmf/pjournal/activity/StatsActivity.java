package com.tmf.pjournal.activity;

import android.os.Bundle;

import com.tmf.pjournal.R;

import butterknife.ButterKnife;

public class StatsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_stats);
        disableNavItem(R.id.nav_stats);
    }
}
