package com.example.robin.angrynerds_wip.activities.event;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {
    public Date mTime;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        int year;
        int month;
        int day;

        if (mTime == null){
            // Current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);
        }else{
            final Calendar c = Calendar.getInstance();
            c.setTime(mTime);
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);
        }

        DatePickerDialog dp = new DatePickerDialog(getActivity(), this, year, month, day);
        dp.setCancelable(true);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        //Return Date to Activity
        ((Init)getActivity()).receiveDate(calendar.getTime());
    }
}
