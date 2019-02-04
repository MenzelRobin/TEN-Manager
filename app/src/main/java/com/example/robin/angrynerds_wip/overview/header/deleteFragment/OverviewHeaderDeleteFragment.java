package com.example.robin.angrynerds_wip.overview.header.deleteFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.overview.header.OverviewHeaderClickListener;
import com.example.robin.angrynerds_wip.overview.overviewActivity.OverviewInit;

public class OverviewHeaderDeleteFragment extends Fragment {
    /* Yannick-Luca Rüttgers
    Header Fragment for the deletionprocess
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview_header_delete, container, false);
        // Set OnClickListeners
        OverviewHeaderClickListener clickListener = new OverviewHeaderClickListener(((OverviewInit) getActivity()).getController());
        view.findViewById(R.id.id_overview_header_delete_button_back).setOnClickListener(clickListener);
        view.findViewById(R.id.id_overview_header_delete_button_delete).setOnClickListener(clickListener);
        return view;
    }
}
