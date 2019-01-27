package com.example.robin.angrynerds_wip.activities.note.note.logic;

import com.example.robin.angrynerds_wip.activities.note.note.data.NoteData;
import com.example.robin.angrynerds_wip.activities.note.note.gui.NoteGui;

public class NoteGuiRefresherLogic {

    NoteApplicationLogic mNoteApplicationLogic;
    NoteGui mNoteGui;
    NoteData mNoteData;


    public NoteGuiRefresherLogic(NoteApplicationLogic pNoteApplicationLogic){
        this.mNoteApplicationLogic = pNoteApplicationLogic;
        this.mNoteGui = pNoteApplicationLogic.getNoteGui();
        this.mNoteData = pNoteApplicationLogic.getNoteData();
    }

    public void dataToGui() {
        //Set colors
        mNoteGui.setColors(mNoteData.getNote().getColor(), mNoteData.getNote().getAccentColor());
        //Set content
        mNoteGui.setNoteTitle(mNoteData.getNote().getTitle());
        mNoteGui.setNoteDescription(mNoteData.getNote().getDescription());
        mNoteGui.setNoteTags(mNoteData.getNote().getTags());

        refreshImages();
    }

    public void refreshImages() {
        mNoteGui.getNoteImageContainer().removeAllViews();
        mNoteGui.setNoteImageContainer(mNoteData.getNoteImageContainers());
        mNoteApplicationLogic.getNoteListenerInitializer().setAllImageListeners();
    }
}
