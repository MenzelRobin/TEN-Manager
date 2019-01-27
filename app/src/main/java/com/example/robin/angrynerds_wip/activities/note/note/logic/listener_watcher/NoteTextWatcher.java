package com.example.robin.angrynerds_wip.activities.note.note.logic.listener_watcher;

import android.text.Editable;
import android.view.View;

import com.example.robin.angrynerds_wip.activities.note.note.logic.NoteApplicationLogic;

public class NoteTextWatcher implements android.text.TextWatcher {

    private NoteApplicationLogic mNoteApplicationLogic;
    private View mView;

    public NoteTextWatcher(NoteApplicationLogic pNoteApplicationLogic, View pView){
        mNoteApplicationLogic = pNoteApplicationLogic;
        mView = pView;
    }

    @Override
    public void onTextChanged(CharSequence pCharSequence, int pStart, int pCount, int pAfter){
        mNoteApplicationLogic.getNoteClickHandler().onTextChanged(pCharSequence.toString(), mView);
    }

    @Override
    public void afterTextChanged(Editable pS){
    }

    @Override
    public void beforeTextChanged(CharSequence pS, int pStart, int pCount, int pAfter){
    }
}
