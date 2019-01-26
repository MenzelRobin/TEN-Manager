package com.example.robin.angrynerds_wip.data.models.tens;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorLong;

import com.example.robin.angrynerds_wip.data.models.utils.BundleKeys;
import com.example.robin.angrynerds_wip.data.models.utils.Colors;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class TEN {
    private String title;
    @JsonIgnore
    private String ID;
    @ColorInt
    @JsonIgnore
    private int color;
    @ColorInt
    @JsonIgnore
    private int accentColor;
    @JsonIgnore
    private Date dateOfCreation;

    //Constructor

    //empty default
    public TEN() {
        int colorIndex = Colors.getRandomColorIndex();
        this.color = Colors.COLORS[colorIndex];
        this.accentColor = Colors.DARKER_ACCENT_COLORS[colorIndex];
        this.ID = null;
        this.dateOfCreation = new Date();
    }

    //simple for usage
    public TEN(String title) {
        int colorIndex = Colors.getRandomColorIndex();
        this.color = Colors.COLORS[colorIndex];
        this.accentColor = Colors.DARKER_ACCENT_COLORS[colorIndex];
        this.ID = null;
        this.title = title;
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

    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(BundleKeys.keyTENID, ID);
        bundle.putString(BundleKeys.keyTENTitle, title);
        bundle.putInt(BundleKeys.keyTENColor, color);
        bundle.putInt(BundleKeys.keyTENAccentColor, accentColor);
        bundle.putLong(BundleKeys.keyTENDateOfCreation, this.dateOfCreation.getTime());
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

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getColor() {
        return color;
    }

    public int getAccentColor() {
        return accentColor;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setAccentColor(int accentColor) {
        this.accentColor = accentColor;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

}
