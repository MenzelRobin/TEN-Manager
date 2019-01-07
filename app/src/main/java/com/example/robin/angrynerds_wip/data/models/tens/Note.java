package com.example.robin.angrynerds_wip.data.models.tens;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Date;

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

    //simple for usage
    public Note(String title, String description){
        super(title);
        this.description = description;
    }

    //simple for usage
    public Note(String title, String description, ArrayList<String> tags){
        super(title);
        this.description = description;
        this.tags = tags;
    }

    //all Attributes for complete Reconstruction
    public Note(String title, String ID, int color, int accentColor, Date dateOfCreation, String description, ArrayList<String> tags, ArrayList<String> pictures){
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
