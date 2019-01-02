package com.example.robin.angrynerds_wip.overview.noteFragment;

import android.view.View;

public class OverviewNoteClickListener implements View.OnClickListener {

    OverviewNoteController mController;

    // Constructor
    OverviewNoteClickListener(OverviewNoteController pController){
        mController = pController;
    }

    // Calls the clicked method if an onClickEvent is triggered
    @Override
    public void onClick(View view) {
        mController.clicked();
    }
}
