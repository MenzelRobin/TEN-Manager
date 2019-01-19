package com.example.robin.angrynerds_wip.activities.event;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.robin.angrynerds_wip.R;

public class MenuItemClickListener implements Toolbar.OnMenuItemClickListener {
    EventApplicationLogic mEventApplicationLogic;

    public MenuItemClickListener(EventApplicationLogic pEventApplicationLogic){
        mEventApplicationLogic = pEventApplicationLogic;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        mEventApplicationLogic.onMenuItemClick(item);
        return false;
    }
}
