package com.example.robin.angrynerds_wip.activities.note.note;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MenuItemClickListener implements Toolbar.OnMenuItemClickListener {
    private ApplicationLogic mApplicationLogic;

    public MenuItemClickListener(ApplicationLogic applicationLogic){
        mApplicationLogic = applicationLogic;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        mApplicationLogic.onMenuItemClick(item);
        return false;
    }
}
