package com.example.robin.angrynerds_wip.overview.imageFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentInit;

public class OverviewImageInit extends OverviewFragmentInit {
    /* Yannick-Luca Rüttgers
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
