package com.example.robin.angrynerds_wip.activities.note.note.logic;

import com.example.robin.angrynerds_wip.activities.note.note.data.NoteData;
import com.example.robin.angrynerds_wip.data.models.utils.Image;

public class NoteAsyncLoadingLogic {

    NoteApplicationLogic mNoteApplicationLogic;
    NoteData mNoteData;

    public NoteAsyncLoadingLogic(NoteApplicationLogic pNoteApplicationLogic) {
        this.mNoteApplicationLogic = pNoteApplicationLogic;
        this. mNoteData = pNoteApplicationLogic.getNoteData();
    }

    public void addAsyncPreviewImage(Image pImage) {
        mNoteApplicationLogic.getNoteData().getNoteDataGui().addPreviewImageFromOriginal(pImage);
        Image image = mNoteApplicationLogic.getNoteData().getNoteDataGui().getLatestImage();
        mNoteApplicationLogic.getNoteGui().addSingleAnimatedImage(mNoteData.getActivity(), image, mNoteApplicationLogic.getNoteListenerInitializer().getClickListener());
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
