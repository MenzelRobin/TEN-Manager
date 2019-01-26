package com.example.robin.angrynerds_wip.overview.todoFragment;

import android.os.Bundle;
import android.util.Log;

import com.example.robin.angrynerds_wip.data.models.utils.BundleKeys;
import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentData;

public class OverviewTodoData extends OverviewFragmentData {

    String mNote;
    String[] mDescription;
    boolean[] mStatus;

    // Adds the Note data to this Object
    public void addData(Bundle pData){
        super.addData(pData);
        mNote = pData.getString(BundleKeys.keyTodoNote);
        mDescription = pData.getStringArray(BundleKeys.keyTodoDescription);
        mStatus = pData.getBooleanArray(BundleKeys.keyTodoStatus);
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
