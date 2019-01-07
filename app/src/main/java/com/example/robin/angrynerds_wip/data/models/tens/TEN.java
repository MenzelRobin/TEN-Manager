package com.example.robin.angrynerds_wip.data.models.tens;

import android.os.Bundle;

import com.example.robin.angrynerds_wip.data.models.utils.Colors;

import java.util.Date;
import java.util.Random;

public class TEN {
    private String title;
    private String ID;
    private int color;
    private Date dateOfCreation;

    private static int nextID = 0; //For MockData only

    //Constructor
    public TEN(){
        this.color = Colors.getRandomColor();
        this.dateOfCreation = new Date();
    }

    public TEN(String title, String ID, int color) {
        this.title = title;
        this.ID = ID;
        this.color = color;
        this.dateOfCreation = new Date();
    }

    //TODO Implement ID in Database -> Delete ID from this class
    public TEN(String title, int color) {
        this.title = title;
        this.color = color;
        this.ID = String.valueOf(nextID++); //for MockData only
        this.dateOfCreation = new Date();
    }

    //Constructor for random color. Color array from colors.xml has to be handed over from activity.
    //use this: int[] bgColors = getResources().getIntArray(R.array.bgColors);
    //TODO Color Array hand over from corresponding Activity
    public TEN(String title) {
        this.color = Colors.getRandomColor();
        this.title=title;
        this.dateOfCreation = new Date();
    }

    public Bundle getBundle(){
        Bundle bundle = new Bundle();
        bundle.putString("ID", ID);
        bundle.putString("Title", title);
        bundle.putInt("Color", color);
        return bundle;
    }

    //Getter and Setter
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getID() {
        return ID;
    }
    public void setID(String ID){ this.ID = ID; }
    public int getColor() {
        return color;
    }
    public void setColor(int color) {
        this.color = color;
    }
    public Date getDateOfCreation() {return dateOfCreation;}

    public Bundle getBundle(){
        Bundle bundle = new Bundle();
        bundle.putString("ID", ID);
        bundle.putString("Title", title);
        bundle.putInt("Color", color);
        return  bundle;
    }
}
