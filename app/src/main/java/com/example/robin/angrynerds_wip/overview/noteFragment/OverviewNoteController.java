package com.example.robin.angrynerds_wip.overview.noteFragment;

import android.content.Intent;
import android.view.View;

import com.example.robin.angrynerds_wip.MainActivity;
import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentController;
import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentData;
import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentGui;
import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentInit;

public class OverviewNoteController extends OverviewFragmentController {

    // Calls the superconstructor
    public OverviewNoteController(OverviewFragmentInit pFragment, OverviewFragmentData pData, OverviewFragmentGui pGui){
        super(pFragment, pData, pGui);
    }

    // Applies saved Data to the Gui
    public void applyData(){
        ((OverviewNoteGui)mGui).setTitle(mData.getTitle());
        ((OverviewNoteGui)mGui).setColor(mData.getColor());
        ((OverviewNoteGui)mGui).setDescription(((OverviewNoteData)mData).getDescription());
    }

    // Adds an OnClickListener to the Fragment
    public void addOnClickListener(){
        OverviewNoteClickListener clickListener = new OverviewNoteClickListener(this);
        ((OverviewNoteGui)mGui).getOverviewNote().setOnClickListener(clickListener);
    }

    // Called by the OnClickListener, starts a new Activity
    public void clicked(){
        //Todo: Add Note Activity
        Intent intent = new Intent(mFragment.getActivity(), MainActivity.class);
        intent.putExtra("ID", mData.getID());
        mFragment.startActivity(intent);
    }
}