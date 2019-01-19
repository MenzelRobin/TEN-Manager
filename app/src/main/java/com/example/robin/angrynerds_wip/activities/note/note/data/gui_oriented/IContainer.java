package com.example.robin.angrynerds_wip.activities.note.note.data.gui_oriented;

import android.app.Activity;
import android.widget.LinearLayout;


public class IContainer {

    protected Activity mActivity;
    protected LinearLayout mImageContainer;

    IContainer(Activity pActivity, int pId){
        this.mActivity = pActivity;
        this.mImageContainer = new LinearLayout(pActivity);
        setImageContainerId(pId);
    }

    public void setImageContainerId(int pId){
        mImageContainer.setId(pId);
    }

    public LinearLayout getImageContainer(){
        return mImageContainer;
    }
}
