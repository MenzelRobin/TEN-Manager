package com.example.robin.angrynerds_wip.activities.note;

import android.graphics.Bitmap;
import android.widget.LinearLayout;

interface IContainer {

    void setImageContainerId(int i);
    LinearLayout getImageContainer();
    Bitmap getImage();
}
