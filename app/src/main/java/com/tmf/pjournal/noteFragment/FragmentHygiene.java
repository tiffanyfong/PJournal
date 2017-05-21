package com.tmf.pjournal.noteFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmf.pjournal.NumberPicker;
import com.tmf.pjournal.R;
import com.tmf.pjournal.activity.NoteActivity;
import com.tmf.pjournal.data.Hygiene;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class FragmentHygiene extends Fragment {
    public static final String TAG = "FragmentHygiene";

    private Hygiene hygiene;
    private Realm realm;

    @BindView(R.id.npLightPads)
    NumberPicker npLightPads;

    @BindView(R.id.npMediumPads)
    NumberPicker npMediumPads;

    @BindView(R.id.npHeavyPads)
    NumberPicker npHeavyPads;

    @BindView(R.id.npLightTampons)
    NumberPicker npLightTampons;

    @BindView(R.id.npMediumTampons)
    NumberPicker npMediumTampons;

    @BindView(R.id.npHeavyTampons)
    NumberPicker npHeavyTampons;

    @BindView(R.id.npMenstrualCups)
    NumberPicker npMenstrualCups;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hygiene, container, false);
        ButterKnife.bind(this, v);
        setAllHygieneText();

        realm = ((NoteActivity) getActivity()).getRealm();
        hygiene = ((NoteActivity) getActivity()).getNote().getHygiene();
        loadHygiene();

        return v;
    }

    private void setAllHygieneText() {
        npLightPads.setTvKey(getString(R.string.light_pads));
        npMediumPads.setTvKey(getString(R.string.medium_pads));
        npHeavyPads.setTvKey(getString(R.string.heavy_pads));
        npLightTampons.setTvKey(getString(R.string.light_tampons));
        npMediumTampons.setTvKey(getString(R.string.medium_tampons));
        npHeavyTampons.setTvKey(getString(R.string.heavy_tampons));
        npMenstrualCups.setTvKey(getString(R.string.menstrual_cups));
    }

    private void loadHygiene() {
        if (hygiene != null) {

        }
    }

    public void updateRealm() {
        if (hygiene == null) {
            realm.beginTransaction();
            hygiene = realm.createObject(Hygiene.class);
            realm.commitTransaction();
        }
        realm.beginTransaction();

//        hygiene.setAcne(tbAcne.isChecked());
//        hygiene.setBackache(tbBackache.isChecked());
//        hygiene.setBloating(tbBloating.isChecked());
//        hygiene.setBloodClots(tbBloodClots.isChecked());
//        hygiene.setCramps(tbCramps.isChecked());
//        hygiene.setDizziness(tbDizziness.isChecked());
//        hygiene.setFatigue(tbFatigue.isChecked());
//        hygiene.setHeadache(tbHeadache.isChecked());
//        hygiene.setInsomnia(tbInsomnia.isChecked());
//        hygiene.setNausea(tbNausea.isChecked());
//        hygiene.setStomachache(tbStomachache.isChecked());
//        hygiene.setTenderBreasts(tbTenderBreasts.isChecked());
        ((NoteActivity) getActivity()).getNote().setHygiene(hygiene);

        realm.commitTransaction();
    }
}