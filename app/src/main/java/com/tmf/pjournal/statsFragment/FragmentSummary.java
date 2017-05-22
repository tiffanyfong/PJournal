package com.tmf.pjournal.statsFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmf.pjournal.R;
import com.tmf.pjournal.activity.StatsActivity;
import com.tmf.pjournal.data.Note;

import butterknife.ButterKnife;

import static com.tmf.pjournal.data.Note.KEY_DATE;

public class FragmentSummary extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_summary, container, false);
        ButterKnife.bind(this, v);
//        realm = ((StatsActivity) getActivity()).getRealm();
//        note = realm.where(Note.class).equalTo(KEY_DATE, ((StatsActivity) getActivity()).getDate()).findFirst();
//        setNoteText();
        return v;
    }

}
