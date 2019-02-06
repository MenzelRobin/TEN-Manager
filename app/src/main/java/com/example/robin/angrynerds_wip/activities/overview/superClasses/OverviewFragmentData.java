package com.example.robin.angrynerds_wip.activities.overview.superClasses;

import android.os.Bundle;

import com.example.robin.angrynerds_wip.data.models.utils.BundleKeys;

public abstract class OverviewFragmentData {
    /* Yannick-Luca Ruettgers
    Superclass for [Fragment]Data Classes. Mirrors the TEN Class.
    */
    String mID;
    String mTitle;
    int mColor;

    public void addData(Bundle pData){
        mID = pData.getString(BundleKeys.KEY_TEN_ID);
        mTitle = pData.getString(BundleKeys.KEY_TEN_TITLE);
        mColor = pData.getInt(BundleKeys.KEY_TEN_COLOR);
    }

    public String getID(){
        return mID;
    }

    public String getTitle(){
        return mTitle;
    }

    public int getColor(){
        return mColor;
    }
}
