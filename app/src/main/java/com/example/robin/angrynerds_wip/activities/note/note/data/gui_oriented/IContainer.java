package com.example.robin.angrynerds_wip.activities.note.note.data.gui_oriented;

import android.app.Activity;
import android.widget.LinearLayout;


public class IContainer {

    protected Activity mActivity;
    protected LinearLayout mImageContainer;

    IContainer(Activity activity, int id){
        this.mActivity = activity;
        this.mImageContainer = new LinearLayout(activity);
        setImageContainerId(id);
    }

    public void setImageContainerId(int id){
        mImageContainer.setId(id);
    }

    public LinearLayout getImageContainer(){
        return mImageContainer;
    }
}
