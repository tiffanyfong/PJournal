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
import com.tmf.pjournal.data.Moods;

import io.realm.Realm;

public class FragmentMoods extends Fragment {

    private String[] moodsArray; // TODO simplify code

    private Moods moods;
    private Realm realm;

    private CheckBox cbAngry;
    private CheckBox cbAnxious;
    private CheckBox cbBlah;
    private CheckBox cbCalm;
    private CheckBox cbConfused;
    private CheckBox cbDepressed;
    private CheckBox cbFine;
    private CheckBox cbHappy;
    private CheckBox cbHyper;
    private CheckBox cbInLove;
    private CheckBox cbIrritable;
    private CheckBox cbSad;
    private CheckBox cbSick;
    private CheckBox cbStressed;
    private CheckBox cbTired;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_moods, container, false);

        moodsArray = getResources().getStringArray(R.array.moodsArray);

        cbAngry = (CheckBox) v.findViewById(R.id.cbAngry);
        cbAnxious = (CheckBox) v.findViewById(R.id.cbAnxious);
        cbBlah = (CheckBox) v.findViewById(R.id.cbBlah);
        cbCalm = (CheckBox) v.findViewById(R.id.cbCalm);
        cbConfused = (CheckBox) v.findViewById(R.id.cbConfused);
        cbDepressed = (CheckBox) v.findViewById(R.id.cbDepressed);
        cbFine = (CheckBox) v.findViewById(R.id.cbFine);
        cbHappy = (CheckBox) v.findViewById(R.id.cbHappy);
        cbHyper = (CheckBox) v.findViewById(R.id.cbHyper);
        cbInLove = (CheckBox) v.findViewById(R.id.cbInLove);
        cbIrritable = (CheckBox) v.findViewById(R.id.cbIrritable);
        cbSad = (CheckBox) v.findViewById(R.id.cbSad);
        cbSick = (CheckBox) v.findViewById(R.id.cbSick);
        cbStressed = (CheckBox) v.findViewById(R.id.cbStressed);
        cbTired = (CheckBox) v.findViewById(R.id.cbTired);

        realm = ((NoteActivity) getActivity()).getRealm();
        moods = ((NoteActivity) getActivity()).getNote().getMoods();

        loadMoods();
        return v;
    }

    public void loadMoods() {
        if (moods != null) {
            cbAngry.setChecked(moods.isAngry());
            cbAnxious.setChecked(moods.isAnxious());
            cbBlah.setChecked(moods.isBlah());
            cbCalm.setChecked(moods.isCalm());
            cbConfused.setChecked(moods.isConfused());
            cbDepressed.setChecked(moods.isDepressed());
            cbFine.setChecked(moods.isFine());
            cbHappy.setChecked(moods.isHappy());
            cbHyper.setChecked(moods.isHyper());
            cbInLove.setChecked(moods.isInLove());
            cbIrritable.setChecked(moods.isIrritable());
            cbSad.setChecked(moods.isSad());
            cbSick.setChecked(moods.isSick());
            cbStressed.setChecked(moods.isStressed());
            cbTired.setChecked(moods.isTired());
        }
    }

    public void updateRealm() {
        if (moods == null) {
            realm.beginTransaction();
            moods = realm.createObject(Moods.class);
            realm.commitTransaction();
        }
        realm.beginTransaction();

        moods.setAngry(cbAngry.isChecked());
        moods.setAnxious(cbAnxious.isChecked());
        moods.setBlah(cbBlah.isChecked());
        moods.setCalm(cbCalm.isChecked());
        moods.setConfused(cbConfused.isChecked());
        moods.setDepressed(cbDepressed.isChecked());
        moods.setFine(cbFine.isChecked());
        moods.setHappy(cbHappy.isChecked());
        moods.setHyper(cbHyper.isChecked());
        moods.setInLove(cbInLove.isChecked());
        moods.setIrritable(cbIrritable.isChecked());
        moods.setSad(cbSad.isChecked());
        moods.setSick(cbSick.isChecked());
        moods.setStressed(cbStressed.isChecked());
        moods.setTired(cbTired.isChecked());
        ((NoteActivity) getActivity()).getNote().setMoods(moods);

        realm.commitTransaction();
    }
}
