package com.example.robin.angrynerds_wip.activities.note.note;

import android.widget.LinearLayout;

class IContainer {

    protected NoteActivity mActivity;
    protected LinearLayout mImageContainer;

    protected final int WIDTH = 800;
    protected final int HEIGHT = 800;

    IContainer(NoteActivity activity, int id){
        this.mActivity = activity;
        this.mImageContainer = new LinearLayout(activity);
        setImageContainerId(id);
    }

    void setImageContainerId(int id){
        mImageContainer.setId(id);
    }
    LinearLayout getImageContainer(){
        return mImageContainer;
    }
}
