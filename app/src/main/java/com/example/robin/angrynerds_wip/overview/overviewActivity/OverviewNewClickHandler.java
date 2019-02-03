package com.example.robin.angrynerds_wip.overview.overviewActivity;

import android.content.Intent;

import com.example.robin.angrynerds_wip.activities.note.note.NoteActivity;
import com.example.robin.angrynerds_wip.data.models.tens.*;

public class OverviewNewClickHandler {
    OverviewInit mActivity;

    public OverviewNewClickHandler(OverviewInit pActivity){
        mActivity = pActivity;
    }

    public void newTEN(Class pClass){
        if(pClass == Todo.class){
            Intent intent = new Intent(mActivity, com.example.robin.angrynerds_wip.activities.todo.Init.class);
            mActivity.startActivity(intent);
        }
        if(pClass == Event.class){
            Intent intent = new Intent(mActivity, com.example.robin.angrynerds_wip.activities.event.Init.class);
            mActivity.startActivity(intent);
        }
        if(pClass == Note.class){
            Intent intent = new Intent(mActivity, NoteActivity.class);
            mActivity.startActivity(intent);
        }
    }
}
