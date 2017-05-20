package com.tmf.pjournal.data;

import io.realm.RealmObject;

public class Note extends RealmObject {
    // TODO make date primaryKey
    private String date;
    private String noteText;

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

}
