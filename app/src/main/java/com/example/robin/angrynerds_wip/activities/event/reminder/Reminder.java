package com.example.robin.angrynerds_wip.activities.event.reminder;

import android.app.Activity;

import com.example.robin.angrynerds_wip.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Reminder {
    private ArrayList<String> mReminderLable;
    private ArrayList<String> mReminderLableStatic;
    private ArrayList<Date> mReminder;

    public Reminder(Activity activity) {
        mReminderLable = new ArrayList<String>();

        mReminderLable.add(0, activity.getString(R.string.event_reminder1));
        mReminderLable.add(1, activity.getString(R.string.event_reminder2));
        mReminderLable.add(2, activity.getString(R.string.event_reminder3));
        mReminderLable.add(3, activity.getString(R.string.event_reminder4));
        mReminderLableStatic = new ArrayList<>();
        mReminderLableStatic.add(0, activity.getString(R.string.event_reminder1));
        mReminderLableStatic.add(1, activity.getString(R.string.event_reminder2));
        mReminderLableStatic.add(2, activity.getString(R.string.event_reminder3));
        mReminderLableStatic.add(3, activity.getString(R.string.event_reminder4));
    }

    public void setReminder(ArrayList<Date> pReminder) {
        mReminder = pReminder;
    }

    public ArrayList<Date> getReminder() {
        return mReminder;
    }

    public String getLabelFromReminder(int rem, Date eventDate) {
        String label = "";
        Date reminder = mReminder.get(rem);
        long difference = eventDate.getTime() - reminder.getTime();

        if (difference == (3600000)) {
            label = mReminderLableStatic.get(0); //1 hour
        } else if (difference == (21600000)) {
            label = mReminderLableStatic.get(1); //6 hour
        } else if (difference == (86400000)) {
            label = mReminderLableStatic.get(2); //24 hour
        } else if (difference == (604800000)) {
            label = mReminderLableStatic.get(3); //1 Day
        } else {
            label = "Error: finding Label for " + reminder;
        }
        return label;
    }

    //Get appropriate Reminder by Lable
    public Date calcReminderFromLable(String lable, Date eventDate) {
        Calendar reminderDate = Calendar.getInstance();
        reminderDate.setTime(eventDate);

        if (lable.equals(mReminderLableStatic.get(0))) {
            reminderDate.set(Calendar.HOUR, reminderDate.get(Calendar.HOUR) - 1);
        } else if (lable.equals(mReminderLableStatic.get(1))) {
            reminderDate.set(Calendar.HOUR, reminderDate.get(Calendar.HOUR) - 6);
        } else if (lable.equals(mReminderLableStatic.get(2))) {
            reminderDate.set(Calendar.DAY_OF_MONTH, reminderDate.get(Calendar.DAY_OF_MONTH) - 1);
        } else if (lable.equals(mReminderLableStatic.get(3))) {
            reminderDate.set(Calendar.WEEK_OF_YEAR, reminderDate.get(Calendar.WEEK_OF_YEAR) - 1);
        }
        return reminderDate.getTime();
    }

    public String[] getReminderLabelString() {
        return mReminderLable.toArray(new String[mReminderLable.size()]);
    }

    public void removeReminderLable(String lable) {
        mReminderLable.remove(lable);
    }
}
