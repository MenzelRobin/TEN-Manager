package com.example.robin.angrynerds_wip.data.models.tens;

import java.util.Date;
import java.util.Random;

public class TEN {
    private String title;
    private String ID;
    private int color;
    private long dateOfCreation;

    private static int nextID = 0;

    //Constructor
    public TEN(String title, String ID, int color) {
        this.title = title;
        this.ID = ID;
        this.color = color;
        this.dateOfCreation = System.currentTimeMillis();
    }

    public TEN(String title, int color) {
        this.title = title;
        this.color = color;
        this.ID = String.valueOf(nextID++); //for MockData only
        this.dateOfCreation = System.currentTimeMillis();
    }

    public TEN(String title, int color) {
        this.title = title;
        this.color = color;
    }

    //Constructor for random color. Color array from colors.xml has to be handed over from activity.
    //use this: int[] bgColors = getResources().getIntArray(R.array.bgColors);
    //TODO Color Array hand over from corresponding Activity
    public TEN(String title, int[] bgColors) {
        this.color = bgColors[new Random().nextInt(bgColors.length)];
        this.title=title;
        this.dateOfCreation = System.currentTimeMillis();
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
    public int getColor() {
        return color;
    }
    public void setColor(int color) {
        this.color = color;
    }
}
