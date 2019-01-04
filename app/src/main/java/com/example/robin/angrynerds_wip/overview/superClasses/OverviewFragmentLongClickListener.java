package com.example.robin.angrynerds_wip.overview.superClasses;

import android.view.View;

public class OverviewFragmentLongClickListener implements View.OnLongClickListener {
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
