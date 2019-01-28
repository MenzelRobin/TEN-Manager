package com.example.robin.angrynerds_wip.activities.note.note.logic;

import com.example.robin.angrynerds_wip.activities.note.note.data.NoteData;
import com.example.robin.angrynerds_wip.activities.note.note.gui.NoteGui;

public class NoteGuiRefresherLogic {

    private NoteApplicationLogic mNoteApplicationLogic;

    private NoteGui mNoteGui;
    private NoteData mNoteData;


    public NoteGuiRefresherLogic(NoteApplicationLogic pNoteApplicationLogic){
        this.mNoteApplicationLogic = pNoteApplicationLogic;
        this.mNoteGui = pNoteApplicationLogic.getNoteGui();
        this.mNoteData = pNoteApplicationLogic.getNoteData();
    }

    public void setNoteGui(NoteGui mNoteGui) {
        this.mNoteGui = mNoteGui;
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
