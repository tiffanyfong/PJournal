package com.tmf.pjournal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.tmf.pjournal.MainApplication;
import com.tmf.pjournal.R;
import com.tmf.pjournal.data.Moods;
import com.tmf.pjournal.data.Note;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

// TODO rename to CalendarActivity?
public class MainActivity extends BaseActivity {

    public static final String KEY_DATE_STRING = "KEY_DATE_STRING";
    public static final String KEY_DATE = "date";
    public static final int REQUEST_NOTE = 101;
    private String selectedDate;
    private Note selectedNote;
    private Moods selectedMoods;

    @BindView(R.id.calendar)
    CalendarView calendar;

    @BindView(R.id.tvCalendarNote)
    TextView tvCalendarNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
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
                selectedDate = (month+1) + "/" + dayOfMonth + "/" + year;
                getDayDataFromRealm();
                updateNoteText();
            }
        });
    }

    @OnClick(R.id.tvCalendarNote) public void notePressed() {
        Intent showNoteActivity = new Intent(MainActivity.this, NoteActivity.class);
        showNoteActivity.putExtra(KEY_DATE_STRING,selectedDate);
        startActivityForResult(showNoteActivity, REQUEST_NOTE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_NOTE) {
            Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
            getDayDataFromRealm();
            updateNoteText();
        }
    }

    private void updateNoteText() {
        if (selectedNote == null && selectedMoods == null) {
            tvCalendarNote.setText(null);
        }
        else {
            String noteText = selectedNote != null ? selectedNote.getNoteText() + "\n" : "";
            String moodsText = selectedMoods != null ? getAllActiveMoods() : "";
            tvCalendarNote.setText(noteText + moodsText);
        }
    }

    private void getDayDataFromRealm() {
        selectedNote = ((MainApplication) getApplication()).getRealm().where(Note.class).equalTo(KEY_DATE, selectedDate).findFirst();
        selectedMoods = ((MainApplication) getApplication()).getRealm().where(Moods.class).equalTo(KEY_DATE, selectedDate).findFirst();
    }

    @Override
    protected void onDestroy() {
        ((MainApplication) getApplication()).closeRealm();
        super.onDestroy();
    }

    public String getAllActiveMoods() {
        String activeMoods = "";
        if (selectedMoods.isAngry())
            activeMoods += getString(R.string.angry) + ", ";
        if (selectedMoods.isAnxious())
            activeMoods += getString(R.string.anxious) + ", ";
        if (selectedMoods.isBlah())
            activeMoods += getString(R.string.blah) + ", ";
        if (selectedMoods.isCalm())
            activeMoods += getString(R.string.calm) + ", ";
        if (selectedMoods.isConfused())
            activeMoods += getString(R.string.confused) + ", ";
        if (selectedMoods.isDepressed())
            activeMoods += getString(R.string.depressed) + ", ";
        if (selectedMoods.isFine())
            activeMoods += getString(R.string.fine) + ", ";
        if (selectedMoods.isHappy())
            activeMoods += getString(R.string.happy) + ", ";
        if (selectedMoods.isHyper())
            activeMoods += getString(R.string.hyper) + ", ";
        if (selectedMoods.isInLove())
            activeMoods += getString(R.string.in_love) + ", ";
        if (selectedMoods.isIrritable())
            activeMoods += getString(R.string.irritable) + ", ";
        if (selectedMoods.isSad())
            activeMoods += getString(R.string.sad) + ", ";
        if (selectedMoods.isSick())
            activeMoods += getString(R.string.sick) + ", ";
        if (selectedMoods.isStressed())
            activeMoods += getString(R.string.stressed) + ", ";
        if (selectedMoods.isTired())
            activeMoods += getString(R.string.tired) + ", ";

        if (activeMoods.length() > 2) {
            return "Moods: " + activeMoods.substring(0,activeMoods.length()-2) + "\n";
        }
        else {
            return "";
        }
    }
}
