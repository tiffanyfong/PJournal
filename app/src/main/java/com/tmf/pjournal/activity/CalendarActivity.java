package com.tmf.pjournal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.tmf.pjournal.MainApplication;
import com.tmf.pjournal.R;
import com.tmf.pjournal.data.Moods;
import com.tmf.pjournal.data.Note;
import com.tmf.pjournal.data.Symptoms;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.tmf.pjournal.data.Note.KEY_DATE;

public class CalendarActivity extends BaseActivity {

    public static final String KEY_DATE_STRING = "KEY_DATE_STRING";
    public static final int REQUEST_NOTE = 101;
    private String selectedDate;
    private Note selectedNote;

    @BindView(R.id.calendar)
    CalendarView calendar;

    @BindView(R.id.tvCalendarNote)
    TextView tvCalendarNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_calendar);
        ButterKnife.bind(this);
        disableNavItem(R.id.nav_calendar);

        ((MainApplication) getApplication()).openRealm();

        // default date = today
        selectedDate = getCurrentDateString();
        getDayDataFromRealm();
        updateNoteText();

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = String.format("%1$2d/%2$02d/%3$02d", year, month+1, dayOfMonth);
                getDayDataFromRealm();
                updateNoteText();
            }
        });
    }

    @OnClick(R.id.tvCalendarNote) public void notePressed() {
        Intent showNoteActivity = new Intent(CalendarActivity.this, NoteActivity.class);
        showNoteActivity.putExtra(KEY_DATE_STRING,selectedDate);
        startActivityForResult(showNoteActivity, REQUEST_NOTE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_NOTE) {
            Toast.makeText(this, getString(R.string.note_updated), Toast.LENGTH_SHORT).show();
            getDayDataFromRealm();
            updateNoteText();
        }
    }

    private void updateNoteText() {
        if (selectedNote == null) {
            tvCalendarNote.setText(null);
        }
        else {
            String noteText = "";
            if (selectedNote.isPeriodStarted())
                noteText += getString(R.string.period_started_today) + "\n";
            if (selectedNote.isPeriodEnded())
                noteText += getString(R.string.period_ended_today) + "\n";
            noteText += selectedNote.getNoteText();
            if (!TextUtils.isEmpty(noteText)) {
                noteText += "\n";
            }
            String moodsText = getAllActiveMoods(selectedNote.getMoods());
            String symptomsText = getAllActiveSymptoms(selectedNote.getSymptoms());
            tvCalendarNote.setText(noteText + symptomsText + moodsText);
        }
    }

    private void getDayDataFromRealm() {
        selectedNote = ((MainApplication) getApplication()).getRealm().where(Note.class).equalTo(KEY_DATE, selectedDate).findFirst();
    }

    @Override
    protected void onDestroy() {
        ((MainApplication) getApplication()).closeRealm();
        super.onDestroy();
    }

    public String getAllActiveMoods(Moods moods) {
        if (moods == null) {
            return "";
        }

        String activeMoods = "";
        if (moods.isAngry())
            activeMoods += getString(R.string.angry) + ", ";
        if (moods.isAnxious())
            activeMoods += getString(R.string.anxious) + ", ";
        if (moods.isBlah())
            activeMoods += getString(R.string.blah) + ", ";
        if (moods.isCalm())
            activeMoods += getString(R.string.calm) + ", ";
        if (moods.isConfused())
            activeMoods += getString(R.string.confused) + ", ";
        if (moods.isDepressed())
            activeMoods += getString(R.string.depressed) + ", ";
        if (moods.isFine())
            activeMoods += getString(R.string.fine) + ", ";
        if (moods.isHappy())
            activeMoods += getString(R.string.happy) + ", ";
        if (moods.isHyper())
            activeMoods += getString(R.string.hyper) + ", ";
        if (moods.isInLove())
            activeMoods += getString(R.string.in_love) + ", ";
        if (moods.isIrritable())
            activeMoods += getString(R.string.irritable) + ", ";
        if (moods.isSad())
            activeMoods += getString(R.string.sad) + ", ";
        if (moods.isSick())
            activeMoods += getString(R.string.sick) + ", ";
        if (moods.isStressed())
            activeMoods += getString(R.string.stressed) + ", ";
        if (moods.isTired())
            activeMoods += getString(R.string.tired) + ", ";

        if (activeMoods.length() > 2) {
            return getString(R.string.moods) + ": " + activeMoods.substring(0,activeMoods.length()-2) + "\n";
        }
        return "";
    }

    public String getAllActiveSymptoms(Symptoms symptoms) {
        if (symptoms == null) {
            return "";
        }

        String activeSymptoms = "";
        if (symptoms.isAcne())
            activeSymptoms += getString(R.string.acne) + ", ";
        if (symptoms.isBackache())
            activeSymptoms += getString(R.string.backache) + ", ";
        if (symptoms.isBloating())
            activeSymptoms += getString(R.string.bloating) + ", ";
        if (symptoms.isBloodClots())
            activeSymptoms += getString(R.string.blood_clots) + ", ";
        if (symptoms.isCramps())
            activeSymptoms += getString(R.string.cramps) + ", ";
        if (symptoms.isDizziness())
            activeSymptoms += getString(R.string.dizziness) + ", ";
        if (symptoms.isFatigue())
            activeSymptoms += getString(R.string.fatigue) + ", ";
        if (symptoms.isHeadache())
            activeSymptoms += getString(R.string.headache) + ", ";
        if (symptoms.isInsomnia())
            activeSymptoms += getString(R.string.insomnia) + ", ";
        if (symptoms.isNausea())
            activeSymptoms += getString(R.string.nausea) + ", ";
        if (symptoms.isStomachache())
            activeSymptoms += getString(R.string.stomachache) + ", ";
        if (symptoms.isTenderBreasts())
            activeSymptoms += getString(R.string.tender_breasts) + ", ";

        if (activeSymptoms.length() > 2) {
            return getString(R.string.symptoms) + ": " + activeSymptoms.substring(0, activeSymptoms.length()-2) + "\n";
        }
        return "";
    }
}
