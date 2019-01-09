package com.example.robin.angrynerds_wip.data.models.tens;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Note extends TEN {

    private String description;
    private ArrayList<String> tags;
    private ArrayList<Bitmap> pictures;

    //Constructors
    public Note() {
        super();
        this.tags = new ArrayList<String>();
        this.pictures = new ArrayList<Bitmap>();
    }

    public Note(String title) {
        super(title);
        this.tags = new ArrayList<String>();
        this.pictures = new ArrayList<Bitmap>();
    }

    //simple for usage
    public Note(String title, String description) {
        super(title);
        this.description = description;        tags = new ArrayList<String>();
        this.pictures = new ArrayList<Bitmap>();

    }

    //simple for usage
    public Note(String title, String description, ArrayList<String> tags) {
        super(title);
        this.description = description;
        this.tags = tags;
        this.pictures = new ArrayList<Bitmap>();
    }

    //all Attributes for complete Reconstruction
    public Note(String title, String ID, int color, int accentColor, Date dateOfCreation, String description, ArrayList<String> tags, ArrayList<Bitmap> pictures) {
        super(title, ID, color, accentColor, dateOfCreation);
        this.description = description;
        this.tags = tags;
        this.pictures = pictures;
    }


    //Getter and Setter
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public void addTag(String tag){
        this.tags.add(tag);
    }

    public ArrayList<Bitmap> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<Bitmap> pictures) {
        this.pictures = pictures;
    }

    public Bundle getBundle() {
        Bundle bundle = super.getBundle();
        bundle.putString("Description", description);
        bundle.putStringArrayList("Tags", tags);
        ArrayList<String> imageIDs = new ArrayList<String>();
        bundle.putStringArrayList("Pictures", imageIDs);
        return bundle;
    }
}
