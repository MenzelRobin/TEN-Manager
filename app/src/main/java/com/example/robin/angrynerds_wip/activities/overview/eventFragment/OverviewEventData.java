package com.example.robin.angrynerds_wip.activities.overview.eventFragment;

import android.os.Bundle;

import com.example.robin.angrynerds_wip.data.models.utils.BundleKeys;
import com.example.robin.angrynerds_wip.activities.overview.superClasses.OverviewFragmentData;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OverviewEventData extends OverviewFragmentData {
    /* Yannick-Luca Ruettgers
    Contains the Data for the Eventfragment. Modeled after Event-Object. Splits up Time into different formats
     */

    String mTime;
    String mDate;
    String mYear;
    String mLocation;

    // Adds the Note data to this Object
    public void addData(Bundle pData){
        super.addData(pData);
        Date pDate = new Date(pData.getLong(BundleKeys.KEY_EVENT_TIME));
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        SimpleDateFormat date = new SimpleDateFormat("E, dd. MMM");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        mTime = time.format(pDate);
        mDate = date.format(pDate);
        mYear = year.format(pDate);
        mLocation = pData.getString(BundleKeys.KEY_EVENT_ADDRESS);
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
