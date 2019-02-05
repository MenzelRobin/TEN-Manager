package com.example.robin.angrynerds_wip.activities.note.note.logic.listener_watcher;

import android.view.MotionEvent;
import android.view.View;

import com.example.robin.angrynerds_wip.activities.note.note.data.NoteConstants;
import com.example.robin.angrynerds_wip.activities.note.note.logic.NoteApplicationLogic;

// Authored by Joscha Nassenstein
public class MotionListener implements View.OnTouchListener {

    private NoteApplicationLogic mNoteApplicationLogic;
    private float mXPositionDown;
    private float mXPositionUp;
    private float mYPositionDown;
    private float mYPositionUp;

    public MotionListener(NoteApplicationLogic pNoteApplicationLogic) {
        mNoteApplicationLogic = pNoteApplicationLogic;
    }

    //TouchListener for ImageOverlay swipe gesture (next image / previous image)
    @Override
    public boolean onTouch(View pView, MotionEvent pEvent) {

        switch(pEvent.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                //Save first touch position
                mXPositionDown = pEvent.getX();
                mYPositionDown = pEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                //Save release position
                mXPositionUp = pEvent.getX();
                mYPositionUp = pEvent.getY();
                float deltaX = mXPositionUp - mXPositionDown;
                float deltaY = mYPositionUp - mYPositionDown;

                if (Math.abs(deltaX) > NoteConstants.MINIMUM_SWIPE_DISTANCE)
                {
                    // Left to Right (previous)
                    if (mXPositionUp > mXPositionDown)
                    {
                        mNoteApplicationLogic.getNoteImagePopupLogic().displayPreviousImage(pView.getId());
                    }
                    // Right to Left (next)
                    else{
                        mNoteApplicationLogic.getNoteImagePopupLogic().displayNextImage(pView.getId());
                    }
                }
                else if(Math.abs(deltaY) > NoteConstants.MINIMUM_SWIPE_DISTANCE){
                    //Top to Bottom or opposite direction
                    mNoteApplicationLogic.getNoteImagePopupLogic().closePopup();
                }
                break;
        }
        return true;
    }
}