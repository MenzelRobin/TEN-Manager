package com.example.robin.angrynerds_wip.activities.note.note.logic;

import com.example.robin.angrynerds_wip.activities.note.note.data.NoteData;
import com.example.robin.angrynerds_wip.activities.note.note.gui.NoteGui;

public class NoteGuiRefresherLogic {

    private NoteApplicationLogic mNoteApplicationLogic;

    private NoteData mNoteData;


    public NoteGuiRefresherLogic(NoteApplicationLogic pNoteApplicationLogic){
        this.mNoteApplicationLogic = pNoteApplicationLogic;
        this.mNoteData = pNoteApplicationLogic.getNoteData();
    }

    public void dataToGui() {
        //Set colors
        mNoteApplicationLogic.getNoteGui().setColors(mNoteData.getNote().getColor(), mNoteData.getNote().getAccentColor());
        //Set content
        mNoteApplicationLogic.getNoteGui().setNoteTitle(mNoteData.getNote().getTitle());
        mNoteApplicationLogic.getNoteGui().setNoteDescription(mNoteData.getNote().getDescription());
        mNoteApplicationLogic.getNoteGui().setNoteTags(mNoteData.getNote().getTags());

        refreshImages();
    }

    public void refreshImages() {
        mNoteApplicationLogic.getNoteGui().getNoteImageContainer().removeAllViews();
        mNoteApplicationLogic.getNoteGui().setNoteImageContainer(mNoteData.getNoteImageContainers());
        mNoteApplicationLogic.getNoteListenerInitializer().setAllImageListeners();
    }
}
