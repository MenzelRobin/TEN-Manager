package com.example.robin.angrynerds_wip.data.models.tens;

import android.os.Bundle;

import com.example.robin.angrynerds_wip.data.models.utils.Colors;

import java.util.Date;

public class TEN {
    private String title;
    private String ID;
    private int color;
    private int accentColor;
    private Date dateOfCreation;

    private static int nextID = 0; //For MockData only

    //Constructor
    public TEN(){
        int colorIndex = Colors.getRandomColorIndex();
        this.color = Colors.COLORS[colorIndex];
        this.accentColor = Colors.DARKER_ACCENT_COLORS[colorIndex];

        this.dateOfCreation = new Date();
    }

    public TEN(String title, String ID, int color) {
        int colorIndex = Colors.getRandomColorIndex();
        this.color = Colors.COLORS[colorIndex];
        this.accentColor = Colors.DARKER_ACCENT_COLORS[colorIndex];

        this.title = title;
        this.ID = ID;
        this.dateOfCreation = new Date();
    }

    //TODO Implement ID in Database -> Delete ID from this class
    public TEN(String title, int color) {
        int colorIndex = Colors.getRandomColorIndex();
        this.color = Colors.COLORS[colorIndex];
        this.accentColor = Colors.DARKER_ACCENT_COLORS[colorIndex];

        this.title = title;
        this.ID = String.valueOf(nextID++); //for MockData only
        this.dateOfCreation = new Date();
    }

    //Constructor for random color. Color array from colors.xml has to be handed over from activity.
    //use this: int[] bgColors = getResources().getIntArray(R.array.bgColors);
    //TODO Color Array hand over from corresponding Activity
    public TEN(String title) {
        int colorIndex = Colors.getRandomColorIndex();
        this.color = Colors.COLORS[colorIndex];
        this.accentColor = Colors.DARKER_ACCENT_COLORS[colorIndex];

        this.title=title;
        this.dateOfCreation = new Date();
    }

    public Bundle getBundle(){
        Bundle bundle = new Bundle();
        bundle.putString("ID", ID);
        bundle.putString("Title", title);
        bundle.putInt("Color", color);
        bundle.putInt("AccentColor", color);
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
    public int getAccentColor() { return accentColor; }
    public void setColor(int color) {
        this.color = color;
    }
    public Date getDateOfCreation() {return dateOfCreation;}

}
