package com.example.robin.angrynerds_wip.activities.note.note.logic.event_handler;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.robin.angrynerds_wip.activities.note.note.logic.NoteApplicationLogic;

public class MenuItemClickListener implements Toolbar.OnMenuItemClickListener {
    private NoteApplicationLogic mNoteApplicationLogic;

    public MenuItemClickListener(NoteApplicationLogic noteApplicationLogic){
        mNoteApplicationLogic = noteApplicationLogic;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        mNoteApplicationLogic.onMenuItemClicked(item);
        return false;
    }
}
