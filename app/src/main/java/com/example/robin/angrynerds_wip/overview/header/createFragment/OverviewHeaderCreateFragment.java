package com.example.robin.angrynerds_wip.overview.header.createFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.overview.header.OverviewHeaderClickListener;
import com.example.robin.angrynerds_wip.overview.overviewActivity.OverviewInit;

public class OverviewHeaderCreateFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview_header_create, container, false);
        // Set OnClickListeners
        OverviewHeaderClickListener clickListener = new OverviewHeaderClickListener(((OverviewInit)getActivity()).getController());
        view.findViewById(R.id.id_overview_header_create_button_todo).setOnClickListener(clickListener);
        view.findViewById(R.id.id_overview_header_create_button_event).setOnClickListener(clickListener);
        view.findViewById(R.id.id_overview_header_create_textView_note).setOnClickListener(clickListener);
        view.findViewById(R.id.id_overview_header_create_button_search).setOnClickListener(clickListener);
        return view;
    }
}
