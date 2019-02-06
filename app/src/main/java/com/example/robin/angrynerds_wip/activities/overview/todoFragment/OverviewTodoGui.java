package com.example.robin.angrynerds_wip.activities.overview.todoFragment;

import android.content.Context;
import android.support.v4.graphics.ColorUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.overview.superClasses.OverviewFragmentGui;

public class OverviewTodoGui extends OverviewFragmentGui {
    /* Yannick-Luca Ruettgers
    Manages the GUI for TodoFragment
    */

    private RelativeLayout mOverviewTodo;
    private TextView mTitle;
    private TextView mNote;
    private TextView mCounter;
    private LinearLayout mContainer1;
    private LinearLayout mContainer2;
    private int mCountChecked;
    private int mCountAll;

    // Adds the Fragment view to this Object
    public void addView(View pView){
        super.addView(pView);
        mChecked = mView.findViewById(R.id.id_overview_todo_imageView_checked);
        mUnchecked = mView.findViewById(R.id.id_overview_todo_imageView_unchecked);
        mOverviewTodo = mView.findViewById(R.id.id_overview_todo);
        mTitle = mView.findViewById(R.id.id_overview_todo_textView_title);
        mNote = mView.findViewById(R.id.id_overview_todo_textView_note);
        mCounter = mView.findViewById(R.id.id_overview_todo_textView_counter);
        mContainer1 = mView.findViewById(R.id.id_overview_todo_linearLayout_container1);
        mContainer2 = mView.findViewById(R.id.id_overview_todo_linearLayout_container2);
        mCountChecked = 0;
        mCountAll = 0;
    }

    // Returns mOverviewNote
    public RelativeLayout getOverviewTodo(){
        return mOverviewTodo;
    }

    // Sets Title
    public void setTitle(String pTitle){
        mTitle.setText(pTitle);
    }

    // Sets Note
    public void setNote(String pNote) {
        mNote.setText(pNote);
    }

    // Sets Color
    public void setColor(int pColor){
        mOverviewTodo.setBackgroundColor(pColor);
    }

    // Generates a Todocheckbox
    public void addCheckbox(boolean pStatus, String pDescription){
        // Set Layout
        Context context = mView.getContext();
        TextView checkbox = new TextView(context);
        checkbox.setTextColor(context.getResources().getColor(R.color.colorWhite));
        checkbox.setGravity(Gravity.CENTER_VERTICAL);
        // Set Status
        if(pStatus) {
            checkbox.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check_box_white_24dp, 0, 0, 0);
            checkbox.setTextColor(ColorUtils.setAlphaComponent(mView.getResources().getColor(R.color.colorWhite), 150));
        } else {
            checkbox.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check_box_outline_blank_white_24dp, 0, 0, 0);
        }
        //Set Description
        checkbox.setText(pDescription);
        // Add Checkbox to alternating container
        if(mCountAll%2==0){
            mContainer1.addView(checkbox);
        } else {
            mContainer2.addView(checkbox);
        }
        mCountAll++;
        if(pStatus) mCountChecked++;
    }

    public void setCounter(){
        mCounter.setText(mCountChecked + "/" + mCountAll);
    }
}
