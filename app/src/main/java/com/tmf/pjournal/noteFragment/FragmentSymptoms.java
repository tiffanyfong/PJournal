package com.tmf.pjournal.noteFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.tmf.pjournal.R;
import com.tmf.pjournal.activity.NoteActivity;
import com.tmf.pjournal.data.Symptoms;

import io.realm.Realm;

public class FragmentSymptoms extends Fragment {
    public static final String KEY_DATE = "date";
    public static final String TAG = "FragmentSymptoms";

    private Symptoms symptoms;
    private Realm realm;

    private ToggleButton tbAcne;
    private ToggleButton tbBackache;
    private ToggleButton tbBloating;
    private ToggleButton tbBloodClots;
    private ToggleButton tbCramps;
    private ToggleButton tbDizziness;
    private ToggleButton tbFatigue;
    private ToggleButton tbHeadache;
    private ToggleButton tbInsomnia;
    private ToggleButton tbNausea;
    private ToggleButton tbStomachache;
    private ToggleButton tbTenderBreasts;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_symptoms, container, false);

        tbAcne = (ToggleButton) v.findViewById(R.id.tbAcne);
        tbBackache = (ToggleButton) v.findViewById(R.id.tbBackache);
        tbBloating = (ToggleButton) v.findViewById(R.id.tbBloating);
        tbBloodClots = (ToggleButton) v.findViewById(R.id.tbBloodClots);
        tbCramps = (ToggleButton) v.findViewById(R.id.tbCramps);
        tbDizziness = (ToggleButton) v.findViewById(R.id.tbDizziness);
        tbFatigue = (ToggleButton) v.findViewById(R.id.tbFatigue);
        tbHeadache = (ToggleButton) v.findViewById(R.id.tbHeadache);
        tbInsomnia = (ToggleButton) v.findViewById(R.id.tbInsomnia);
        tbNausea = (ToggleButton) v.findViewById(R.id.tbNausea);
        tbStomachache = (ToggleButton) v.findViewById(R.id.tbStomachache);
        tbTenderBreasts = (ToggleButton) v.findViewById(R.id.tbTenderBreasts);

        realm = ((NoteActivity) getActivity()).getRealm();
        symptoms = realm.where(Symptoms.class).equalTo(KEY_DATE, ((NoteActivity) getActivity()).getDate()).findFirst();
        loadSymptoms();

        return v;
    }

    public void loadSymptoms() {
        if (symptoms != null) {
            tbAcne.setChecked(symptoms.isAcne());
            tbBackache.setChecked(symptoms.isBackache());
            tbBloating.setChecked(symptoms.isBloating());
            tbBloodClots.setChecked(symptoms.isBloodClots());
            tbCramps.setChecked(symptoms.isCramps());
            tbDizziness.setChecked(symptoms.isDizziness());
            tbFatigue.setChecked(symptoms.isFatigue());
            tbHeadache.setChecked(symptoms.isHeadache());
            tbInsomnia.setChecked(symptoms.isInsomnia());
            tbNausea.setChecked(symptoms.isNausea());
            tbStomachache.setChecked(symptoms.isStomachache());
            tbTenderBreasts.setChecked(symptoms.isTenderBreasts());
        }
    }

    public void updateRealm() {
        if (symptoms == null) {
            realm.beginTransaction();
            symptoms = realm.createObject(Symptoms.class);
            symptoms.setDate(((NoteActivity) getActivity()).getDate());
            realm.commitTransaction();
        }
        realm.beginTransaction();

        symptoms.setAcne(tbAcne.isChecked());
        symptoms.setBackache(tbBackache.isChecked());
        symptoms.setBloating(tbBloating.isChecked());
        symptoms.setBloodClots(tbBloodClots.isChecked());
        symptoms.setCramps(tbCramps.isChecked());
        symptoms.setDizziness(tbDizziness.isChecked());
        symptoms.setFatigue(tbFatigue.isChecked());
        symptoms.setHeadache(tbHeadache.isChecked());
        symptoms.setInsomnia(tbInsomnia.isChecked());
        symptoms.setNausea(tbNausea.isChecked());
        symptoms.setStomachache(tbStomachache.isChecked());
        symptoms.setTenderBreasts(tbTenderBreasts.isChecked());

        realm.commitTransaction();
    }


}
