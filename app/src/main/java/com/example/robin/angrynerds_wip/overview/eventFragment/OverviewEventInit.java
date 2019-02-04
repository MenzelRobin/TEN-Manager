package com.example.robin.angrynerds_wip.overview.eventFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentInit;

public class OverviewEventInit extends OverviewFragmentInit {
    /* Yannick-Luca Rüttgers
    Initiates all necessary components for the Eventfragment
     */

    public OverviewEventInit(){
        super();
    }

    public void initData(){
        mData = new OverviewEventData();
    }

    public void initGui(){
        mGui = new OverviewEventGui();
    }

    public void initController(){
        mController = new OverviewEventController(this, mData, mGui);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview_event, container, false);
        super.onCreateView(getArguments(), view);
        return view;
    }
}
