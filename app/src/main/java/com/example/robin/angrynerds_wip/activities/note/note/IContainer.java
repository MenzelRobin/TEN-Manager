package com.example.robin.angrynerds_wip.activities.note.note;

import android.graphics.Bitmap;
import android.widget.LinearLayout;

abstract class IContainer {

    abstract void setImageContainerId(int id);
    abstract LinearLayout getImageContainer();
    abstract Bitmap getImage();
}
