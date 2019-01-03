package com.example.robin.angrynerds_wip.overview.noteFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentInit;

public class OverviewNoteInit extends OverviewFragmentInit {

    /* Replaced by superclass
    private OverviewNoteData mData;
    private OverviewNoteGui mGui;
    private OverviewNoteController mController;*/

    public OverviewNoteInit(){
        initData();
        initGui();
        initController();
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
        mController.addData(getArguments());
        mController.addView(view);
        ((OverviewNoteController)mController).addOnClickListener();
        return view;
    }
}
