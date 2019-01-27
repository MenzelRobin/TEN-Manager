package com.example.robin.angrynerds_wip.activities.note.note.logic;

import android.util.Log;

import com.example.robin.angrynerds_wip.activities.note.note.gui.GraphicsContainer;
import com.example.robin.angrynerds_wip.activities.note.note.gui.NoteGui;
import com.example.robin.angrynerds_wip.activities.note.note.logic.event_handler.ClickListener;
import com.example.robin.angrynerds_wip.activities.note.note.logic.event_handler.MenuItemClickListener;
import com.example.robin.angrynerds_wip.activities.note.note.logic.event_handler.NoteTextWatcher;

public class NoteListenerInitializer {

    NoteApplicationLogic mNoteApplicationLogic;
    ClickListener mClickListener;

    public NoteListenerInitializer(NoteApplicationLogic pNoteApplicationLogic) {
        this.mNoteApplicationLogic = pNoteApplicationLogic;
        mClickListener = new ClickListener(pNoteApplicationLogic);
    }

    //Listener Manager Klasse
    public void initListener() {

        MenuItemClickListener menuItemClickListener = new MenuItemClickListener(mNoteApplicationLogic);
        NoteGui mNoteGui = mNoteApplicationLogic.getNoteGui();

        mNoteGui.getNoteTitle().setOnClickListener(mClickListener);
        mNoteGui.getNoteImageContainer().setOnClickListener(mClickListener);
        mNoteGui.getNoteTags().setOnClickListener(mClickListener);
        setAllImageListeners();
        mNoteGui.getNoteTitle().addTextChangedListener(new NoteTextWatcher(mNoteApplicationLogic, mNoteGui.getNoteTitle()));
        mNoteGui.getNoteDescription().addTextChangedListener(new NoteTextWatcher(mNoteApplicationLogic, mNoteGui.getNoteDescription()));
        mNoteGui.getToolbar().setNavigationOnClickListener(mClickListener);
        mNoteGui.getToolbar().setOnMenuItemClickListener(menuItemClickListener);
    }

    //Listener Manager Klasse
    public void setAllImageListeners() {
        for (GraphicsContainer mImage : mNoteApplicationLogic.getNoteData().getNoteImageContainers()) {
            setImageClickListener(mImage);
        }
    }

    //Sets ClickListener on mImageContainer
    private void setImageClickListener(GraphicsContainer pImageContainer) {
        pImageContainer.getImageContainer().setOnClickListener(mClickListener);
    }
}
