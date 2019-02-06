package com.example.robin.angrynerds_wip.activities.overview.imageFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.overview.superClasses.OverviewFragmentInit;

public class OverviewImageInit extends OverviewFragmentInit {
    /* Yannick-Luca Ruettgers
    Initiates all necessary components for the Image Fragment
     */

    public OverviewImageInit(){
        super();
    }

    public void initData(){
        mData = new OverviewImageData();
    }

    public void initGui(){
        mGui = new OverviewImageGui();
    }

    public void initController(){
        mController = new OverviewImageController(this, mData, mGui);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview_image, container, false);
        super.onCreateView(getArguments(), view);
        return view;
    }
}
