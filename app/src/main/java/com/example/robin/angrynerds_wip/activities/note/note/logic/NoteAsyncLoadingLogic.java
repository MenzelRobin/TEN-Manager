package com.example.robin.angrynerds_wip.activities.note.note.logic;

import android.graphics.Bitmap;
import android.view.View;
import android.view.Window;

import com.example.robin.angrynerds_wip.activities.note.note.data.NoteData;
import com.example.robin.angrynerds_wip.activities.note.note.gui.ImageContainer;
import com.example.robin.angrynerds_wip.activities.note.note.gui.ImageOverlay;
import com.example.robin.angrynerds_wip.activities.note.note.gui.NoteGui;
import com.example.robin.angrynerds_wip.data.models.utils.Image;

public class NoteAsyncLoadingLogic {

    NoteApplicationLogic mNoteApplicationLogic;
    NoteData mNoteData;
    NoteGui mNoteGui;

    public NoteAsyncLoadingLogic(NoteApplicationLogic pNoteApplicationLogic) {
        this.mNoteApplicationLogic = pNoteApplicationLogic;
        this. mNoteData = pNoteApplicationLogic.getNoteData();
        this. mNoteGui = pNoteApplicationLogic.getNoteGui();
    }

    public void addAsyncPreviewImage(Image pImage) {
        mNoteData.addImageContainer(pImage);
        ImageContainer imageContainer = (ImageContainer) mNoteData.getNoteImageContainers().get(mNoteData.getNoteImageContainers().size() - 2);
        mNoteGui.addSingleAnimatedImage(imageContainer);
        mNoteApplicationLogic.getNoteListenerInitializer().initListener();
    }

    //Irgendwas mit GUI und Async eventuell mit addAsyncImage in eine Klasse
    public void startLoadingSpinner() {
        mNoteGui.disableAll();
        mNoteGui.startLoadingSpinner();
    }

    public void stopLoadingSpinner() {
        mNoteGui.stopLoadingSpinner();
        mNoteGui.enableAll();
    }
}
