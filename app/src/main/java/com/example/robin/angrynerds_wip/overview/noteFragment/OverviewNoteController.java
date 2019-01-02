package com.example.robin.angrynerds_wip.overview.noteFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.robin.angrynerds_wip.MainActivity;
import com.example.robin.angrynerds_wip.data.models.tens.Note;

public class OverviewNoteController {

    OverviewNoteInit mFragment;
    OverviewNoteData mData;
    OverviewNoteGui mGui;

    // Constructor
    public OverviewNoteController(OverviewNoteInit pFragment, OverviewNoteData pData, OverviewNoteGui pGui){
        mFragment = pFragment;
        mData = pData;
        mGui = pGui;
    }

    // Passes Data to Dataobject
    public void addData(Bundle pData){
        mData.addData(pData);
    }

    // Passes the View to the Guiobject and applies the Data
    public void addView(View pView){
        mGui.addView(pView);
        applyData();
    }

    // Adds an OnClickListener to the Fragment
    public void addOnClickListener(){
        OverviewNoteClickListener clickListener = new OverviewNoteClickListener(this);
        mGui.getOverviewNote().setOnClickListener(clickListener);
    }

    // Called by the OnClickListener, starts a new Activity
    public void clicked(){
        Intent intent = new Intent(mFragment.getActivity(), MainActivity.class);
        intent.putExtra("ID", mData.getID()); // Hier können eigene Parameter hinzugefügt werden.
        mFragment.startActivity(intent); // Activity Starten
    }

    // Applies saved Data to the Gui
    public void applyData(){
        mGui.setTitle(mData.getTitle());
        mGui.setColor(mData.getColor());
        mGui.setDescription(mData.getDescription());
    }
}
