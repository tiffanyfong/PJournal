package com.tmf.pjournal.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.tmf.pjournal.R;
import com.tmf.pjournal.adapter.StatsFragmentPager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatsActivity extends BaseActivity {

    private StatsFragmentPager statsFragmentPager;
    private String date;

    @BindView(R.id.statsPager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_stats);
        disableNavItem(R.id.nav_stats);
        ButterKnife.bind(this);

        statsFragmentPager = new StatsFragmentPager(getSupportFragmentManager());
        viewPager.setAdapter(statsFragmentPager);
        date = getCurrentDateString();
    }
}
