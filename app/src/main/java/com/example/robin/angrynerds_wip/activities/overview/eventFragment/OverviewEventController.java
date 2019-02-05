package com.example.robin.angrynerds_wip.activities.overview.eventFragment;

import android.content.Intent;

import com.example.robin.angrynerds_wip.activities.overview.superClasses.*;
import com.example.robin.angrynerds_wip.activities.event.EventActivity;

public class OverviewEventController extends OverviewFragmentController {
    /* Yannick-Luca Rüttgers
    Contains the Applicationlogic for the Event Fragment
     */

    // Calls the superconstructor
    public OverviewEventController(OverviewFragmentInit pFragment, OverviewFragmentData pData, OverviewFragmentGui pGui){
        super(pFragment, pData, pGui);
    }

    // Applies saved EventData to the EventGui
    public void applyData(){
        ((OverviewEventGui)mGui).setTitle(mData.getTitle());
        ((OverviewEventGui)mGui).setColor(mData.getColor());
        ((OverviewEventGui)mGui).setTime(((OverviewEventData)mData).getTime());
        ((OverviewEventGui)mGui).setDate(((OverviewEventData)mData).getDate());
        ((OverviewEventGui)mGui).setYear(((OverviewEventData)mData).getYear());
        ((OverviewEventGui)mGui).setLocation(((OverviewEventData)mData).getLocation());
    }

    // Adds an OnClickListener to the Fragment
    public void addOnClickListener(){
        ((OverviewEventGui)mGui).getOverviewEvent().setOnClickListener(super.getOnClickListener());
    }

    // Adds an OnLongClickListener to the Fragment
    public void addOnLongClickListener(){
        ((OverviewEventGui)mGui).getOverviewEvent().setOnLongClickListener(super.getOnLongClickListener());
    }

    // Called by the OnClickListener, starts a new Activity
    public void clicked(){
        if(mDeleteState) {
            toggleMark();
        } else {
            Intent intent = new Intent(mFragment.getActivity(), EventActivity.class);
            intent.putExtra("ID", mData.getID());
            mFragment.startActivity(intent);
        }
    }
}
