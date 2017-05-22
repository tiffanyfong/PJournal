package com.tmf.pjournal.statsFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tmf.pjournal.R;
import com.tmf.pjournal.activity.StatsActivity;
import com.tmf.pjournal.data.Note;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

import static com.tmf.pjournal.data.Note.KEY_DATE;
import static com.tmf.pjournal.data.Note.KEY_PERIOD_ENDED;
import static com.tmf.pjournal.data.Note.KEY_PERIOD_STARTED;

public class FragmentSummary extends Fragment {

    private Realm realm;

    @BindView(R.id.tvAvgCycleLength)
    TextView tvAvgCycleLength;

    @BindView(R.id.tvAvgPeriodLength)
    TextView tvAvgPeriodLength;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_summary, container, false);
        ButterKnife.bind(this, v);
        realm = ((StatsActivity) getActivity()).getRealm();
        RealmResults<Note> periodStartedResults = realm.where(Note.class).equalTo(KEY_PERIOD_STARTED, true).findAllSorted(KEY_DATE, Sort.ASCENDING);
        RealmResults<Note> periodEndedResults = realm.where(Note.class).equalTo(KEY_PERIOD_ENDED, true).findAllSorted(KEY_DATE, Sort.ASCENDING);

        calculateAvgCycleLength(periodStartedResults);
        calculateAvgPeriodLength(periodStartedResults, periodEndedResults);
        return v;
    }

    // TODO lots of assumptions here i.e. users inputted data in correctly
    private void calculateAvgCycleLength(RealmResults<Note> startResults) {
        int size = startResults.size();
        double sumCycleLengths = 0;
        double currCycleLength = 0;

        if (size < 2) {
            tvAvgCycleLength.setText(getString(R.string.not_enough_information));
            return;
        }

        for (int i = 0; i < size-1; i++) {
            currCycleLength = getDayDiff(startResults.get(i).getDate(),startResults.get(i+1).getDate());
            if (currCycleLength < 0) {
                tvAvgCycleLength.setText(getString(R.string.not_enough_information));
                return;
            }
            sumCycleLengths += currCycleLength;
        }

        int avgCycleLength = (int) (sumCycleLengths/(size-1) + 0.5);
        tvAvgCycleLength.setText(String.format(getString(R.string.x_days), avgCycleLength));
    }

    // TODO implement
    private void calculateAvgPeriodLength(RealmResults<Note> startResults, RealmResults<Note> endedResults) {
//        if (startResults.isEmpty() || endedResults.isEmpty()) {
            tvAvgPeriodLength.setText(getString(R.string.not_enough_information));
//            return;
//        }
    }

    private double getDayDiff(String sDate1, String sDate2) {
        try {
            Date date1 = new SimpleDateFormat("yyyy/MM/dd").parse(sDate1);
            Date date2 = new SimpleDateFormat("yyyy/MM/dd").parse(sDate2);
            long diffMillis = date2.getTime() - date1.getTime();
            return TimeUnit.DAYS.convert(diffMillis,TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
