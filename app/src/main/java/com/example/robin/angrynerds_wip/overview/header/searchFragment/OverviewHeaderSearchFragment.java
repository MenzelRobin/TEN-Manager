package com.example.robin.angrynerds_wip.overview.header.searchFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.overview.header.OverviewHeaderClickListener;
import com.example.robin.angrynerds_wip.overview.overviewActivity.OverviewInit;

public class OverviewHeaderSearchFragment  extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview_header_search, container, false);
        // Set OnClickListeners
        OverviewHeaderClickListener clickListener = new OverviewHeaderClickListener(((OverviewInit) getActivity()).getController(), view);
        view.findViewById(R.id.id_overview_header_search_button_back).setOnClickListener(clickListener);
        view.findViewById(R.id.id_overview_header_search_button_search).setOnClickListener(clickListener);
        view.findViewById(R.id.id_overview_header_search_view_searchText).setFocusableInTouchMode(true);
        view.findViewById(R.id.id_overview_header_search_view_searchText).requestFocus();
        return view;
    }
}
