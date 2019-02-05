package com.example.robin.angrynerds_wip.activities.overview.overviewActivity;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class OverviewInit extends AppCompatActivity {
    /* Yannick-Luca Rüttgers
    Initiates all necessary Activitycomponents
     */

    private OverviewData mData;
    private OverviewGui mGui;
    private OverviewController mController;

    // Called on Create / Recreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initGui();
        initController();
    }

    // Called when the Orientation of the App is changed
    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        initGui();
        initController();
    }

    // Called when the Activity comes back into the foreground
    @Override
    public void onResume(){
        super.onResume();
        if(mController != null) {
            mController.onResume();
        } else {
            initData();
            initGui();
            initController();
        }
    }

    // Initializes the Controller
    private void initData() {
        mData = new OverviewData();
    }

    // Initializes the Graphical User Interface
    private void initGui() {
        mGui = new OverviewGui(this);
    }

    // Initializes the Controller
    private void initController() {
        mController = new OverviewController(this, mData, mGui);
    }

    // Used to trigger controllermethods from fragments
    public OverviewController getController(){
        return mController;
    }
}
