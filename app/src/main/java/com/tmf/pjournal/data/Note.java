package com.tmf.pjournal.data;

import io.realm.RealmObject;

public class Note extends RealmObject {
    // TODO make date primaryKey
    private long dateMillis;
    private String noteText;

    public Note() {}

    public long getDateMillis() {
        return dateMillis;
    }

    public void setDateMillis(long dateMillis) {
        this.dateMillis = dateMillis;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

}
