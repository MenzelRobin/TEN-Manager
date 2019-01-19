package com.example.robin.angrynerds_wip.activities.event;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Reminder {
    private ArrayList<String> mReminderLable;
    private ArrayList<String> mReminderLableStatic;
    private ArrayList<Date> mReminder;

    public Reminder(){
        mReminderLable = new ArrayList<String>();
        mReminderLable.add(0, "1 Stunde vorher");
        mReminderLable.add(1, "6 Stunden vorher");
        mReminderLable.add(2, "1 Tag vorher");
        mReminderLable.add(3, "1 Woche vorher");
        mReminderLableStatic = new ArrayList<>();
        mReminderLableStatic.add(0, "1 Stunde vorher");
        mReminderLableStatic.add(1, "6 Stunden vorher");
        mReminderLableStatic.add(2, "1 Tag vorher");
        mReminderLableStatic.add(3, "1 Woche vorher");
    }

    public void setReminder(ArrayList<Date> pReminder){
        mReminder = pReminder;
    }

    public ArrayList<Date> getReminder(){
        return mReminder;
    }
    public int getReminderSize(){
        return mReminder.size();
    }

    public Date calcReminderFromLable(int i){
        return mReminder.get(i);
    }

    public String getLabelFromReminder(int rem, Date eventDate){
        String label = "";
        Date reminder = mReminder.get(rem);

        long difference = eventDate.getTime() - reminder.getTime();
        Log.d("LOGTAG", "Differenz " + String.valueOf(difference));

        if(difference == (3600000)){
            //1 hour
            label = mReminderLableStatic.get(0);
        }else if(difference == (21600000)){
            //6 hour
            label = mReminderLableStatic.get(1);
        }else if(difference == (86400000)){
            //24 hour
            label = mReminderLableStatic.get(2);
        }else if(difference == (604800000)){
            //1 Day
            label = mReminderLableStatic.get(3);
        }else{
            label = "Error: finding Label for " + reminder;
        }

        return label;
    }

    //Get appropriate Reminder by Lable
    public Date calcReminderFromLable(String lable, Date eventDate){
        Calendar reminderDate = Calendar.getInstance();
        reminderDate.setTime(eventDate);

        if(lable.equals(mReminderLableStatic.get(0))){
            reminderDate.set(Calendar.HOUR, reminderDate.get(Calendar.HOUR) - 1);
        }else if(lable.equals(mReminderLableStatic.get(1))){
            reminderDate.set(Calendar.HOUR, reminderDate.get(Calendar.HOUR) - 6);
        }else if(lable.equals(mReminderLableStatic.get(2))){
            reminderDate.set(Calendar.DAY_OF_MONTH, reminderDate.get(Calendar.DAY_OF_MONTH) - 1);
        }else if(lable.equals(mReminderLableStatic.get(3))){
            reminderDate.set(Calendar.WEEK_OF_YEAR, reminderDate.get(Calendar.WEEK_OF_YEAR) - 1);
        }

        Log.d("LOGTAG", "Reminder updated to " + lable + " " + reminderDate.getTime());
        return reminderDate.getTime();

    }

    public String[] getReminderLabelString(){
        return mReminderLable.toArray(new String[mReminderLable.size()]);
    }

    public void removeReminderLable(String lable){
        mReminderLable.remove(lable);
    }

    public ArrayList<String> getReminderLabel(){
        return mReminderLable;
    }

    public ArrayList<Date> calcReminderFromLable(){
        return mReminder;
    }
}
