package com.tmf.pjournal.noteFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.tmf.pjournal.R;
import com.tmf.pjournal.activity.NoteActivity;
import com.tmf.pjournal.data.Note;

import io.realm.Realm;

public class FragmentNote extends Fragment {
    public static final String TAG = "FragmentNote";
    private EditText etNoteText;
    private Note note;
    private Realm realm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_note, container, false);
        etNoteText = (EditText) v.findViewById(R.id.etNoteText);
        realm = ((NoteActivity) getActivity()).getAppRealmNote();
        note = realm.where(Note.class).equalTo("date", ((NoteActivity) getActivity()).getDate()).findFirst();
        setNoteText();
        return v;
    }

    private void setNoteText() {
        if (note != null) {
            etNoteText.setText(note.getNoteText());
        }
    }

    private String getNoteText() {
        return etNoteText.getText().toString();
    }

    public void updateRealm() {
        if (note != null) {
            realm.beginTransaction();
            note.setNoteText(getNoteText());
            realm.commitTransaction();
        }
        else {
            realm.beginTransaction();
            note = realm.createObject(Note.class);
            note.setDate(((NoteActivity) getActivity()).getDate());
            note.setNoteText(getNoteText());
            realm.commitTransaction();
        }
    }
}
