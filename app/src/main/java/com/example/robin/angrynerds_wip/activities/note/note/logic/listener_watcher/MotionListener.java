package com.example.robin.angrynerds_wip.activities.note.note.logic.listener_watcher;

import android.view.MotionEvent;
import android.view.View;

import com.example.robin.angrynerds_wip.activities.note.note.data.NoteConstants;
import com.example.robin.angrynerds_wip.activities.note.note.logic.NoteApplicationLogic;

public class MotionListener implements View.OnTouchListener {

    private NoteApplicationLogic mNoteApplicationLogic;
    private float mXPositionDown;
    private float mXPositionUp;

    public MotionListener(NoteApplicationLogic pNoteApplicationLogic) {
        mNoteApplicationLogic = pNoteApplicationLogic;
    }

    @Override
    public boolean onTouch(View pView, MotionEvent pEvent) {

        switch(pEvent.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                mXPositionDown = pEvent.getX();  //Save first touch position
                break;
            case MotionEvent.ACTION_UP:
                mXPositionUp = pEvent.getX();  //Save release position
                float deltaX = mXPositionUp - mXPositionDown;

                if (Math.abs(deltaX) > NoteConstants.MINIMUM_SWIPE_DISTANCE)
                {
                    // Left to Right (previous)
                    if (mXPositionUp > mXPositionDown)
                    {
                        mNoteApplicationLogic.getNoteImagePopupLogic().displayPreviousImage(pView.getId());
                    }
                    // Right to Left (next)
                    else
                    {
                        mNoteApplicationLogic.getNoteImagePopupLogic().displayNextImage(pView.getId());
                    }
                }
                break;
        }
        return true;
    }
}