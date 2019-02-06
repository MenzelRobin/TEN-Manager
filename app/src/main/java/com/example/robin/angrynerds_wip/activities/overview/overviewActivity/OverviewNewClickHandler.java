package com.example.robin.angrynerds_wip.activities.overview.overviewActivity;

import android.content.Intent;

import com.example.robin.angrynerds_wip.activities.event.EventActivity;
import com.example.robin.angrynerds_wip.activities.note.note.NoteActivity;
import com.example.robin.angrynerds_wip.data.models.tens.*;

public class OverviewNewClickHandler {
    /* Yannick-Luca Ruettgers
    Handles the actions for new TENs.
     */
    OverviewInit mActivity;

    public OverviewNewClickHandler(OverviewInit pActivity){
        mActivity = pActivity;
    }

    // Starts a new Activity depending on the kind of TEN
    public void newTEN(Class pClass){
        if(pClass == Todo.class){
            Intent intent = new Intent(mActivity, com.example.robin.angrynerds_wip.activities.todo.Init.class);
            mActivity.startActivity(intent);
        }
        if(pClass == Event.class){
            Intent intent = new Intent(mActivity, EventActivity.class);
            mActivity.startActivity(intent);
        }
        if(pClass == Note.class){
            Intent intent = new Intent(mActivity, NoteActivity.class);
            mActivity.startActivity(intent);
        }
    }
}
