package com.example.robin.angrynerds_wip.overview.overviewActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.robin.angrynerds_wip.R;

public class OverviewInit extends AppCompatActivity {

    private OverviewData mData;
    private OverviewGui mGui;
    private OverviewController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initGui();
        initController();
    }

    // Initializes the Controller
    private void initData() {
        mData = new OverviewData(this);
    }

    // Initializes the Graphical User Interface
    private void initGui() {
        mGui = new OverviewGui(this);
    }

    // Initializes the Controller
    private void initController() {
        mController = new OverviewController(mData, mGui);
    }

}
