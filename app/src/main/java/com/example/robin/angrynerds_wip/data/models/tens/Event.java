package com.example.robin.angrynerds_wip.data.models.tens;

import com.example.robin.angrynerds_wip.data.models.utils.Address;
import com.example.robin.angrynerds_wip.data.models.utils.RecurringType;

import java.util.Date;

public class Event extends TEN {
    private Date time;
    private Date [] reminder;
    private Address address;
    private RecurringType recurringType;

    //Constructor
    public Event(String title, int[] bgColors, Date time, Date[] reminder) {
        super(title, bgColors);
        this.time = time;
        this.reminder = reminder;
        this.address = null;
        this.recurringType = RecurringType.NONE;
    }

    public Event(String title, int[] bgColors, Date time, Date[] reminder, Address address) {
        super(title, bgColors);
        this.time = time;
        this.reminder = reminder;
        this.address = address;
        this.recurringType = RecurringType.NONE;
    }

    public Event(String title, int[] bgColors, Date time, Date[] reminder, Address address, RecurringType recurringType) {
        super(title, bgColors);
        this.time = time;
        this.reminder = reminder;
        this.address = address;
        this.recurringType = recurringType;
    }

    public Event(String title, int[] bgColors, Date time, Date[] reminder, RecurringType recurringType) {
        super(title, bgColors);
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

    public Event(String title, int color, Date time, Date[] reminder, Adress adress, RecurringType recurringType) {
        super(title, color);
        this.time = time;
        this.reminder = reminder;
        this.adress = adress;
        this.recurringType = recurringType;
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