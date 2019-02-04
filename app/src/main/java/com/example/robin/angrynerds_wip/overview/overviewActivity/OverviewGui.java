package com.example.robin.angrynerds_wip.overview.overviewActivity;

import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.data.models.tens.*;

public class OverviewGui {
    /* Yannick-Luca Rüttgers
    Manages the GUI for OverviewActivity
     */

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

        // Sets Layout and Containerids depending on orientation and screensize
        DisplayMetrics metrics = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float yInches= metrics.heightPixels/metrics.ydpi;
        float xInches= metrics.widthPixels/metrics.xdpi;
        double diagonalInches = Math.sqrt(xInches*xInches + yInches*yInches);
        boolean isTablet = diagonalInches>=6.5;
        boolean isPortrait = pActivity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
        if(!isTablet && isPortrait){
            mActivity.setContentView(R.layout.activity_overview_1col);
            mContainerIDs = new int[]{R.id.id_overview_linearLayout_container};
        } else if((isTablet && isPortrait) || !isTablet){
            mActivity.setContentView(R.layout.activity_overview_2col);
            mContainerIDs = new int[]{R.id.id_overview_linearLayout_container1, R.id.id_overview_linearLayout_container2};
        } else if(isTablet){
            mActivity.setContentView(R.layout.activity_overview_3col);
            mContainerIDs = new int[]{R.id.id_overview_linearLayout_container1, R.id.id_overview_linearLayout_container2, R.id.id_overview_linearLayout_container3};
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

    // Overloads markButton
    public void markButton(){
        markButton(TEN.class);
    }

    // Highlights the selected Button
    public void markButton(Class pClass){
        mShowAll.setBackgroundColor(mActivity.getResources().getColor(pClass == TEN.class?R.color.colorGrey:R.color.colorWhite));
        mShowTodo.setBackgroundColor(mActivity.getResources().getColor(pClass == Todo.class?R.color.colorGrey:R.color.colorWhite));
        mShowEvent.setBackgroundColor(mActivity.getResources().getColor(pClass == Event.class?R.color.colorGrey:R.color.colorWhite));
        mShowNote.setBackgroundColor(mActivity.getResources().getColor(pClass == Note.class?R.color.colorGrey:R.color.colorWhite));
        mScrollView.fullScroll(View.FOCUS_UP);
    }

    // Hides the Footer
    public void hideFooter(){
        mFooter.setVisibility(View.GONE);
    }

    // Shows the Footer
    public void showFooter(){
        mFooter.setVisibility(View.VISIBLE);
    }

    public int[] getContainerIDs(){ return mContainerIDs; }

    public int getHeaderId() { return mHeaderId; }

    public Button getShowAll() { return mShowAll; }

    public Button getShowTodo() { return mShowTodo; }

    public Button getShowEvent() { return mShowEvent; }

    public Button getShowNote() { return mShowNote; }
}