package com.tmf.pjournal.noteFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.tmf.pjournal.R;
import com.tmf.pjournal.activity.NoteActivity;
import com.tmf.pjournal.data.Symptoms;

import io.realm.Realm;

public class FragmentSymptoms extends Fragment {

    private Symptoms symptoms;
    private Realm realm;

    private CheckBox cbAcne;
    private CheckBox cbBackache;
    private CheckBox cbBloating;
    private CheckBox cbBloodClots;
    private CheckBox cbCramps;
    private CheckBox cbDizziness;
    private CheckBox cbFatigue;
    private CheckBox cbHeadache;
    private CheckBox cbInsomnia;
    private CheckBox cbNausea;
    private CheckBox cbStomachache;
    private CheckBox cbTenderBreasts;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_symptoms, container, false);

        cbAcne = (CheckBox) v.findViewById(R.id.cbAcne);
        cbBackache = (CheckBox) v.findViewById(R.id.cbBackache);
        cbBloating = (CheckBox) v.findViewById(R.id.cbBloating);
        cbBloodClots = (CheckBox) v.findViewById(R.id.cbBloodClots);
        cbCramps = (CheckBox) v.findViewById(R.id.cbCramps);
        cbDizziness = (CheckBox) v.findViewById(R.id.cbDizziness);
        cbFatigue = (CheckBox) v.findViewById(R.id.cbFatigue);
        cbHeadache = (CheckBox) v.findViewById(R.id.cbHeadache);
        cbInsomnia = (CheckBox) v.findViewById(R.id.cbInsomnia);
        cbNausea = (CheckBox) v.findViewById(R.id.cbNausea);
        cbStomachache = (CheckBox) v.findViewById(R.id.cbStomachache);
        cbTenderBreasts = (CheckBox) v.findViewById(R.id.cbTenderBreasts);

        realm = ((NoteActivity) getActivity()).getRealm();

        symptoms = ((NoteActivity) getActivity()).getNote().getSymptoms();
        loadSymptoms();

        return v;
    }

    public void loadSymptoms() {
        if (symptoms != null) {
            cbAcne.setChecked(symptoms.isAcne());
            cbBackache.setChecked(symptoms.isBackache());
            cbBloating.setChecked(symptoms.isBloating());
            cbBloodClots.setChecked(symptoms.isBloodClots());
            cbCramps.setChecked(symptoms.isCramps());
            cbDizziness.setChecked(symptoms.isDizziness());
            cbFatigue.setChecked(symptoms.isFatigue());
            cbHeadache.setChecked(symptoms.isHeadache());
            cbInsomnia.setChecked(symptoms.isInsomnia());
            cbNausea.setChecked(symptoms.isNausea());
            cbStomachache.setChecked(symptoms.isStomachache());
            cbTenderBreasts.setChecked(symptoms.isTenderBreasts());
        }
    }

    public void updateRealm() {
        if (symptoms == null) {
            realm.beginTransaction();
            symptoms = realm.createObject(Symptoms.class);
            realm.commitTransaction();
        }
        realm.beginTransaction();

        symptoms.setAcne(cbAcne.isChecked());
        symptoms.setBackache(cbBackache.isChecked());
        symptoms.setBloating(cbBloating.isChecked());
        symptoms.setBloodClots(cbBloodClots.isChecked());
        symptoms.setCramps(cbCramps.isChecked());
        symptoms.setDizziness(cbDizziness.isChecked());
        symptoms.setFatigue(cbFatigue.isChecked());
        symptoms.setHeadache(cbHeadache.isChecked());
        symptoms.setInsomnia(cbInsomnia.isChecked());
        symptoms.setNausea(cbNausea.isChecked());
        symptoms.setStomachache(cbStomachache.isChecked());
        symptoms.setTenderBreasts(cbTenderBreasts.isChecked());
        ((NoteActivity) getActivity()).getNote().setSymptoms(symptoms);

        realm.commitTransaction();
    }


}
