package com.example.robin.angrynerds_wip.activities.note.note.logic.listener_watcher;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.robin.angrynerds_wip.activities.note.note.logic.NoteApplicationLogic;

public class MenuItemClickListener implements Toolbar.OnMenuItemClickListener {

    private NoteApplicationLogic mNoteApplicationLogic;

    public MenuItemClickListener(NoteApplicationLogic pNoteApplicationLogic){
        mNoteApplicationLogic = pNoteApplicationLogic;
    }

    //Click listener option for toolbar menu
    @Override
    public boolean onMenuItemClick(MenuItem pItem) {
        mNoteApplicationLogic.getNoteClickHandler().onMenuItemClicked(pItem);
        return false;
    }
}
