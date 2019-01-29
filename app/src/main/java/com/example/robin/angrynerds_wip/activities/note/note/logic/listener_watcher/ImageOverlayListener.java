package com.example.robin.angrynerds_wip.activities.note.note.logic.listener_watcher;

import android.content.DialogInterface;
import android.util.Log;

import com.example.robin.angrynerds_wip.activities.note.note.logic.NoteApplicationLogic;

public class ImageOverlayListener implements DialogInterface.OnCancelListener {

    NoteApplicationLogic mNoteApplicationLogic;

    public ImageOverlayListener(NoteApplicationLogic mNoteApplicationLogic) {
        this.mNoteApplicationLogic = mNoteApplicationLogic;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        this.mNoteApplicationLogic.getNoteData().resetNoteBitmaps();
        this.mNoteApplicationLogic.getNoteImagePopupLogic().closePopup();
    }
}
