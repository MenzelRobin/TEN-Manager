package com.example.robin.angrynerds_wip.overview.overviewActivity;

import android.content.res.Configuration;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;

public class OverviewGui {

    private OverviewInit mActivity;

    private int mHeaderId;
    private int[] mContainerIDs;

    private LinearLayout mFooter;
    private ScrollView mScrollView;
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
        mScrollView = mActivity.findViewById(R.id.id_overview_scrollView_center);
        // Footer Buttons
        mShowAll = mActivity.findViewById(R.id.id_overview_button_all);
        mShowTodo = mActivity.findViewById(R.id.id_overview_button_todo);
        mShowEvent = mActivity.findViewById(R.id.id_overview_button_event);
        mShowNote = mActivity.findViewById(R.id.id_overview_button_note);
    }

    public void markButton(Class pClass){
        mShowAll.setBackgroundColor(mActivity.getResources().getColor(R.color.colorGrey));
        mShowTodo.setBackgroundColor(mActivity.getResources().getColor(R.color.colorGrey));
        mShowEvent.setBackgroundColor(mActivity.getResources().getColor(R.color.colorGrey));
        mShowNote.setBackgroundColor(mActivity.getResources().getColor(R.color.colorGrey));
        if(pClass == TEN.class){
            mShowAll.setBackgroundColor(mActivity.getResources().getColor(R.color.colorWhite));
        }
        if(pClass == Todo.class){
            mShowTodo.setBackgroundColor(mActivity.getResources().getColor(R.color.colorWhite));
        }
        if(pClass == Event.class){
            mShowEvent.setBackgroundColor(mActivity.getResources().getColor(R.color.colorWhite));
        }
        if(pClass == Note.class){
            mShowNote.setBackgroundColor(mActivity.getResources().getColor(R.color.colorWhite));
        }
        mScrollView.fullScroll(View.FOCUS_UP);
    }

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
