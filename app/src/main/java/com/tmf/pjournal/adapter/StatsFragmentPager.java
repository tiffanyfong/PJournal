package com.tmf.pjournal.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tmf.pjournal.statsFragment.FragmentHygieneStats;
import com.tmf.pjournal.statsFragment.FragmentSummary;

public class StatsFragmentPager extends FragmentPagerAdapter {

    public StatsFragmentPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentSummary();
            case 1:
                return new FragmentHygieneStats();
            default:
                return new FragmentSummary();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    // TODO extract strings
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Summary";
            case 1:
                return "Hygiene";
            default:
                return "Summary";
        }
    }
}
