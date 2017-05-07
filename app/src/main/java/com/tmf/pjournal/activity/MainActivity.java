package com.tmf.pjournal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;

import com.tmf.pjournal.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

// TODO rename to CalendarActivity?
public class MainActivity extends BaseActivity {

    public static final String KEY_DATE_MILLIS = "KEY_DATE_MILLIS";

    @BindView(R.id.calendar)
    CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        ButterKnife.bind(this);
        disableNavItem(R.id.nav_calendar);
    }

    @OnClick(R.id.tvCalendarNote) public void notePressed() {
        Intent showNoteActivity = new Intent(MainActivity.this, NoteActivity.class);
        showNoteActivity.putExtra(KEY_DATE_MILLIS,calendar.getDate());
        startActivity(showNoteActivity);
    }



}
