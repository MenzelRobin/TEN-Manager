package com.example.robin.angrynerds_wip.activities.event.logic.listener;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.robin.angrynerds_wip.activities.event.logic.EventApplicationLogic;

public class EventMenuItemClickListener implements Toolbar.OnMenuItemClickListener {
    EventApplicationLogic mEventApplicationLogic;

    public EventMenuItemClickListener(EventApplicationLogic pEventApplicationLogic){
        mEventApplicationLogic = pEventApplicationLogic;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        mEventApplicationLogic.onMenuItemClick(item);
        return false;
    }
}
