package com.example.robin.angrynerds_wip.overview.superClasses;

import android.view.View;

public class OverviewFragmentClickListener implements View.OnClickListener{
    private OverviewFragmentController mController;

    public OverviewFragmentClickListener(OverviewFragmentController pController){
        mController = pController;
    }

    @Override
    public void onClick(View view){
        mController.clicked();
    }
}
