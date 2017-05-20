package com.tmf.pjournal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.tmf.pjournal.noteFragment.FragmentHygiene;
import com.tmf.pjournal.noteFragment.FragmentMoods;
import com.tmf.pjournal.noteFragment.FragmentNote;
import com.tmf.pjournal.noteFragment.FragmentSymptoms;

public class NoteFragmentPager extends FragmentPagerAdapter {
    public SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

    public NoteFragmentPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentNote();
            case 1:
                return new FragmentSymptoms();
            case 2:
                return new FragmentMoods();
            case 3:
                return new FragmentHygiene();
            default:
                return new FragmentNote();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Notes";
            case 1:
                return "Symptoms";
            case 2:
                return "Moods";
            case 3:
                return "Hygiene";
            default:
                return "Notes";
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }
}
