package com.example.robin.angrynerds_wip.data.models.utils;

import android.graphics.Bitmap;

public class Image {
    private String id;
    private Bitmap bitmap;

    //Constructor
    public Image(){
        this.id = "";
        this.bitmap = null;
    }

    public Image(String id, Bitmap bitmap) {
        this.id = id;
        this.bitmap = bitmap;
    }

    //Getter and Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
