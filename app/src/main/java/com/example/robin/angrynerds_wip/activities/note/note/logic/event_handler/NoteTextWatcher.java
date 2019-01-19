package com.example.robin.angrynerds_wip.activities.note.note.logic.event_handler;

import android.text.Editable;
import android.view.View;

import com.example.robin.angrynerds_wip.activities.note.note.logic.NoteApplicationLogic;

public class NoteTextWatcher implements android.text.TextWatcher {
    private NoteApplicationLogic mNoteApplicationLogic;
    private View mView;

    public NoteTextWatcher(NoteApplicationLogic noteApplicationLogic, View view){
        mNoteApplicationLogic = noteApplicationLogic;
        mView = view;
    }

    @Override
    public void onTextChanged(CharSequence c, int start, int count, int after){
        mNoteApplicationLogic.onTextChanged(c.toString(), mView);
    }

    @Override
    public void afterTextChanged(Editable s){
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after){

    }
}
