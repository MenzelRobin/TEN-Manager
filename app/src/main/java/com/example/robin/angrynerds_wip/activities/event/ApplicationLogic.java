package com.example.robin.angrynerds_wip.activities.event;

import android.content.Intent;

//import de.fhdw.bfwi316b.set.colorchooser.activities.ActivityUtilities;
//import de.fhdw.bfwi316b.set.colorchooser.activities.Data;

public class ApplicationLogic {

    //private Data mData;
    private Gui mGui;
    /*
    public ApplicationLogic(Data data, Gui gui) {
        mData = data;
        mGui = gui;
        initGui();
        initListener();
    }*/

    private void initGui() {
        dataToGui();
    }

    private void initListener() {
        ClickListener clickListener;

        clickListener = new ClickListener(this);
        mGui.getOkButton().setOnClickListener(clickListener);
    }

    public void dataToGui() {
    }

    public void onActivityReturned(int requestCode, int resultCode, Intent data) {
    }

    public void onOkButtonClicked() {}

    public void onBackPressed() {    }

}
