package com.example.robin.angrynerds_wip.activities.note.note.logic.listener_watcher;

import com.example.robin.angrynerds_wip.activities.note.note.gui.NoteGui;
import com.example.robin.angrynerds_wip.activities.note.note.logic.NoteApplicationLogic;

public class NoteListenerInitializer {

    private NoteApplicationLogic mNoteApplicationLogic;
    private ClickListener mClickListener;

    public NoteListenerInitializer(NoteApplicationLogic pNoteApplicationLogic) {
        this.mNoteApplicationLogic = pNoteApplicationLogic;
        mClickListener = new ClickListener(pNoteApplicationLogic);
    }

    public ClickListener getClickListener(){
        return mClickListener;
    }

    public void initListener() {

        MenuItemClickListener menuItemClickListener = new MenuItemClickListener(mNoteApplicationLogic);
        NoteGui mNoteGui = mNoteApplicationLogic.getNoteGui();

        mNoteGui.getNoteImageContainer().setOnClickListener(mClickListener);
        mNoteGui.getNoteTags().setOnClickListener(mClickListener);
        mNoteGui.getNoteTitle().addTextChangedListener(new NoteTextWatcher(mNoteApplicationLogic, mNoteGui.getNoteTitle()));
        mNoteGui.getNoteDescription().addTextChangedListener(new NoteTextWatcher(mNoteApplicationLogic, mNoteGui.getNoteDescription()));
        mNoteGui.getToolbar().setNavigationOnClickListener(mClickListener);
        mNoteGui.getToolbar().setOnMenuItemClickListener(menuItemClickListener);
    }
}
