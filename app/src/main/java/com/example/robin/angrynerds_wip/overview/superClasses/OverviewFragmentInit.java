package com.example.robin.angrynerds_wip.overview.superClasses;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;


public abstract class OverviewFragmentInit extends Fragment {
    /* Yannick-Luca Rüttgers
    Superclass for [Fragment]Init classes
    */

    protected OverviewFragmentData mData;
    protected OverviewFragmentGui mGui;
    protected OverviewFragmentController mController;

    public OverviewFragmentInit(){
        initData();
        initGui();
        initController();
    }

    public void initData(){}

    public void initGui(){}

    public void initController(){}

    public void onCreateView(Bundle pArguments, View pView){
        mController.addData(pArguments);
        mController.addView(pView);
        mController.addOnClickListener();
        mController.addOnLongClickListener();
    }

    public OverviewFragmentController getController(){
        return mController;
    }
}
