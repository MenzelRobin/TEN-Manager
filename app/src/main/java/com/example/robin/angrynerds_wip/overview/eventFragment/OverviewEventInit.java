package com.example.robin.angrynerds_wip.overview.eventFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.data.models.tens.Event;

public class OverviewEventInit extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_overview_event, container, false);
    }

    public void addData(Event pData){

    }
}
