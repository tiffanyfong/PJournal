package com.tmf.pjournal.noteFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import com.tmf.pjournal.R;
import com.tmf.pjournal.activity.NoteActivity;
import com.tmf.pjournal.data.Hygiene;
import com.tmf.pjournal.data.Note;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class FragmentHygiene extends Fragment {
    public static final String TAG = "FragmentHygiene";

    private Hygiene hygiene;
    private Realm realm;

    @BindView(R.id.npLightTampons)
    NumberPicker npLightTampons;

    @BindView(R.id.npMediumTampons)
    NumberPicker npMediumTampons;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hygiene, container, false);
        ButterKnife.bind(this, v);

        npLightTampons.setMinValue(0);
        npLightTampons.setWrapSelectorWheel(false);
        npMediumTampons.setMinValue(0);
        npMediumTampons.setWrapSelectorWheel(false);

        realm = ((NoteActivity) getActivity()).getRealm();
        hygiene = ((NoteActivity) getActivity()).getNote().getHygiene();
        loadHygiene();

        return v;
    }

    private void loadHygiene() {
        if (hygiene != null) {
            npLightTampons.setValue(hygiene.getLightTampons());
            npMediumTampons.setValue(hygiene.getMediumTampons());
        }
    }

    public void updateRealm() {

    }
}