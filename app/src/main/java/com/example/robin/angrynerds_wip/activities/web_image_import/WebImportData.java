package com.example.robin.angrynerds_wip.activities.web_image_import;

import android.app.Activity;
import android.content.Context;

public class WebImportData {
    WebImportActivity mAcitivity;
    String mSearchterm;
    int color;

    public WebImportData(WebImportActivity pWebImportActivity, String mSearchterm, int color){
        this.mAcitivity = pWebImportActivity;
        this.mSearchterm = mSearchterm;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public String getSearchterm() {
        return this.mSearchterm;
    }

    public WebImportActivity getActivity() {
        return  this.mAcitivity;
    }
}
