package com.example.robin.angrynerds_wip.overview.eventFragment;

import android.os.Bundle;

import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentData;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OverviewEventData extends OverviewFragmentData {

    String mTime;
    String mDate;
    String mYear;
    String mLocation;

    // Adds the Note data to this Object
    public void addData(Bundle pData){
        super.addData(pData);
        Date pDate = new Date(Long.parseLong(pData.getString("time")));
        SimpleDateFormat time = new SimpleDateFormat("hh:mm");
        SimpleDateFormat date = new SimpleDateFormat("E, dd. MMM");
        SimpleDateFormat year = new SimpleDateFormat("YYYY");
        mTime = time.format(pDate);
        mDate = date.format(pDate);
        mYear = year.format(pDate);
        mLocation = pData.getString("Street") + ", " + pData.getInt("HouseNumber") + ", " + pData.getString("Plz") + " " + pData.getString("City");
    }

    // Returns the Time
    public String getTime() {
        return mTime;
    }

    // Returns the Date
    public String getDate() {
        return mDate;
    }

    // Returns the Year
    public String getYear() {
        return mYear;
    }

    // Returns the Location
    public String getLocation() {
        return mLocation;
    }
}
