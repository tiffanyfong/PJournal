package com.tmf.pjournal.noteFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.tmf.pjournal.R;
import com.tmf.pjournal.activity.NoteActivity;
import com.tmf.pjournal.data.Note;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

import static com.tmf.pjournal.data.Note.KEY_DATE;

public class FragmentNote extends Fragment {
    public static final String TAG = "FragmentNote";
    private Note note;
    private Realm realm;

    @BindView(R.id.etNoteText)
    EditText etNoteText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_note, container, false);
        ButterKnife.bind(this, v);
        realm = ((NoteActivity) getActivity()).getRealm();
        note = realm.where(Note.class).equalTo(KEY_DATE, ((NoteActivity) getActivity()).getDate()).findFirst();
        setNoteText();
        return v;
    }

    private void setNoteText() {
        if (note != null) {
            etNoteText.setText(note.getNoteText());
        }
    }

    private String getEtNoteText() {
        return etNoteText.getText().toString();
    }

    public void updateRealm() {
        if (note == null) {
            realm.beginTransaction();
            note = realm.createObject(Note.class);
            note.setDate(((NoteActivity) getActivity()).getDate());
            realm.commitTransaction();
        }
        realm.beginTransaction();
        note.setNoteText(getEtNoteText());
        realm.commitTransaction();
    }
}
