package com.example.robin.angrynerds_wip.activities.todo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.app.DialogFragment;
import android.widget.DatePicker;

import com.example.robin.angrynerds_wip.R;

import java.util.Calendar;
import java.util.Date;

// Author: Florian Rath in cooperation with Robin Menzel
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{
    private Date mTime;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();

        if (mTime != null) {
            c.setTime(mTime);
        }

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            return new DatePickerDialog(getActivity(), R.style.Theme_AppCompat_Light_Dialog, this, year, month, day);
        }else{
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        ((Init)getActivity()).receiveDate(cal.getTime());
    }

    public void setDate(Date pTime) {
        mTime = pTime;
    }
}
