package com.example.robin.angrynerds_wip.overview.superClasses;

import android.view.View;

public class OverviewFragmentLongClickListener implements View.OnLongClickListener {
    /* Fabia Schmid
    Manages long Clicks on Fragments. Long Clicks start the deletionprocess
     */

    OverviewFragmentController mController;

    public OverviewFragmentLongClickListener(OverviewFragmentController pController){
        mController = pController;
    }

    @Override
    public boolean onLongClick(View view) {
        mController.longClicked();
        return true;
    }
}
