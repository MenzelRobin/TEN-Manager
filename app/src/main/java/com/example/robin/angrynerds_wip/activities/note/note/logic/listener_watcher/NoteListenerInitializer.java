package com.example.robin.angrynerds_wip.activities.note.note.logic.listener_watcher;

import com.example.robin.angrynerds_wip.activities.note.note.gui.GraphicsContainer;
import com.example.robin.angrynerds_wip.activities.note.note.gui.NoteGui;
import com.example.robin.angrynerds_wip.activities.note.note.logic.NoteApplicationLogic;
import com.example.robin.angrynerds_wip.activities.note.note.logic.listener_watcher.ClickListener;
import com.example.robin.angrynerds_wip.activities.note.note.logic.listener_watcher.MenuItemClickListener;
import com.example.robin.angrynerds_wip.activities.note.note.logic.listener_watcher.NoteTextWatcher;

public class NoteListenerInitializer {

    NoteApplicationLogic mNoteApplicationLogic;
    ClickListener mClickListener;

    public NoteListenerInitializer(NoteApplicationLogic pNoteApplicationLogic) {
        this.mNoteApplicationLogic = pNoteApplicationLogic;
        mClickListener = new ClickListener(pNoteApplicationLogic);
    }

    public ClickListener getClickListener(){
        return mClickListener;
    }

    //Listener Manager Klasse
    public void initListener() {

        MenuItemClickListener menuItemClickListener = new MenuItemClickListener(mNoteApplicationLogic);
        NoteGui mNoteGui = mNoteApplicationLogic.getNoteGui();

        mNoteGui.getNoteTitle().setOnClickListener(mClickListener);
        mNoteGui.getNoteImageContainer().setOnClickListener(mClickListener);
        mNoteGui.getNoteTags().setOnClickListener(mClickListener);
        mNoteGui.getNoteTitle().addTextChangedListener(new NoteTextWatcher(mNoteApplicationLogic, mNoteGui.getNoteTitle()));
        mNoteGui.getNoteDescription().addTextChangedListener(new NoteTextWatcher(mNoteApplicationLogic, mNoteGui.getNoteDescription()));
        mNoteGui.getToolbar().setNavigationOnClickListener(mClickListener);
        mNoteGui.getToolbar().setOnMenuItemClickListener(menuItemClickListener);
    }
}
