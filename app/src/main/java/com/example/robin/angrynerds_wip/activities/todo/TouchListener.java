package com.example.robin.angrynerds_wip.activities.todo;

import android.view.MotionEvent;
import android.view.View;

// Author: Sertan Soner Cetin
public class TouchListener  implements View.OnTouchListener {

    private TodoApplicationLogic mTodoApplicationLogic;

    TouchListener(TodoApplicationLogic todoApplicationLogic) {
        mTodoApplicationLogic = todoApplicationLogic;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {

        mTodoApplicationLogic.updateProgress();

        if(MotionEvent.ACTION_UP == event.getAction())
            mTodoApplicationLogic.onEditTextClicked();
        return false;
    }
}