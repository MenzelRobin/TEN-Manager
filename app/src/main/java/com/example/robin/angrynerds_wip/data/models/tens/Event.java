package com.example.robin.angrynerds_wip.data.models.tens;

import android.os.Bundle;

import com.example.robin.angrynerds_wip.data.models.utils.BundleKeys;
import com.example.robin.angrynerds_wip.data.models.utils.RecurringType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event extends TEN {
    private Date time;
    private ArrayList<Date> reminder;
    private String address;
    private RecurringType recurringType;

    //Constructor

    //empty default
    public Event() {
        super();
        this.time = new Date();
        this.reminder = new ArrayList<>();
        this.address = "";
        this.recurringType = RecurringType.NONE;
    }

    //simple for usage
    public Event(String title, Date time, ArrayList<Date> reminder) {
        super(title);
        this.time = time;
        this.reminder = reminder;
        this.address = null;
        this.recurringType = RecurringType.NONE;
    }

    //simple for usage
    public Event(String title, Date time, ArrayList<Date> reminder, String address) {
        super(title);
        this.time = time;
        this.reminder = reminder;
        this.address = address;
        this.recurringType = RecurringType.NONE;
    }

    //simple for usage
    public Event(String title, Date time, ArrayList<Date> reminder, String address, RecurringType recurringType) {
        super(title);
        this.time = time;
        this.reminder = reminder;
        this.address = address;
        this.recurringType = recurringType;
    }

    //simple for usage
    public Event(String title, Date time, ArrayList<Date> reminder, RecurringType recurringType) {
        super(title);
        this.time = time;
        this.reminder = reminder;
        this.address = null;
        this.recurringType = recurringType;
    }

    //all Attributes for reconstruction of complete Object
    public Event(String title, String ID, int color, int accentColor, Date dateOfCreation, Date time, ArrayList<Date> reminder, String address, RecurringType recurringType) {
        super(title, ID, color, accentColor, dateOfCreation);
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

    public Bundle getBundle() {
        Bundle bundle = super.getBundle();
        bundle.putLong(BundleKeys.keyEventTime, time.getTime());
        bundle.putString(BundleKeys.keyEventAddress, address);
        return bundle;
    }
}