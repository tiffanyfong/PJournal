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
        npLightPads.setKey(getString(R.string.light_pads));
        npMediumPads.setKey(getString(R.string.medium_pads));
        npHeavyPads.setKey(getString(R.string.heavy_pads));
        npLightTampons.setKey(getString(R.string.light_tampons));
        npMediumTampons.setKey(getString(R.string.medium_tampons));
        npHeavyTampons.setKey(getString(R.string.heavy_tampons));
        npMenstrualCups.setKey(getString(R.string.menstrual_cups));
    }

    private void loadHygiene() {
        if (hygiene != null) {
            npLightPads.setValue(hygiene.getLightPads());
            npMediumPads.setValue(hygiene.getMediumPads());
            npHeavyPads.setValue(hygiene.getHeavyPads());
            npLightTampons.setValue(hygiene.getLightTampons());
            npMediumTampons.setValue(hygiene.getMediumTampons());
            npHeavyTampons.setValue(hygiene.getHeavyTampons());
            npMenstrualCups.setValue(hygiene.getMenstrualCups());
        }
    }

    public void updateRealm() {
        if (hygiene == null) {
            realm.beginTransaction();
            hygiene = realm.createObject(Hygiene.class);
            hygiene.setDate(((NoteActivity) getActivity()).getDate());
            realm.commitTransaction();
        }
        realm.beginTransaction();

        hygiene.setLightPads(npLightPads.getValue());
        hygiene.setMediumPads(npMediumPads.getValue());
        hygiene.setHeavyPads(npHeavyPads.getValue());
        hygiene.setLightTampons(npLightTampons.getValue());
        hygiene.setMediumTampons(npMediumTampons.getValue());
        hygiene.setHeavyTampons(npHeavyTampons.getValue());
        hygiene.setMenstrualCups(npMenstrualCups.getValue());

        ((NoteActivity) getActivity()).getNote().setHygiene(hygiene);

        realm.commitTransaction();
    }
}