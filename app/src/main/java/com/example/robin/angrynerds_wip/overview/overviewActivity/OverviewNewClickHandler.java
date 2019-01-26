package com.example.robin.angrynerds_wip.overview.overviewActivity;

import android.util.Log;

import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;

public class OverviewNewClickHandler {
    OverviewInit mActivity;

    public OverviewNewClickHandler(OverviewInit pActivity){
        mActivity = pActivity;
    }

    public void newTEN(Class pClass){
        if(pClass == Todo.class){
            // Todo: New TO Do
        }
        if(pClass == Event.class){
            // Todo: New TO Do
        }
        if(pClass == Note.class){
            // Todo: New TO Do
        }
    }
}
