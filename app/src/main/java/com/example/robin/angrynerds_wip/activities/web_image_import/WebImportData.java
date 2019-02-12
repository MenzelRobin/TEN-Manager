package com.example.robin.angrynerds_wip.activities.web_image_import;

import android.app.Activity;

public class WebImportData {
    Activity mAcitivity;
    String mSearchterm;

    public WebImportData(WebImportActivity pWebImportActivity, String mSearchterm){
        this.mAcitivity = pWebImportActivity;
        this.mSearchterm = mSearchterm;
    }


    public String getSearchterm() {
        return this.mSearchterm;
    }
}
