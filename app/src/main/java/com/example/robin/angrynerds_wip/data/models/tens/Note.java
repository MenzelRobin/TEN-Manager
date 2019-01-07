package com.example.robin.angrynerds_wip.data.models.tens;

import android.os.Bundle;

import java.util.ArrayList;

import android.os.Bundle;

public class Note extends TEN{

    private String description;
    private ArrayList<String> tags;
    private ArrayList<String> pictures;

    //Constructors
    public Note(){
        super();
    }
    public Note(String title){
        super(title);
    }

    public Note(String title, String description){
        super(title);
        this.description = description;
    }

    public Note(String title, int color, String description){
        super(title, color);
        this.description = description;
    }

    public Note(String title, int color, String description, ArrayList<String> tags){
        super(title, color);
        this.description = description;
        this.tags = tags;
    }

    public Note(String title, int color, String description, ArrayList<String> tags, ArrayList<String> pictures){
        super(title, color);
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
    public ArrayList<String> getTags() {return tags;}
    public void setTags(ArrayList<String> tags) {this.tags = tags;}
    public ArrayList<String> getPictures() {return pictures;}
    public void setPictures(ArrayList<String> pictures) {this.pictures = pictures; }

    public Bundle getBundle(){
        Bundle bundle = super.getBundle();
        bundle.putString("Description", description);
        bundle.putStringArrayList("Tags", tags);
        bundle.putStringArrayList("Pictures", pictures);
        return bundle;
    }
}
