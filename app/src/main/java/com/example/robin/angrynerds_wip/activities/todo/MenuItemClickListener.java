package com.example.robin.angrynerds_wip.activities.todo;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MenuItemClickListener implements Toolbar.OnMenuItemClickListener{
    ApplicationLogic mApplicationLogic;

    public MenuItemClickListener(ApplicationLogic pApplicationLogic){
        mApplicationLogic = pApplicationLogic;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        mApplicationLogic.onMenuItemClick(item);
        return false;
    }
}
