package com.example.robin.angrynerds_wip.overview.overviewActivity;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.robin.angrynerds_wip.R;

public class OverviewGui {

    private OverviewInit mActivity;

    private int mContainer1Id;
    private int mContainer2Id;
    private int mHeaderId;

    private LinearLayout mContainer1;
    private LinearLayout mContainer2;
    private LinearLayout mFooter;
    private Button mShowAll;
    private Button mShowTodo;
    private Button mShowEvent;
    private Button mShowNote;

    OverviewGui(OverviewInit pActivity){
        mActivity = pActivity;
        mActivity.setContentView(R.layout.activity_overview);
        // IDs for Fragment Input
        mHeaderId = R.id.id_overview_relativeLayout_header;
        mContainer1Id = R.id.id_overview_linearLayout_container1;
        mContainer2Id = R.id.id_overview_linearLayout_container2;
        // Container
        mContainer1 = mActivity.findViewById(mContainer1Id);
        mContainer2 = mActivity.findViewById(mContainer2Id);
        mFooter = mActivity.findViewById(R.id.id_overview_linearLayout_footer);
        // Footer Buttons
        mShowAll = mActivity.findViewById(R.id.id_overview_button_all);
        mShowTodo = mActivity.findViewById(R.id.id_overview_button_todo);
        mShowEvent = mActivity.findViewById(R.id.id_overview_button_event);
        mShowNote = mActivity.findViewById(R.id.id_overview_button_note);

        //clearContainer();
    }

    public void clearContainer(){
        mContainer1.removeAllViewsInLayout();
        mContainer2.removeAllViewsInLayout();
    }

    public void hideFooter(){
        mFooter.setVisibility(View.GONE);
    }

    public void showFooter(){
        mFooter.setVisibility(View.VISIBLE);
    }

    public int getContainer1Id(){
        return mContainer1Id;
    }

    public int getContainer2Id(){
        return mContainer2Id;
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
