package com.tmf.pjournal.statsFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.tmf.pjournal.R;
import com.tmf.pjournal.activity.StatsActivity;
import com.tmf.pjournal.data.Hygiene;
import com.tmf.pjournal.data.Note;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

import static com.tmf.pjournal.data.Note.KEY_DATE;

public class FragmentHygieneStats extends Fragment {
    private Realm realm;
    private List<String> xAxisDates = new ArrayList<>();

    @BindView(R.id.barChart)
    BarChart barChart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hygiene_stats, container, false);
        ButterKnife.bind(this, v);

        realm = ((StatsActivity) getActivity()).getRealm();
        RealmResults<Hygiene> results = realm.where(Hygiene.class).findAllSorted(KEY_DATE, Sort.ASCENDING);

        // TODO get all notes in most recent cycle
        // TODO change the date field to Date class (not String) to use realm's greaterThan/lessThan
        // TODO stacked bar for hygiene products
        // currently all dates for one hygiene product
        formatBarChart();

        if (!results.isEmpty()) {
            BarDataSet barDataSet = getBarDataSet(results);
            BarData barData = new BarData(barDataSet);

            barChart.setData(barData);
            barChart.getDescription().setEnabled(false);
            barChart.invalidate();
        }

        return v;
    }

    @NonNull
    private BarDataSet getBarDataSet(RealmResults<Hygiene> results) {
        List<BarEntry> stackEntries = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            stackEntries.add(new BarEntry(i, makeHygieneStack(results.get(i))));
            xAxisDates.add(results.get(i).getDate());
        }

        BarDataSet barDataSet = new BarDataSet(stackEntries, "");
        barDataSet.setColors(getResources().getIntArray(R.array.hygieneStackColors));
        barDataSet.setStackLabels(getResources().getStringArray(R.array.hygieneArray));
        barChart.getLegend().setWordWrapEnabled(true);

        return barDataSet;
    }

    private float[] makeHygieneStack(Hygiene hygiene) {
        return new float[] {
                hygiene.getLightPads(),
                hygiene.getMediumPads(),
                hygiene.getHeavyPads(),
                hygiene.getLightTampons(),
                hygiene.getMediumTampons(),
                hygiene.getHeavyTampons(),
                hygiene.getMenstrualCups()
        };
    }

    private void formatBarChart() {
        IAxisValueFormatter formatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xAxisDates.get((int) value);
            }
        };

        barChart.getXAxis().setGranularity(1f); // prevents showing decimal axis values
        barChart.getXAxis().setValueFormatter(formatter);
        barChart.getAxisLeft().setGranularity(1f);
        barChart.getAxisRight().setGranularity(1f);
    }
}
