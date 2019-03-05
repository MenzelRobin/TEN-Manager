package com.example.robin.angrynerds_wip.activities.web_image_import;

import android.app.Activity;
import android.content.Context;

public class WebImportData {
    WebImportActivity mAcitivity;
    String mSearchterm;

    public WebImportData(WebImportActivity pWebImportActivity, String mSearchterm){
        this.mAcitivity = pWebImportActivity;
        this.mSearchterm = mSearchterm;
    }


    public String getSearchterm() {
        return this.mSearchterm;
    }

    public WebImportActivity getActivity() {
        return  this.mAcitivity;
    }
}
