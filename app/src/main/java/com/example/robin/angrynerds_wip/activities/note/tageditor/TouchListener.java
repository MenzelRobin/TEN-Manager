package com.example.robin.angrynerds_wip.activities.note.tageditor;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class TouchListener implements View.OnTouchListener {

    private Activity mActivity;
    private ApplicationLogic mApplicationLogic;

    TouchListener(Activity pActivity, ApplicationLogic pApplicationLogic) {
        mActivity = pActivity;
        mApplicationLogic = pApplicationLogic;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        //view.performClick();
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        if(MotionEvent.ACTION_UP == event.getAction())
            mApplicationLogic.onTagTextClicked();
        return false;
    }
}
