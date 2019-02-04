package com.example.robin.angrynerds_wip.activities.note.note.logic.listener_watcher;

import android.content.DialogInterface;

import com.example.robin.angrynerds_wip.activities.note.note.logic.NoteApplicationLogic;

public class ImageOverlayListener implements DialogInterface.OnCancelListener {

    private NoteApplicationLogic mNoteApplicationLogic;

    public ImageOverlayListener(NoteApplicationLogic mNoteApplicationLogic) {
        this.mNoteApplicationLogic = mNoteApplicationLogic;
    }

    //Deletes image on cancellation of ImageOverlay for RAM optimization
    @Override
    public void onCancel(DialogInterface pDialog) {
        this.mNoteApplicationLogic.getNoteData().resetNoteBitmaps();
        this.mNoteApplicationLogic.getNoteImagePopupLogic().resetImageOverlay();
    }
}
