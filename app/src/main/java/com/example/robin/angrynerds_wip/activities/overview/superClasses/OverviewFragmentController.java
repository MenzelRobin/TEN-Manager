package com.example.robin.angrynerds_wip.activities.overview.superClasses;

import android.os.Bundle;
import android.view.View;

import com.example.robin.angrynerds_wip.activities.overview.overviewActivity.OverviewInit;

public abstract class OverviewFragmentController {
    /* Yannick-Luca Rüttgers
    Superclass for [Fragment]Controller Classes. Mainly responsible for delete Sate
    */

    protected boolean mDeleteState;
    protected boolean mMarked;
    protected OverviewFragmentInit mFragment;
    protected OverviewFragmentData mData;
    protected OverviewFragmentGui mGui;

    public OverviewFragmentController(OverviewFragmentInit pFragment, OverviewFragmentData pData, OverviewFragmentGui pGui){
        mFragment = pFragment;
        mData = pData;
        mGui = pGui;
        mDeleteState = false;
    }

    // Passes EventData to Dataobject
    public void addData(Bundle pData){
        mData.addData(pData);
    }

    // Adds View to GUI Object and applies EventData
    public void addView(View pView){
        mGui.addView(pView);
        applyData();
    }

    public OverviewFragmentClickListener getOnClickListener(){
        return new OverviewFragmentClickListener(this);
    }

    public void addOnLongClickListener(){}

    public OverviewFragmentLongClickListener getOnLongClickListener(){
        return new OverviewFragmentLongClickListener(this);
    }

    // Start Deletionprocess
    public void longClicked(){
        if(mDeleteState) {
            toggleMark();
        } else {
            ((OverviewInit) mFragment.getActivity()).getController().longClick();
            toggleMark();
        }
    }

    // Toggle Marked for Deletion
    public void toggleMark(){
        mMarked = !mMarked;
        mGui.applyMarked(mMarked);
    }

    // Set Deletestate to a specific state
    public void setDeleteState(boolean pDeleteState){
        mDeleteState = pDeleteState;
        if(mDeleteState){
            mGui.applyMarked(mMarked);
        } else {
            mMarked = false;
            mGui.hideCheckbox();
        }
    }

    public void applyData(){}

    public void addOnClickListener(){}

    public void clicked(){}

    public boolean getMarked(){
        return mMarked;
    }

    public String getTENID(){
        return mData.getID();
    }
}
