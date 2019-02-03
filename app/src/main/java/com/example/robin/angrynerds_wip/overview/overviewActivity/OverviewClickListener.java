package com.example.robin.angrynerds_wip.overview.overviewActivity;

import android.view.View;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.data.models.tens.*;

public class OverviewClickListener implements View.OnClickListener {

    private OverviewController mController;

    public OverviewClickListener(OverviewController pController, OverviewGui pGui){
        mController = pController;
        pGui.getShowAll().setOnClickListener(this);
        pGui.getShowTodo().setOnClickListener(this);
        pGui.getShowEvent().setOnClickListener(this);
        pGui.getShowNote().setOnClickListener(this);
    }

    // Calls the controllers show Method with different Classes
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.id_overview_button_all:
                mController.show(TEN.class);
                break;
            case R.id.id_overview_button_todo:
                mController.show(Todo.class);
                break;
            case R.id.id_overview_button_event:
                mController.show(Event.class);
                break;
            case R.id.id_overview_button_note:
                mController.show(Note.class);
                break;
        }
    }
}
