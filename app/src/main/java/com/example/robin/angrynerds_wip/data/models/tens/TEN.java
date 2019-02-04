package com.example.robin.angrynerds_wip.data.models.tens;

import android.os.Bundle;
import android.support.annotation.ColorInt;

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
        this.ID = null;
        this.title = "";
        int colorIndex = Colors.getRandomColorIndex();
        this.color = Colors.COLORS[colorIndex];
        this.accentColor = Colors.DARKER_ACCENT_COLORS[colorIndex];
        this.dateOfCreation = new Date();
    }

    //simple for usage
    public TEN(String title) {
        this.ID = null;
        this.title = title;
        int colorIndex = Colors.getRandomColorIndex();
        this.color = Colors.COLORS[colorIndex];
        this.accentColor = Colors.DARKER_ACCENT_COLORS[colorIndex];
        this.dateOfCreation = new Date();
    }

    //complete Object must be reconstructed
    public TEN(String title, String ID, int color, int accentColor, Date dateOfCreation) {
        this.ID = ID;
        this.title = title;
        this.color = color;
        this.accentColor = accentColor;
        this.dateOfCreation = dateOfCreation;
    }

    public boolean isFound(String pSearchString){
        return title!=null?title.toLowerCase().contains(pSearchString.toLowerCase()):false;
    }

    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(BundleKeys.KEY_TEN_ID, ID);
        bundle.putString(BundleKeys.KEY_TEN_TITLE, title);
        bundle.putInt(BundleKeys.KEY_TEN_COLOR, color);
        bundle.putInt(BundleKeys.KEY_TEN_ACCENT_COLOR, accentColor);
        bundle.putLong(BundleKeys.KEY_TEN_DATE_OF_CREATION, this.dateOfCreation.getTime());
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
