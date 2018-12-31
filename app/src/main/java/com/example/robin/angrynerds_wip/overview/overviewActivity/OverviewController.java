package com.example.robin.angrynerds_wip.overview.overviewActivity;

import com.example.robin.angrynerds_wip.data.models.tens.Note;

public class OverviewController {

    private OverviewData mData;
    private OverviewGui mGui;

    OverviewController(OverviewData pData, OverviewGui pGui) {
        mData = pData;
        mGui = pGui;
        refreshData();
        mGui.setmTextView(mData.getNotes().get(0).getTitle());
    }

    public void refreshData(){
        mData.pullTENs();
    }
}
