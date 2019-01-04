package com.example.robin.angrynerds_wip.overview.todoFragment;

import android.os.Bundle;

import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentData;

public class OverviewTodoData extends OverviewFragmentData {

    String mNote;

    // Adds the Note data to this Object
    public void addData(Bundle pData){
        super.addData(pData);
        mNote = pData.getString("Note");
    }

    // Returns the Description
    public String getNote(){
        return mNote;
    }
}
