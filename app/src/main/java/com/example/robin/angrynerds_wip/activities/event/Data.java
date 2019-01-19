package com.example.robin.angrynerds_wip.activities.event;

import java.util.ArrayList;
import java.util.Date;

import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.services.Delete;
import com.example.robin.angrynerds_wip.data.services.Read;
import com.example.robin.angrynerds_wip.data.services.Update;

public class Data {
    private Init mActivity;
    private Event mEvent;

    Data(Init pActivity, String eventId) {
        mActivity = pActivity;
        mEvent = Read.getEventByID(eventId);
    }

    public void deleteEvent() {
        Delete.deleteTEN(mEvent.getID());
    }

    public void updateEvent() {
        Update.saveTEN(mEvent);
    }

    public void setDate(Date date) {
        mEvent.setTime(date);
        updateEvent();
    }

    public void setTitle(String title) {
        mEvent.setTitle(title);
        updateEvent();
    }

    public void setLocation(String location) {
        mEvent.setAddress(location);
        updateEvent();
    }

    public void addReminder(Date reminder) {
        ArrayList<Date> reminderList = mEvent.getReminder();
        reminderList.add(reminder);
        mEvent.setReminder(reminderList);
        updateEvent();
    }

    public void addReminderList(ArrayList<Date> newReminder) {
        mEvent.setReminder(newReminder);
        updateEvent();
    }

    public void removeReminder(int i) {
        ArrayList<Date> reminderList = mEvent.getReminder();
        reminderList.remove(i - 1);

        mEvent.setReminder(reminderList);
        updateEvent();
    }

    //Getter
    public Event getmEvent() {
        return mEvent;
    }

}
