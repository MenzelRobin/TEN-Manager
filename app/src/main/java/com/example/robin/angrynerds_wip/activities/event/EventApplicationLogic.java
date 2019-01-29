package com.example.robin.angrynerds_wip.activities.event;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.robin.angrynerds_wip.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.util.Locale;

public class EventApplicationLogic {

    private Gui mGui;
    private AppCompatActivity mActivity;
    private Data mData;
    private Reminder mReminder;
    private NotificationManagerCompat mNotificationManager;

    private NotificationHelper mNotificationHelper;

    public EventApplicationLogic(Gui pGui, AppCompatActivity pActivity, Data pData) {
        mGui = pGui;
        mActivity = pActivity;
        mData = pData;
        mReminder = new Reminder();
        initGui();
        initListener();

        mNotificationManager = NotificationManagerCompat.from(mActivity);
    }

    private void initGui() {
        //initialize Toolbar including menu and back button
        mActivity.setSupportActionBar(mGui.getToolbar());
        mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        mActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);

        updateGui();
    }

    private void initListener() {
        //Gui + Toolbar Clicklistener
        ClickListener clickListener;
        clickListener = new ClickListener(this);
        MenuItemClickListener menuItemClickListener;
        menuItemClickListener = new MenuItemClickListener(this);
        mGui.getToolbar().setNavigationOnClickListener(clickListener);
        mGui.getToolbar().setOnMenuItemClickListener(menuItemClickListener);
        mGui.getEditTextDate().setOnClickListener(clickListener);
        mGui.getEditTextTime().setOnClickListener(clickListener);
        mGui.getEditTextNewReminder().setOnClickListener(clickListener);
        mGui.getIconCloseReminder1().setOnClickListener(clickListener);
        mGui.getIconCloseReminder2().setOnClickListener(clickListener);
        mGui.getIconCloseReminder3().setOnClickListener(clickListener);
        mGui.getIconCloseReminder4().setOnClickListener(clickListener);
        //Textwatcher
        mGui.getEditTextTitle().addTextChangedListener(new TextWatcher(this, mGui.getEditTextTitle()));
        mGui.getEditTextLocation().addTextChangedListener(new TextWatcher(this, mGui.getEditTextLocation()));
    }

    public void updateGui() {
        //Fill Gui Elements with Data
        mGui.setTitle(mData.getmEvent().getTitle());
        mGui.setTime(formatTime(mData.getmEvent().getTime()));
        mGui.setDate(formatDate(mData.getmEvent().getTime()));
        mGui.setLocation(mData.getmEvent().getAddress());
        mGui.setColor(mData.getmEvent().getColor(), mData.getmEvent().getAccentColor());
        mReminder = new Reminder();
        mReminder.setReminder(mData.getmEvent().getReminder());
        mGui.setReminder(mReminder, mData.getmEvent().getTime());
        setAlarm();
    }

    public void setAlarm(){
        //Set alarm if reminder is in the future
        Calendar actualTime = Calendar.getInstance();
        for (int i = 0; i < mData.getmEvent().getReminder().size(); i++) {
            if (mData.getmEvent().getReminder().get(i).after(actualTime.getTime())) {
                Log.d("LOGTAG", "Start Alarm");
                startAlarm(mData.getmEvent().getReminder().get(i), i);
            }
        }
    }

    //Formate Date to human readable Date
    public String formatDate(Date date) {
        DateFormat displayFormat = new SimpleDateFormat("EEEE', ' dd. MMMM yyyy", Locale.GERMAN);
        return displayFormat.format(date);
    }

    //Formate Date to human readable Time
    public String formatTime(Date date) {
        DateFormat displayFormat = new SimpleDateFormat("HH:mm", Locale.GERMAN);
        return displayFormat.format(date);
    }

    //Return to overview if back pressed / Event deleted / toolbar navigation
    public void returnToOverview() {
        mActivity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.slide_out_right);
        mActivity.finish();
    }

    //Receive Date from DatePicker and save it
    public void receiveDate(Date dt) {
        //Save Date
        Calendar newDate = Calendar.getInstance();
        Calendar recDate = Calendar.getInstance();
        recDate.setTime(dt);
        newDate.setTime(mData.getmEvent().getTime());
        newDate.set(Calendar.YEAR, recDate.get(Calendar.YEAR));
        newDate.set(Calendar.MONTH, recDate.get(Calendar.MONTH));
        newDate.set(Calendar.DAY_OF_MONTH, recDate.get(Calendar.DAY_OF_MONTH));
        mData.setDate(newDate.getTime());

        updateReminder();
        updateGui();
    }

    //Receive Time from TimePicker and save it
    public void receiveTime(Date date) {
        Calendar mDate = Calendar.getInstance();
        Calendar recDate = Calendar.getInstance();
        recDate.setTime(date);
        mDate.setTime(mData.getmEvent().getTime());
        mDate.set(Calendar.HOUR_OF_DAY, recDate.get(Calendar.HOUR_OF_DAY));
        mDate.set(Calendar.MINUTE, recDate.get(Calendar.MINUTE));
        mDate.set(Calendar.SECOND, 0);
        mData.setDate(mDate.getTime());

        updateReminder();
        updateGui();
    }

    //If Event Time is changed, reminder have to be updated
    public void updateReminder() {
        ArrayList<Date> eventReminder = new ArrayList<Date>();
        mReminder.setReminder(mData.getmEvent().getReminder());

        if (mGui.getEditTextReminder1().length() != 0) {
            eventReminder.add(mReminder.calcReminderFromLable(mGui.getEditTextReminder1().getText().toString(), mData.getmEvent().getTime()));
        }
        if (mGui.getEditTextReminder2().length() > 0) {
            eventReminder.add(mReminder.calcReminderFromLable(mGui.getEditTextReminder2().getText().toString(), mData.getmEvent().getTime()));
        }
        if (mGui.getEditTextReminder3().length() > 0) {
            eventReminder.add(mReminder.calcReminderFromLable(mGui.getEditTextReminder3().getText().toString(), mData.getmEvent().getTime()));
        }
        if (mGui.getEditTextReminder4().length() > 0) {
            eventReminder.add(mReminder.calcReminderFromLable(mGui.getEditTextReminder4().getText().toString(), mData.getmEvent().getTime()));
        }

        mData.addReminderList(eventReminder);
    }

    //Open DatePicker fragment dialog
    public void showDatePickerDialog(View v) {
        DialogFragment datePicker = new DatePickerFragment();
        datePicker.show(mActivity.getFragmentManager(), "DatePicker");
    }

    //Open TimePicker fragment dialog with
    public void showTimePickerDialog(View v) {
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(mActivity.getFragmentManager(), "TimePicker");
    }

    //User wants to add new reminder
    public void onNewReminderClicked() {
        if (mData.getmEvent().getReminder().size() != 4) {
            //Create a AlertDialog with suggested Reminder periods
            new AlertDialog.Builder(mActivity)
                    .setItems(mReminder.getReminderLabelString(), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mData.addReminder(mReminder.calcReminderFromLable(mReminder.getReminderLabelString()[i], mData.getmEvent().getTime()));
                            updateGui();
                        }
                    }).show();
        } else {
            new AlertDialog.Builder(mActivity)
                    .setMessage("Es ist nicht möglich, mehr als 4 Erinnerungen einzustellen.")
                    .setOnCancelListener( new DialogInterface.OnCancelListener() {
                                @Override
                                public void onCancel(DialogInterface dialog) {
                                    dialog.cancel();
                                }})
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    }).show();
        }
    }

    //Save Text from editText, when Text changed
    public void onTextChanged(String text, View view) {
        if (view.getId() == R.id.id_event_editText_title) {
            mData.setTitle(text);
        } else if (view.getId() == R.id.id_event_editText_location) {
            mData.setLocation(text);
        }
    }

    //Remove Reminder when close icon is clicked
    public void onCloseReminderClicked(int i) {
        switch (i) {
            case 1:
                if (mGui.getIconCloseReminder1().getAlpha() == (float) 0.5) {
                    mData.removeReminder(i);
                    cancelAlarm(0);
                }
                break;
            case 2:
                if (mGui.getIconCloseReminder2().getAlpha() == (float) 0.5) {
                    mData.removeReminder(i);
                    cancelAlarm(1);
                }
                break;
            case 3:
                if (mGui.getIconCloseReminder3().getAlpha() == (float) 0.5) {
                    mData.removeReminder(i);
                    cancelAlarm(2);
                }
                break;
            case 4:
                if (mGui.getIconCloseReminder4().getAlpha() == (float) 0.5) {
                    mData.removeReminder(i);
                    cancelAlarm(3);
                }
                break;
        }
        updateGui();
    }

    //Toolbar menu is clicked
    public void onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.event_action_settings) {
            mData.deleteEvent();
            returnToOverview();
        }
    }

    //AlarmManager & Notifications
    public void startAlarm(Date reminderTime, int requestCode) {
        /*
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(reminderTime.getTime());*/

        Log.d("ALARM", "Neuer Alarm am: " + reminderTime.toString());
        AlarmManager alarmManager = (AlarmManager) mActivity.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(mActivity, AlertReceiver.class);
        intent.putExtra("TITLE", "Erinnerung: " + mData.getmEvent().getTitle());
        intent.putExtra("TEXT", "am " + formatDate(mData.getmEvent().getTime()) + " um " + formatTime(mData.getmEvent().getTime()) + " Uhr");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mActivity, requestCode, intent, 0);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, reminderTime.getTime(), pendingIntent); //removed c.getTimeInMillis()
    }

    //Remove Alarm from AlarmMangager
    public void cancelAlarm(int requestCode) {
        Log.d("ALARM","Stopped Alarm!");
        AlarmManager alarmManager = (AlarmManager) mActivity.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(mActivity, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mActivity, requestCode, intent, 0);

        alarmManager.cancel(pendingIntent);
    }
}