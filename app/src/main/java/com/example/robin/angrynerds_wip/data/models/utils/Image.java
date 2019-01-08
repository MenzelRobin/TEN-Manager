package com.example.robin.angrynerds_wip.data.models.utils;

import android.graphics.Bitmap;

public class Image {

    private String imageID;
    private Bitmap image;

    //Constructors
    public Image(){
        imageID = null;
        image = null;
    }

    public Image(Bitmap image){
        this.image = image;
    }

    public Image(String ID, Bitmap image){
        this.imageID = ID;
        this.image = image;
    }

    //Getter and Setter
    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        if(this.imageID == null){
            this.imageID = imageID;
        }
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
