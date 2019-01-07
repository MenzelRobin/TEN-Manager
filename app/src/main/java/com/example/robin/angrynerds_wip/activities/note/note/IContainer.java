package com.example.robin.angrynerds_wip.activities.note.note;

import android.graphics.Bitmap;
import android.widget.LinearLayout;

interface IContainer {

    void setImageContainerId(int id);
    LinearLayout getImageContainer();
    Bitmap getImage();
}
