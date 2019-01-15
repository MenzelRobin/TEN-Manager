package com.example.robin.angrynerds_wip.data.models.utils;

import android.graphics.Bitmap;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Image {
    private String id;
    @JsonIgnore
    private Bitmap bitmap;

    //Constructor
    public Image() {
        this.id = "";
        this.bitmap = null;
    }

    public Image(String id) {
        this.id = id;
        this.bitmap = null;
    }

    public Image(String id, Bitmap bitmap) {
        this.id = id;
        this.bitmap = bitmap;
    }

    public Image (Image image){
        this.id = "" + image.getId();
        this.bitmap = null;
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
