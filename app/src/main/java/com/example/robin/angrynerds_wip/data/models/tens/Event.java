package com.example.robin.angrynerds_wip.data.models.tens;

import android.os.Bundle;

import com.example.robin.angrynerds_wip.data.models.utils.Address;
import com.example.robin.angrynerds_wip.data.models.utils.RecurringType;

import java.util.Date;

public class Event extends TEN {
    private Date time;
    private Date [] reminder;
    private Address address;
    private RecurringType recurringType;

    //Constructor
    public Event(String title, Date time, Date[] reminder) {
        super(title);
        this.time = time;
        this.reminder = reminder;
        this.address = null;
        this.recurringType = RecurringType.NONE;
    }

    public Event(String title, Date time, Date[] reminder, Address address) {
        super(title);
        this.time = time;
        this.reminder = reminder;
        this.address = address;
        this.recurringType = RecurringType.NONE;
    }

    public Event(String title, Date time, Date[] reminder, Address address, RecurringType recurringType) {
        super(title);
        this.time = time;
        this.reminder = reminder;
        this.address = address;
        this.recurringType = recurringType;
    }

    public Event(String title, Date time, Date[] reminder, RecurringType recurringType) {
        super(title);
        this.time = time;
        this.reminder = reminder;
        this.address = null;
        this.recurringType = recurringType;
    }

    public Event(String title, int color, Date time, Date[] reminder, Address address, RecurringType recurringType) {
        super(title, color);
        this.time = time;
        this.reminder = reminder;
        this.address = address;
        this.recurringType = recurringType;
    }

    public Bundle getBundle(){
        Bundle bundle = super.getBundle();
        bundle.putString("time", time.toString());
        bundle.putAll(address.getBundle());
        return bundle;
    }

    //Getter and Setter
    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }
    public Date[] getReminder() {
        return reminder;
    }
    public void setReminder(Date[] reminder) {
        this.reminder = reminder;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public RecurringType getRecurringType() {
        return recurringType;
    }
    public void setRecurringType(RecurringType recurringType) {
        this.recurringType = recurringType;
    }
}