package com.example.robin.angrynerds_wip.activities.event.data;

import android.content.Intent;
import android.provider.CalendarContract;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.event.EventActivity;
import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.utils.RecurringType;
import com.example.robin.angrynerds_wip.data.services.Create;
import com.example.robin.angrynerds_wip.data.services.Delete;
import com.example.robin.angrynerds_wip.data.services.Read;
import com.example.robin.angrynerds_wip.data.services.Update;
import com.example.robin.angrynerds_wip.modules.share.ShareModule;

public class EventData {
    /* Robin Menzel
    Contains the data retention for Events.
    */
    private EventActivity mActivity;
    private Event mEvent;

    public EventData(EventActivity pActivity, String eventId) {
        mActivity = pActivity;

        //Create new event if eventId is empty
        if (eventId == null) {
            mEvent = Create.newEvent();
        } else {
            mEvent = Read.getEventByID(eventId);
        }
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

    public void setDate(int year, int month, int date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(mEvent.getTime());
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, date);
        mEvent.setTime(cal.getTime());
    }

    public void setTime(int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(mEvent.getTime());
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        mEvent.setTime(cal.getTime());
    }

    public void setTitle(String title) {
        mEvent.setTitle(title);
        updateEvent();
    }

    public void setLocation(String location) {
        mEvent.setAddress(location);
        updateEvent();
    }

    public void setRecurringType(int i) {
        switch (i) {
            case 0:
                mEvent.setRecurringType(RecurringType.NONE);
                break;
            case 1:
                mEvent.setRecurringType(RecurringType.DAILY);
                break;
            case 2:
                mEvent.setRecurringType(RecurringType.WEEKLY);
                break;
            case 3:
                mEvent.setRecurringType(RecurringType.YEARLY);
        }
    }

    public void addReminderList(ArrayList<Date> newReminder) {
        mEvent.setReminder(newReminder);
        updateEvent();
    }

    public void addReminder(Date reminder) {
        ArrayList<Date> reminderList = mEvent.getReminder();
        reminderList.add(reminder);
        mEvent.setReminder(reminderList);
        updateEvent();
    }

    public void removeReminder(int i) {
        ArrayList<Date> reminderList = mEvent.getReminder();
        reminderList.remove(i - 1);

        mEvent.setReminder(reminderList);
        updateEvent();
    }

    public void shareEvent() {
        ShareModule.shareEvent(mActivity, mEvent, getFormatedDate(), getFormatedTime());
    }

    //Getter
    public Event getEvent() {
        return mEvent;
    }

    //Formate Date to human readable Date
    public String getFormatedDate() {
        DateFormat displayFormat = new SimpleDateFormat("EEEE', ' dd. MMMM yyyy", Locale.GERMAN);
        return displayFormat.format(mEvent.getTime());
    }

    //Formate Date to human readable Time
    public String getFormatedTime() {
        DateFormat displayFormat = new SimpleDateFormat("HH:mm", Locale.GERMAN);
        return displayFormat.format(mEvent.getTime());
    }

    public void exportToCalendar() {
        Intent calendarIntent = new Intent(Intent.ACTION_EDIT);
        calendarIntent.setType("vnd.android.cursor.item/event");
        calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, mEvent.getTime().getTime());
        calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, mEvent.getTime().getTime() + 60 * 60 * 1000);
        calendarIntent.putExtra(CalendarContract.Events.TITLE, mEvent.getTitle());
        calendarIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, mEvent.getAddress());
        calendarIntent.putExtra(CalendarContract.Events.DESCRIPTION, mActivity.getString(R.string.event_calendar_description));
        mActivity.startActivity(calendarIntent);
    }
}
