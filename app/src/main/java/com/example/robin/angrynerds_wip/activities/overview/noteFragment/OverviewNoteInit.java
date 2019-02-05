package com.example.robin.angrynerds_wip.activities.overview.noteFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.overview.superClasses.OverviewFragmentInit;

public class OverviewNoteInit extends OverviewFragmentInit {
    /* Yannick-Luca Rüttgers
    Initiates all necessary components for the Note Fragment
     */

    public OverviewNoteInit(){
        super();
    }

    public void initData(){
        mData = new OverviewNoteData();
    }

    public void initGui(){
        mGui = new OverviewNoteGui();
    }

    public void initController(){
        mController = new OverviewNoteController(this, mData, mGui);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview_note, container, false);
        super.onCreateView(getArguments(), view);
        return view;
    }
}