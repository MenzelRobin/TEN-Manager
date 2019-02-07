package com.example.robin.angrynerds_wip.activities.note.note.logic;

import android.widget.Toast;

import com.example.robin.angrynerds_wip.activities.note.note.data.NoteData;
import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.services.ImageService;

// Authored by Jan Beilfuss
public class NoteAsyncLoadingLogic {

    private NoteApplicationLogic mNoteApplicationLogic;
    private NoteData mNoteData;

    public NoteAsyncLoadingLogic(NoteApplicationLogic pNoteApplicationLogic) {
        this.mNoteApplicationLogic = pNoteApplicationLogic;
        this. mNoteData = pNoteApplicationLogic.getNoteData();
    }

    public void addAsyncPreviewImage(Image pImage) {
        mNoteApplicationLogic.getNoteData().getNoteDataGui().getPreviewImages().add(pImage);
        mNoteApplicationLogic.getNoteGui().addSingleAnimatedImage(mNoteData.getActivity(), pImage, mNoteApplicationLogic.getNoteListenerInitializer().getClickListener());
    }

    public void startLoadingSpinner() {
        mNoteApplicationLogic.getNoteGui().disableAll();
        mNoteApplicationLogic.getNoteGui().startLoadingSpinner();
    }

    public void stopLoadingSpinner() {
        mNoteApplicationLogic.getNoteGui().stopLoadingSpinner();
        mNoteApplicationLogic.getNoteGui().enableAll();
    }
}
