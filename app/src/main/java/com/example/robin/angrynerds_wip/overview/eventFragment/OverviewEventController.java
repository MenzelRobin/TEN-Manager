package com.example.robin.angrynerds_wip.overview.eventFragment;

import android.content.Intent;

import com.example.robin.angrynerds_wip.MainActivity;
import com.example.robin.angrynerds_wip.overview.noteFragment.OverviewNoteData;
import com.example.robin.angrynerds_wip.overview.noteFragment.OverviewNoteGui;
import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentController;
import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentData;
import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentGui;
import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentInit;

public class OverviewEventController extends OverviewFragmentController {

    // Calls the superconstructor
    public OverviewEventController(OverviewFragmentInit pFragment, OverviewFragmentData pData, OverviewFragmentGui pGui){
        super(pFragment, pData, pGui);
    }

    // Applies saved Data to the Gui
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
            //Todo: Add Create Event Activity
            Intent intent = new Intent(mFragment.getActivity(), MainActivity.class);
            intent.putExtra("ID", mData.getID());
            mFragment.startActivity(intent);
        }
    }
}
