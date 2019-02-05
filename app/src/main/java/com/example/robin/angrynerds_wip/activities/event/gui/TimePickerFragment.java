package com.example.robin.angrynerds_wip.activities.event.gui;

import android.app.TimePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.TimePicker;
import android.text.format.DateFormat;

import com.example.robin.angrynerds_wip.activities.event.EventActivity;

import java.util.Calendar;
import java.util.Date;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    /* Robin Menzel
    Contains the TimePicker Fragment to choose the hour and minutes.
    */
    private Date mTime;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c;
        int hour, minute;

        c = Calendar.getInstance();

        if (mTime != null) {
            c.setTime(mTime);
        }

        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hour, int minute) {
        //Send Time to Activity
        ((EventActivity) getActivity()).receiveTime(hour, minute);
    }

    public void setTime(Date pTime) {
        mTime = pTime;
    }
}