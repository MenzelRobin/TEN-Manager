package com.example.robin.angrynerds_wip.activities.event.logic;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.event.data.EventData;
import com.example.robin.angrynerds_wip.activities.event.logic.listener.EventClickListener;
import com.example.robin.angrynerds_wip.activities.event.gui.RecurringTypeHelper;
import com.example.robin.angrynerds_wip.activities.event.gui.DatePickerFragment;
import com.example.robin.angrynerds_wip.activities.event.gui.EventGui;
import com.example.robin.angrynerds_wip.activities.event.logic.listener.EventMenuItemClickListener;
import com.example.robin.angrynerds_wip.activities.event.logic.listener.TextWatcher;
import com.example.robin.angrynerds_wip.activities.event.gui.TimePickerFragment;
import com.example.robin.angrynerds_wip.activities.event.reminder.AlarmManagerHelper;
import com.example.robin.angrynerds_wip.activities.event.data.Reminder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class EventApplicationLogic {

    private EventGui mGui;
    private AppCompatActivity mActivity;
    private EventData mData;
    private Reminder mReminder;
    private AlarmManagerHelper mAlarmManagerhelper;
    private RecurringTypeHelper mRecurringTypeHelper;
    private NotificationManagerCompat mNotificationManager;

    public EventApplicationLogic(EventGui pGui, AppCompatActivity pActivity, EventData pData) {
        mGui = pGui;
        mActivity = pActivity;
        mData = pData;
        mReminder = new Reminder(mActivity);
        mRecurringTypeHelper = new RecurringTypeHelper(mActivity);
        mAlarmManagerhelper = new AlarmManagerHelper();
        initGui();
        initListener();

        mNotificationManager = NotificationManagerCompat.from(mActivity);
    }

    //initialize Toolbar including menu and back button
    private void initGui() {
        mActivity.setSupportActionBar(mGui.getToolbar());
        mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        mActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        if (mData.getEvent().getTitle() == "") {
            mGui.getEditTextTitle().setFocusableInTouchMode(true);
            mGui.getEditTextTitle().requestFocus();
        }
        updateGui();
    }

    //EventGui + Toolbar Clicklistener
    private void initListener() {
        EventClickListener clickListener;
        clickListener = new EventClickListener(this);
        EventMenuItemClickListener menuItemClickListener;
        menuItemClickListener = new EventMenuItemClickListener(this);
        mGui.getToolbar().setNavigationOnClickListener(clickListener);
        mGui.getToolbar().setOnMenuItemClickListener(menuItemClickListener);
        mGui.getEditTextDate().setOnClickListener(clickListener);
        mGui.getEditTextTime().setOnClickListener(clickListener);
        mGui.getEditTextRecurringType().setOnClickListener(clickListener);
        mGui.getEditTextNewReminder().setOnClickListener(clickListener);
        mGui.getIconCloseReminder1().setOnClickListener(clickListener);
        mGui.getIconCloseReminder2().setOnClickListener(clickListener);
        mGui.getIconCloseReminder3().setOnClickListener(clickListener);
        mGui.getIconCloseReminder4().setOnClickListener(clickListener);
        mGui.getIconNavigation().setOnClickListener(clickListener);
        //Textwatcher
        mGui.getEditTextTitle().addTextChangedListener(new TextWatcher(this, mGui.getEditTextTitle()));
        mGui.getEditTextLocation().addTextChangedListener(new TextWatcher(this, mGui.getEditTextLocation()));
    }

    //Fill EventGui Elements with EventData
    public void updateGui() {
        mGui.setTitle(mData.getEvent().getTitle());
        mGui.setTime(mData.getFormatedTime());
        mGui.setDate(mData.getFormatedDate());
        mGui.setLocation(mData.getEvent().getAddress());
        mGui.setColor(mData.getEvent().getColor(), mData.getEvent().getAccentColor());
        mGui.setRecurringType(mRecurringTypeHelper.getRecurringTypeString(mData.getEvent().getRecurringType()));
        mReminder = new Reminder(mActivity);
        mReminder.setReminder(mData.getEvent().getReminder());
        mGui.setReminder(mReminder, mData.getEvent().getTime());
        if (mData.getEvent().getAddress().length() > 1) {
            mGui.setNavigationVisible(true);
        }
        setAlarm();
    }

    //Return to overview if back pressed / Event deleted / toolbar navigation
    public void returnToOverview() {
        mActivity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.slide_out_right);
        mActivity.finish();
    }

    //Set alarm if reminder is in the future
    public void setAlarm() {
        Calendar actualTime = Calendar.getInstance();
        for (int i = 0; i < mData.getEvent().getReminder().size(); i++) {
            if (mData.getEvent().getReminder().get(i).after(actualTime.getTime())) {
                mAlarmManagerhelper.startAlarm(mActivity, mData, mData.getEvent().getReminder().get(i), i);
            }
        }
    }

    //Receive Date from DatePicker and save it
    public void receiveDate(int year, int month, int date) {
        mData.setDate(year, month, date);
        updateReminder();
    }

    //Receive Time from TimePicker and save it
    public void receiveTime(int hour, int minute) {
        mData.setTime(hour, minute);
        updateReminder();
    }

    //If Event Time is changed, reminder have to be updated
    public void updateReminder() {
        ArrayList<Date> eventReminder = new ArrayList<Date>();
        mReminder.setReminder(mData.getEvent().getReminder());

        if (mGui.getEditTextReminder1().length() > 0) {
            eventReminder.add(mReminder.getReminderFromLable(mGui.getEditTextReminder1().getText().toString(), mData.getEvent().getTime()));
        }
        if (mGui.getEditTextReminder2().length() > 0) {
            eventReminder.add(mReminder.getReminderFromLable(mGui.getEditTextReminder2().getText().toString(), mData.getEvent().getTime()));
        }
        if (mGui.getEditTextReminder3().length() > 0) {
            eventReminder.add(mReminder.getReminderFromLable(mGui.getEditTextReminder3().getText().toString(), mData.getEvent().getTime()));
        }
        if (mGui.getEditTextReminder4().length() > 0) {
            eventReminder.add(mReminder.getReminderFromLable(mGui.getEditTextReminder4().getText().toString(), mData.getEvent().getTime()));
        }
        mData.addReminderList(eventReminder);
        updateGui();
    }

    //Open DatePicker fragment dialog
    public void showDatePickerDialog(View v) {
        DatePickerFragment datePicker = new DatePickerFragment();
        datePicker.setDate(mData.getEvent().getTime());
        datePicker.show(mActivity.getFragmentManager(), "DatePicker");
    }

    //Open TimePicker fragment dialog with
    public void showTimePickerDialog(View v) {
        TimePickerFragment timePicker = new TimePickerFragment();
        timePicker.setTime(mData.getEvent().getTime());
        timePicker.show(mActivity.getFragmentManager(), "TimePicker");
    }

    //User wants to add new reminder
    public void onNewReminderClicked() {
        if (mData.getEvent().getReminder().size() != 4) {
            //Create a AlertDialog with suggested Reminder periods
            new AlertDialog.Builder(mActivity)
                    .setItems(mReminder.getReminderLabelString(), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mData.addReminder(mReminder.getReminderFromLable(mReminder.getReminderLabelString()[i], mData.getEvent().getTime()));
                            updateGui();
                        }
                    })
                    .setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            dialog.dismiss();
                            dialog.cancel();
                        }
                    })
                    .show();
        } else {
            new AlertDialog.Builder(mActivity)
                    .setMessage("Es ist nicht möglich, mehr als 4 Erinnerungen einzustellen.")
                    .setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            dialog.cancel();
                        }
                    })
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
            if (mData.getEvent().getAddress().length() > 1) {mGui.setNavigationVisible(true);}
            else {mGui.setNavigationVisible(false);}
        }
    }

    //Remove Reminder when close icon is clicked
    public void onCloseReminderClicked(int i) {
        if (i == 1 && mGui.getIconCloseReminder1().getAlpha() == (float) 0.5) {
            mData.removeReminder(i);
            mAlarmManagerhelper.cancelAlarm(mActivity, mData, 0);
        } else if (i == 2 && mGui.getIconCloseReminder2().getAlpha() == (float) 0.5) {
            mData.removeReminder(i);
            mAlarmManagerhelper.cancelAlarm(mActivity, mData, 1);
        } else if (i == 3 && mGui.getIconCloseReminder3().getAlpha() == (float) 0.5) {
            mData.removeReminder(i);
            mAlarmManagerhelper.cancelAlarm(mActivity, mData, 2);
        } else if (i == 4 && mGui.getIconCloseReminder4().getAlpha() == (float) 0.5) {
            mData.removeReminder(i);
            mAlarmManagerhelper.cancelAlarm(mActivity, mData, 3);
        }
        updateGui();
    }

    //Toolbar menu is clicked
    public void onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.event_action_settings_delete) {
            mData.deleteEvent();
            returnToOverview();
        } else if (item.getItemId() == R.id.event_action_settings_share) {
            mData.shareEvent();
        } else if (item.getItemId() == R.id.event_action_settings_calendar) {
            mData.exportToCalendar();
        }
    }

    public void onNavigationClicked() {
        if (mData.getEvent().getAddress() != "") {
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + mData.getEvent().getAddress());
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

            //If GMaps is installed, use GMaps!
            if (mapIntent.resolveActivity(mActivity.getPackageManager()) != null) {
                mapIntent.setPackage("com.google.android.apps.maps");
            }

            mActivity.startActivity(mapIntent);
        }
    }

    public void onRecurringTypeClicked() {
        new AlertDialog.Builder(mActivity)
            .setItems(mRecurringTypeHelper.getRecurringTypeStrings(), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    mData.setRecurringType(i);
                    updateGui();
                }
            }).show();
    }
}