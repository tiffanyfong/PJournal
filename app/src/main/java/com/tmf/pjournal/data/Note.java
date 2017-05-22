package com.tmf.pjournal.data;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

public class Note extends RealmObject {
    @Ignore
    public static final String KEY_DATE = "date";
    @Ignore
    public static final String KEY_PERIOD_STARTED = "periodStarted";
    @Ignore
    public static final String KEY_PERIOD_ENDED = "periodEnded";

    private String date;
    private String noteText;
    private Symptoms symptoms;
    private Moods moods;
    private Hygiene hygiene;
    private boolean periodStarted;
    private boolean periodEnded;

    public Note() {}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public Symptoms getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(Symptoms symptoms) {
        this.symptoms = symptoms;
    }

    public Moods getMoods() {
        return moods;
    }

    public void setMoods(Moods moods) {
        this.moods = moods;
    }

    public Hygiene getHygiene() {
        return hygiene;
    }

    public void setHygiene(Hygiene hygiene) {
        this.hygiene = hygiene;
    }

    public boolean isPeriodStarted() {
        return periodStarted;
    }

    public void setPeriodStarted(boolean periodStarted) {
        this.periodStarted = periodStarted;
    }

    public boolean isPeriodEnded() {
        return periodEnded;
    }

    public void setPeriodEnded(boolean periodEnded) {
        this.periodEnded = periodEnded;
    }
}
