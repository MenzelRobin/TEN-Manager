package com.example.robin.angrynerds_wip.activities.overview.imageFragment;

import android.content.Intent;

import com.example.robin.angrynerds_wip.activities.note.note.NoteActivity;
import com.example.robin.angrynerds_wip.activities.overview.superClasses.OverviewFragmentController;
import com.example.robin.angrynerds_wip.activities.overview.superClasses.OverviewFragmentData;
import com.example.robin.angrynerds_wip.activities.overview.superClasses.OverviewFragmentGui;
import com.example.robin.angrynerds_wip.activities.overview.superClasses.OverviewFragmentInit;

public class OverviewImageController extends OverviewFragmentController {
    /* Yannick-Luca Ruettgers
    Contains the Applicationlogic for the Image Fragment
     */

    // Calls the superconstructor
    public OverviewImageController(OverviewFragmentInit pFragment, OverviewFragmentData pData, OverviewFragmentGui pGui){
        super(pFragment, pData, pGui);
    }

    // Applies saved EventData to the EventGui
    public void applyData(){
        ((OverviewImageGui)mGui).setTitle(mData.getTitle());
        ((OverviewImageGui)mGui).setColor(mData.getColor());
        ((OverviewImageGui)mGui).setImage(((OverviewImageData)mData).getImage());
    }

    // Adds an OnClickListener to the Fragment
    public void addOnClickListener(){
        ((OverviewImageGui)mGui).getOverviewImage().setOnClickListener(super.getOnClickListener());
    }

    // Adds an OnLongClickListener to the Fragment
    public void addOnLongClickListener(){
        ((OverviewImageGui)mGui).getOverviewImage().setOnLongClickListener(super.getOnLongClickListener());
    }

    // Called by the OnClickListener, starts a new Activity
    public void clicked(){
        if(mDeleteState) {
            toggleMark();
        } else {
            Intent intent = new Intent(mFragment.getActivity(), NoteActivity.class);
            intent.putExtra("ID", mData.getID());
            mFragment.startActivity(intent);
        }
    }
}
