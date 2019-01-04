package com.example.robin.angrynerds_wip.overview.superClasses;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.robin.angrynerds_wip.overview.overviewActivity.OverviewInit;

public abstract class OverviewFragmentController {

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

    // Passes Data to Dataobject
    public void addData(Bundle pData){
        mData.addData(pData);
    }

    // Adds View to GUI Object and applies Data
    public void addView(View pView){
        mGui.addView(pView);
        applyData();
    }

    public void applyData(){}

    public void addOnClickListener(){}

    public OverviewFragmentClickListener getOnClickListener(){
        return new OverviewFragmentClickListener(this);
    }

    public void addOnLongClickListener(){}

    public OverviewFragmentLongClickListener getOnLongClickListener(){
        return new OverviewFragmentLongClickListener(this);
    }

    public void clicked(){}

    public void longClicked(){
        if(mDeleteState) {
            toggleMark();
        } else {
            ((OverviewInit) mFragment.getActivity()).getController().longClick();
            toggleMark();
        }
    }

    public void toggleMark(){
        mMarked = !mMarked;
        mGui.applyMarked(mMarked);
    }

    public void setDeleteState(boolean pDeleteState){
        mDeleteState = pDeleteState;
        if(mDeleteState){
            mGui.applyMarked(mMarked);
        } else {
            mMarked = false;
            mGui.hideCheckbox();
        }
    }

    public boolean getMarked(){
        return mMarked;
    }

    public String getTENID(){
        return mData.getID();
    }
}