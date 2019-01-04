package com.example.robin.angrynerds_wip.overview.overviewActivity;

import android.view.View;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;

public class OverviewClickListener implements View.OnClickListener {
    private OverviewController mController;

    public OverviewClickListener(OverviewController pController){
        mController = pController;
    }

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
