package com.example.robin.angrynerds_wip.overview.overviewActivity;

import android.content.res.Configuration;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.robin.angrynerds_wip.R;

public class OverviewGui {

    private OverviewInit mActivity;

    private int mHeaderId;
    private int[] mContainerIDs;

    private LinearLayout mFooter;
    private Button mShowAll;
    private Button mShowTodo;
    private Button mShowEvent;
    private Button mShowNote;

    OverviewGui(OverviewInit pActivity){
        mActivity = pActivity;

        // Sets Layout and Containerids depending on orientation
        if(pActivity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            mActivity.setContentView(R.layout.activity_overview);
            mContainerIDs = new int[]{R.id.id_overview_linearLayout_container};
        } else {
            mActivity.setContentView(R.layout.activity_overview_landscape);
            mContainerIDs = new int[]{R.id.id_overview_linearLayout_container1, R.id.id_overview_linearLayout_container2};
        }


        // Container
        mHeaderId = R.id.id_overview_relativeLayout_header;
        mFooter = mActivity.findViewById(R.id.id_overview_linearLayout_footer);
        // Footer Buttons
        mShowAll = mActivity.findViewById(R.id.id_overview_button_all);
        mShowTodo = mActivity.findViewById(R.id.id_overview_button_todo);
        mShowEvent = mActivity.findViewById(R.id.id_overview_button_event);
        mShowNote = mActivity.findViewById(R.id.id_overview_button_note);
    }

    /*
    public void clearContainer(){
        for(int containerID : mContainerIDs){
            ((LinearLayout)mActivity.findViewById(containerID)).removeAllViewsInLayout();
        }
    }*/

    public void hideFooter(){
        mFooter.setVisibility(View.GONE);
    }

    public void showFooter(){
        mFooter.setVisibility(View.VISIBLE);
    }

    public int[] getContainerIDs(){
        return mContainerIDs;
    }

    public int getHeaderId() { return mHeaderId; }

    public Button getShowAll() {
        return mShowAll;
    }

    public Button getShowTodo() {
        return mShowTodo;
    }

    public Button getShowEvent() {
        return mShowEvent;
    }

    public Button getShowNote() {
        return mShowNote;
    }
}
