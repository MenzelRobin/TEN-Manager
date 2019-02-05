package com.example.robin.angrynerds_wip.activities.note.notetags;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

// Authored by Joscha Nassenstein
class MenuItemClickListener implements Toolbar.OnMenuItemClickListener {

    ApplicationLogic mApplicationLogic;

    MenuItemClickListener(ApplicationLogic pApplicationLogic){
        mApplicationLogic = pApplicationLogic;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        mApplicationLogic.onMenuItemClick(item);
        return false;
    }
}
