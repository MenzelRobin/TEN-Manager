package com.example.robin.angrynerds_wip.activities.note.note.logic.listener_watcher;

import android.view.MotionEvent;
import android.view.View;

import com.example.robin.angrynerds_wip.activities.note.note.data.NoteConstants;
import com.example.robin.angrynerds_wip.activities.note.note.logic.NoteApplicationLogic;

public class MotionListener implements View.OnTouchListener {

    private NoteApplicationLogic mNoteApplicationLogic;
    private float x1,x2;

    public MotionListener(NoteApplicationLogic pNoteApplicationLogic) {
        mNoteApplicationLogic = pNoteApplicationLogic;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {

        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;

                if (Math.abs(deltaX) > NoteConstants.MINIMUM_SWIPE_DISTANCE)
                {
                    // Left to Right (previous)
                    if (x2 > x1)
                    {
                        mNoteApplicationLogic.getNoteImagePopupLogic().displayPreviousImage(view.getId());
                    }
                    // Right to Left (next)
                    else
                    {
                        mNoteApplicationLogic.getNoteImagePopupLogic().displayNextImage(view.getId());
                    }
                }
                break;
        }
        return true;
    }
}