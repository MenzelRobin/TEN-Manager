package com.example.robin.angrynerds_wip.activities.overview.todoFragment;

import android.os.Bundle;

import com.example.robin.angrynerds_wip.data.models.utils.BundleKeys;
import com.example.robin.angrynerds_wip.activities.overview.superClasses.OverviewFragmentData;

public class OverviewTodoData extends OverviewFragmentData {
    /* Yannick-Luca Rüttgers
    Manages Data for the Todofragment
    */
    String mNote;
    String[] mDescription;
    boolean[] mStatus;

    // Adds the Note data to this Object
    public void addData(Bundle pData){
        super.addData(pData);
        mNote = pData.getString(BundleKeys.KEY_TODO_NOTE);
        mDescription = pData.getStringArray(BundleKeys.KEY_TODO_DESCRIPTION);
        mStatus = pData.getBooleanArray(BundleKeys.KEY_TODO_STATUS);
    }

    // Returns the Description
    public String getNote(){
        return mNote;
    }

    public String[] getDescription(){
        return mDescription;
    }

    public boolean[] getStatus(){
        return mStatus;
    }
}
