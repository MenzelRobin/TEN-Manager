package com.example.robin.angrynerds_wip.activities.note.tageditor;

import android.view.MotionEvent;
import android.view.View;

public class TouchListener implements View.OnTouchListener {

    private ApplicationLogic mApplicationLogic;

    TouchListener(ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if(MotionEvent.ACTION_UP == event.getAction())
            mApplicationLogic.onTagTextClicked();
        return false;
    }
}
