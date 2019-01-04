package com.example.robin.angrynerds_wip.overview.header;

import android.view.View;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.overview.overviewActivity.OverviewController;

public class OverviewHeaderClickListener implements View.OnClickListener {

    OverviewController mController;

    public OverviewHeaderClickListener(OverviewController pController){
        mController = pController;
    }

    // Calls different controller methods depending on the view clicked.
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            // HeaderCreate
            case R.id.id_overview_header_create_button_todo:
                mController.newTodo();
                break;
            case R.id.id_overview_header_create_button_event:
                mController.newEvent();
                break;
            case R.id.id_overview_header_create_textView_note:
                mController.newNote();
                break;
            // HeaderDelete
            case R.id.id_overview_header_delete_button_back:
                mController.back();
                break;
            case R.id.id_overview_header_delete_button_delete:
                mController.delete();
                break;
        }
    }
}
