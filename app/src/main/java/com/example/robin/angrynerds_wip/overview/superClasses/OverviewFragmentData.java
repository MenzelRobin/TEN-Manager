package com.example.robin.angrynerds_wip.overview.superClasses;

import android.os.Bundle;

public abstract class OverviewFragmentData {
    String mID;
    String mTitle;
    int mColor;

    public void addData(Bundle pData){
        mID = pData.getString("ID");
        mTitle = pData.getString("Title");
        mColor = pData.getInt("Color");
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
