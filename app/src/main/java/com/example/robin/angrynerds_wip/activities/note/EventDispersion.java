package com.example.robin.angrynerds_wip.activities.note;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

class EventDispersion {

    private ApplicationLogic mApplicationLogic;
    private int id;

    private static final int MENUGROUPTOP = 11;
    private static final int MENUITEMDELETEIMAGE = 11;

    EventDispersion(ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
    }

    void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        // menu.setHeaderTitle("my context menu");
        if ( mApplicationLogic.checkImageID(view.getId()) ) {
            menu.add(MENUGROUPTOP, MENUITEMDELETEIMAGE, Menu.NONE, "Delete Image");
            id = view.getId();
            mApplicationLogic.displayToast(String.valueOf(id));
        }
    }

    boolean onContextItemSelected(MenuItem menuItem) {
        boolean result = false;

        if ( menuItem.getGroupId() == MENUITEMDELETEIMAGE ) {
            mApplicationLogic.deleteImage(id);
            result = true;
        }
        return result;
    }
}
