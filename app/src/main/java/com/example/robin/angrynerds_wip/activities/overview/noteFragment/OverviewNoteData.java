package com.example.robin.angrynerds_wip.activities.overview.noteFragment;

import android.os.Bundle;

import com.example.robin.angrynerds_wip.data.models.utils.BundleKeys;
import com.example.robin.angrynerds_wip.activities.overview.superClasses.OverviewFragmentData;

public class OverviewNoteData extends OverviewFragmentData {
    /* Yannick-Luca Ruettgers
    Contains the Data for the Note Fragment
     */

    String mDescription;

    // Adds the Note data to this Object
    public void addData(Bundle pData){
        super.addData(pData);
        mDescription = pData.getString(BundleKeys.KEY_NOTE_DESCRIPTION);
    }

    // Returns the Description
    public String getDescription(){
        return mDescription;
    }
}
