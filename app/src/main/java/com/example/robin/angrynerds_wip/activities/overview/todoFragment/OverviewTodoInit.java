package com.example.robin.angrynerds_wip.activities.overview.todoFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.overview.superClasses.OverviewFragmentInit;

public class OverviewTodoInit extends OverviewFragmentInit {
    /* Yannick-Luca Rüttgers
    Initiates all necessary Components for TodoFragment
    */
    public OverviewTodoInit(){
        super();
    }

    public void initData(){
        mData = new OverviewTodoData();
    }

    public void initGui(){
        mGui = new OverviewTodoGui();
    }

    public void initController(){
        mController = new OverviewTodoController(this, mData, mGui);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview_todo, container, false);
        super.onCreateView(getArguments(), view);
        return view;
    }

}
