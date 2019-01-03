package com.example.robin.angrynerds_wip.overview.superClasses;

import android.os.Bundle;
import android.view.View;

import com.example.robin.angrynerds_wip.overview.noteFragment.OverviewNoteData;
import com.example.robin.angrynerds_wip.overview.noteFragment.OverviewNoteGui;
import com.example.robin.angrynerds_wip.overview.noteFragment.OverviewNoteInit;

public abstract class OverviewFragmentController {
    protected OverviewFragmentInit mFragment;
    protected OverviewFragmentData mData;
    protected OverviewFragmentGui mGui;

    public OverviewFragmentController(OverviewFragmentInit pFragment, OverviewFragmentData pData, OverviewFragmentGui pGui){
        mFragment = pFragment;
        mData = pData;
        mGui = pGui;
    }

    // Passes Data to Dataobject
    public void addData(Bundle pData){
        mData.addData(pData);
    }

    public void addView(View pView){
        mGui.addView(pView);
    }
}
