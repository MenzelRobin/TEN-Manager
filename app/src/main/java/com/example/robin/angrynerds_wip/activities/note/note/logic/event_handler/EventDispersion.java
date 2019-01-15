package com.example.robin.angrynerds_wip.activities.note.note.logic.event_handler;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.robin.angrynerds_wip.activities.note.note.logic.NoteApplicationLogic;

public class EventDispersion {

    private NoteApplicationLogic mNoteApplicationLogic;
    private int id;

    private static final int MENUGROUPTOP = 11;
    private static final int MENUITEMDELETEIMAGE = 11;

    public EventDispersion(NoteApplicationLogic noteApplicationLogic) {
        mNoteApplicationLogic = noteApplicationLogic;
    }

    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        if ( mNoteApplicationLogic.checkImageID(view.getId()) ) {
            menu.add(MENUGROUPTOP, MENUITEMDELETEIMAGE, Menu.NONE, "Bild löschen");
            id = view.getId();
        }
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        boolean result = false;

        if ( menuItem.getGroupId() == MENUITEMDELETEIMAGE ) {
            mNoteApplicationLogic.deleteImage(id);
            result = true;
        }
        return result;
    }
}