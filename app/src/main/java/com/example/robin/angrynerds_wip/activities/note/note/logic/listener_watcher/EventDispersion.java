package com.example.robin.angrynerds_wip.activities.note.note.logic.listener_watcher;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.robin.angrynerds_wip.activities.note.note.logic.NoteApplicationLogic;

public class EventDispersion {

    private NoteApplicationLogic mNoteApplicationLogic;
    private int mId;

    private static final int MENUGROUPTOP = 11;
    private static final int MENUITEMDELETEIMAGE = 11;

    public EventDispersion(NoteApplicationLogic pNoteApplicationLogic) {
        mNoteApplicationLogic = pNoteApplicationLogic;
    }

    public void onCreateContextMenu(ContextMenu pMenu, View pView, ContextMenu.ContextMenuInfo pMenuInfo) {
        //if ( mNoteApplicationLogic.checkImageID(pView.getId()) ) {
            pMenu.add(MENUGROUPTOP, MENUITEMDELETEIMAGE, Menu.NONE, "Bild löschen");
            mId = pView.getId();
        //}
    }

    public boolean onContextItemSelected(MenuItem pMenuItem) {
        boolean result = false;

        if ( pMenuItem.getGroupId() == MENUITEMDELETEIMAGE ) {
            mNoteApplicationLogic.deleteImage(mId);
            result = true;
        }
        return result;
    }
}