package com.example.robin.angrynerds_wip.overview.noteFragment;

import android.os.Bundle;

import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentData;

public class OverviewNoteData extends OverviewFragmentData {

    String mDescription;

    // Adds the Note data to this Object
    public void addData(Bundle pData){
        super.addData(pData);
        mDescription = pData.getString("Description");
    }

    // Returns the Description
    public String getDescription(){
        return mDescription;
    }
}
