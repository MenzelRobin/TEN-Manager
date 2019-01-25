package com.example.robin.angrynerds_wip.overview.imageFragment;

import android.content.Intent;

import com.example.robin.angrynerds_wip.MainActivity;
import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentController;
import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentData;
import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentGui;
import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentInit;

public class OverviewImageController extends OverviewFragmentController {

    // Calls the superconstructor
    public OverviewImageController(OverviewFragmentInit pFragment, OverviewFragmentData pData, OverviewFragmentGui pGui){
        super(pFragment, pData, pGui);
    }

    // Applies saved Data to the Gui
    public void applyData(){
        //((OverviewNoteGui)mGui).setTitle(mData.getTitle());
        ((OverviewImageGui)mGui).setColor(mData.getColor());
        //((OverviewNoteGui)mGui).setDescription(((OverviewNoteData)mData).getDescription());
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
            //Todo: Add Create Note Activity
            Intent intent = new Intent(mFragment.getActivity(), MainActivity.class);
            intent.putExtra("ID", mData.getID());
            mFragment.startActivity(intent);
        }
    }
}