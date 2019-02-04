package com.example.robin.angrynerds_wip.overview.noteFragment;

import android.content.Intent;

import com.example.robin.angrynerds_wip.MainActivity;
import com.example.robin.angrynerds_wip.activities.note.note.NoteActivity;
import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentController;
import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentData;
import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentGui;
import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentInit;

public class OverviewNoteController extends OverviewFragmentController {

    // Calls the superconstructor
    public OverviewNoteController(OverviewFragmentInit pFragment, OverviewFragmentData pData, OverviewFragmentGui pGui){
        super(pFragment, pData, pGui);
    }

    // Applies saved EventData to the EventGui
    public void applyData(){
        ((OverviewNoteGui)mGui).setTitle(mData.getTitle());
        ((OverviewNoteGui)mGui).setColor(mData.getColor());
        ((OverviewNoteGui)mGui).setDescription(((OverviewNoteData)mData).getDescription());
    }

    // Adds an OnClickListener to the Fragment
    public void addOnClickListener(){
        ((OverviewNoteGui)mGui).getOverviewNote().setOnClickListener(super.getOnClickListener());
    }

    // Adds an OnLongClickListener to the Fragment
    public void addOnLongClickListener(){
        ((OverviewNoteGui)mGui).getOverviewNote().setOnLongClickListener(super.getOnLongClickListener());
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