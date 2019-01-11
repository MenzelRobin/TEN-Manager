package com.example.robin.angrynerds_wip.data.models.tens;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorLong;

import com.example.robin.angrynerds_wip.data.models.utils.Colors;

import java.util.Date;

public class TEN {
    private String title;
    private String ID;
    @ColorInt
    private int color;
    @ColorInt
    private int accentColor;
    private Date dateOfCreation;

    //Constructor

    //empty default
    public TEN(){
        int colorIndex = Colors.getRandomColorIndex();
        this.color = Colors.COLORS[colorIndex];
        this.accentColor = Colors.DARKER_ACCENT_COLORS[colorIndex];

        this.dateOfCreation = new Date();
    }

    //simple for usage
    public TEN(String title) {
        int colorIndex = Colors.getRandomColorIndex();
        this.color = Colors.COLORS[colorIndex];
        this.accentColor = Colors.DARKER_ACCENT_COLORS[colorIndex];

        this.title=title;
        this.dateOfCreation = new Date();
    }

    //complete Object must be reconstructed
    public TEN(String title, String ID, int color, int accentColor, Date dateOfCreation) {
        this.color = color;
        this.accentColor = accentColor;
        this.title = title;
        this.ID = ID;
        this.dateOfCreation = dateOfCreation;
    }

    public Bundle getBundle(){
        Bundle bundle = new Bundle();
        bundle.putString("ID", ID);
        bundle.putString("Title", title);
        bundle.putLong("Color", color);
        bundle.putLong("AccentColor", color);
        bundle.putLong("DateOfCreation", this.dateOfCreation.getTime());
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
