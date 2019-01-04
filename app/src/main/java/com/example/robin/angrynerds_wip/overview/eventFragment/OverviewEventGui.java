package com.example.robin.angrynerds_wip.overview.eventFragment;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentGui;

public class OverviewEventGui extends OverviewFragmentGui {

    private RelativeLayout mOverviewEvent;
    private TextView mTitle;
    private TextView mTime;
    private TextView mDate;
    private TextView mYear;
    private TextView mLocation;

    // Adds the Fragment view to this Object
    public void addView(View pView){
        super.addView(pView);
        mChecked = mView.findViewById(R.id.id_overview_event_imageView_checked);
        mUnchecked = mView.findViewById(R.id.id_overview_event_imageView_unchecked);
        mOverviewEvent = mView.findViewById(R.id.id_overview_event);
        mTitle = mView.findViewById(R.id.id_overview_event_textView_title);
        mTime = mView.findViewById(R.id.id_overview_event_textView_time);
        mDate = mView.findViewById(R.id.id_overview_event_textView_date);
        mYear = mView.findViewById(R.id.id_overview_event_textView_year);
        mLocation = mView.findViewById(R.id.id_overview_event_textView_location);
    }

    // Returns mOverviewNote
    public RelativeLayout getOverviewEvent(){
        return mOverviewEvent;
    }

    // Sets Title
    public void setTitle(String pTitle){
        mTitle.setText(pTitle);
    }

    // Sets Color
    public void setColor(int pColor){
        mOverviewEvent.setBackgroundColor(pColor);
    }

    // Sets Time
    public void setTime(String pTime) {
        this.mTime.setText(pTime);
    }

    // Sets Date
    public void setDate(String pDate) {
        this.mDate.setText(pDate);
    }

    // Sets Year
    public void setYear(String pYear) {
        this.mYear.setText(pYear);
    }

    // Sets Location
    public void setLocation(String pLocation) {
        this.mLocation.setText(pLocation);
    }

}
