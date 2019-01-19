package com.example.robin.angrynerds_wip.activities.note.note.data.gui_oriented;

import android.widget.LinearLayout;

import com.example.robin.angrynerds_wip.activities.note.note.NoteActivity;

public class IContainer {

    protected NoteActivity mActivity;
    protected LinearLayout mImageContainer;



    IContainer(NoteActivity activity, int id){
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
