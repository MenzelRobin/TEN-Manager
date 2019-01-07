package com.example.robin.angrynerds_wip.data.models.tens;

import android.os.Bundle;

import com.example.robin.angrynerds_wip.data.models.utils.RecurringType;

import java.util.ArrayList;
import java.util.Date;

public class Event extends TEN {
    private Date time;
    private ArrayList<Date> reminder;
    private String address;
    private RecurringType recurringType;

    //Constructor
    public Event(){
        super();
    }

    public Event(String title, Date time, ArrayList<Date> reminder) {
        super(title);
        this.time = time;
        this.reminder = reminder;
        this.address = null;
        this.recurringType = RecurringType.NONE;
    }

    public Event(String title, Date time, ArrayList<Date> reminder, String address) {
        super(title);
        this.time = time;
        this.reminder = reminder;
        this.address = address;
        this.recurringType = RecurringType.NONE;
    }

    public Event(String title, Date time, ArrayList<Date> reminder, String address, RecurringType recurringType) {
        super(title);
        this.time = time;
        this.reminder = reminder;
        this.address = address;
        this.recurringType = recurringType;
    }

    public Event(String title, Date time, ArrayList<Date> reminder, RecurringType recurringType) {
        super(title);
        this.time = time;
        this.reminder = reminder;
        this.address = null;
        this.recurringType = recurringType;
    }

    public Event(String title, int color, Date time, ArrayList<Date> reminder, String address, RecurringType recurringType) {
        super(title, color);
        this.time = time;
        this.reminder = reminder;
        this.address = address;
        this.recurringType = recurringType;
    }

    //Getter and Setter
    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }
    public ArrayList<Date> getReminder() {
        return reminder;
    }
    public void setReminder(ArrayList<Date> reminder) {
        this.reminder = reminder;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public RecurringType getRecurringType() {
        return recurringType;
    }
    public void setRecurringType(RecurringType recurringType) {
        this.recurringType = recurringType;
    }

    public Bundle getBundle(){
        Bundle bundle = super.getBundle();
        bundle.putString("time", time.toString());
        bundle.putString("adress", address);
        return bundle;
    }
}