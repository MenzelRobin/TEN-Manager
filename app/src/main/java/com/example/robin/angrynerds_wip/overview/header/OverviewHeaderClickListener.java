package com.example.robin.angrynerds_wip.overview.header;

import android.view.View;
import android.widget.TextView;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;
import com.example.robin.angrynerds_wip.overview.header.searchFragment.OverviewHeaderSearchFragment;
import com.example.robin.angrynerds_wip.overview.overviewActivity.OverviewController;

public class OverviewHeaderClickListener implements View.OnClickListener {

    OverviewController mController;
    View mView;

    public OverviewHeaderClickListener(OverviewController pController){
        mController = pController;
    }

    public OverviewHeaderClickListener(OverviewController pController, View pView){
        mController = pController;
        mView = pView;
    }

    // Calls different controller methods depending on the view clicked.
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            // HeaderCreate
            case R.id.id_overview_header_create_button_todo:
                mController.newTEN(Todo.class);
                break;
            case R.id.id_overview_header_create_button_event:
                mController.newTEN(Event.class);
                break;
            case R.id.id_overview_header_create_textView_note:
                mController.newTEN(Note.class);
                break;
            case R.id.id_overview_header_create_button_search:
                mController.activateSearch();
                break;
            // HeaderDelete
            case R.id.id_overview_header_delete_button_back:
                mController.back();
                break;
            case R.id.id_overview_header_delete_button_delete:
                mController.delete();
                break;
            // HeaderSearch
            case R.id.id_overview_header_search_button_back:
                mController.back();
                break;
            case R.id.id_overview_header_search_button_search:
                mController.search(((TextView)(mView.findViewById(R.id.id_overview_header_search_view_searchText))).getText().toString());
                break;
        }
    }
}
