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
import com.tmf.pjournal.data.Moods;

import io.realm.Realm;

public class FragmentMoods extends Fragment {
    public static final String KEY_DATE = "date";
    public static final String TAG = "FragmentMoods";

    private String[] moodsArray;


    private Moods moods;
    private Realm realm;

    private ToggleButton tbAngry;
    private ToggleButton tbAnxious;
    private ToggleButton tbBlah;
    private ToggleButton tbCalm;
    private ToggleButton tbConfused;
    private ToggleButton tbDepressed;
    private ToggleButton tbFine;
    private ToggleButton tbHappy;
    private ToggleButton tbHyper;
    private ToggleButton tbInLove;
    private ToggleButton tbIrritable;
    private ToggleButton tbSad;
    private ToggleButton tbSick;
    private ToggleButton tbStressed;
    private ToggleButton tbTired;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_moods, container, false);

        moodsArray = getResources().getStringArray(R.array.moodsArray);

        tbAngry = (ToggleButton) v.findViewById(R.id.tbAngry);
        tbAnxious = (ToggleButton) v.findViewById(R.id.tbAnxious);
        tbBlah = (ToggleButton) v.findViewById(R.id.tbBlah);
        tbCalm = (ToggleButton) v.findViewById(R.id.tbCalm);
        tbConfused = (ToggleButton) v.findViewById(R.id.tbConfused);
        tbDepressed = (ToggleButton) v.findViewById(R.id.tbDepressed);
        tbFine = (ToggleButton) v.findViewById(R.id.tbFine);
        tbHappy = (ToggleButton) v.findViewById(R.id.tbHappy);
        tbHyper = (ToggleButton) v.findViewById(R.id.tbHyper);
        tbInLove = (ToggleButton) v.findViewById(R.id.tbInLove);
        tbIrritable = (ToggleButton) v.findViewById(R.id.tbIrritable);
        tbSad = (ToggleButton) v.findViewById(R.id.tbSad);
        tbSick = (ToggleButton) v.findViewById(R.id.tbSick);
        tbStressed = (ToggleButton) v.findViewById(R.id.tbStressed);
        tbTired = (ToggleButton) v.findViewById(R.id.tbTired);

        realm = ((NoteActivity) getActivity()).getRealm();
        moods = realm.where(Moods.class).equalTo(KEY_DATE, ((NoteActivity) getActivity()).getDate()).findFirst();
        setMoods();
        return v;
    }

    public void setMoods() {
        if (moods != null) {
            tbAngry.setChecked(moods.isAngry());
            tbAnxious.setChecked(moods.isAnxious());
            tbBlah.setChecked(moods.isBlah());
            tbCalm.setChecked(moods.isCalm());
            tbConfused.setChecked(moods.isConfused());
            tbDepressed.setChecked(moods.isDepressed());
            tbFine.setChecked(moods.isFine());
            tbHappy.setChecked(moods.isHappy());
            tbHyper.setChecked(moods.isHyper());
            tbInLove.setChecked(moods.isInLove());
            tbIrritable.setChecked(moods.isIrritable());
            tbSad.setChecked(moods.isSad());
            tbSick.setChecked(moods.isSick());
            tbStressed.setChecked(moods.isStressed());
            tbTired.setChecked(moods.isTired());
        }
    }

    public void updateRealm() {
        if (moods == null) {
            realm.beginTransaction();
            moods = realm.createObject(Moods.class);
            moods.setDate(((NoteActivity) getActivity()).getDate());
            realm.commitTransaction();
        }
        realm.beginTransaction();

        moods.setAngry(tbAngry.isChecked());
        moods.setAnxious(tbAnxious.isChecked());
        moods.setBlah(tbBlah.isChecked());
        moods.setCalm(tbCalm.isChecked());
        moods.setConfused(tbConfused.isChecked());
        moods.setDepressed(tbDepressed.isChecked());
        moods.setFine(tbFine.isChecked());
        moods.setHappy(tbHappy.isChecked());
        moods.setHyper(tbHyper.isChecked());
        moods.setInLove(tbInLove.isChecked());
        moods.setIrritable(tbIrritable.isChecked());
        moods.setSad(tbSad.isChecked());
        moods.setSick(tbSick.isChecked());
        moods.setStressed(tbStressed.isChecked());
        moods.setTired(tbTired.isChecked());

        realm.commitTransaction();
    }
}
