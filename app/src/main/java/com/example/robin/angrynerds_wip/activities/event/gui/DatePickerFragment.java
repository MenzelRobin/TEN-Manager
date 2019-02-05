package com.example.robin.angrynerds_wip.activities.event.gui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.event.EventActivity;

import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    /* Robin Menzel
    Contains the DatePicker Fragment to choose the Date.
    */
    public Date mTime;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year, month, day;

        if (mTime != null) {
            c.setTime(mTime);
        }

        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dp = new DatePickerDialog(getActivity(), this, year, month, day);
        dp.setCancelable(true);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), R.style.Theme_AppCompat_Light_Dialog, this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        //Return Date to Activity
        ((EventActivity) getActivity()).receiveDate(year, month, day);
    }

    public void setDate(Date pTime) {
        mTime = pTime;
    }


}
