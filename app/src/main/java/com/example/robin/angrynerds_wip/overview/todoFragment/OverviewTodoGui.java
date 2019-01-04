package com.example.robin.angrynerds_wip.overview.todoFragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentGui;

public class OverviewTodoGui extends OverviewFragmentGui {

    private RelativeLayout mOverviewTodo;
    private TextView mTitle;
    private TextView mNote;
    private TextView mCounter;
    private LinearLayout mContainer1;
    private LinearLayout mContainer2;




    // Adds the Fragment view to this Object
    public void addView(View pView){
        super.addView(pView);
        mChecked = mView.findViewById(R.id.id_overview_todo_imageView_checked);
        mUnchecked = mView.findViewById(R.id.id_overview_todo_imageView_unchecked);
        mOverviewTodo = mView.findViewById(R.id.id_overview_todo);
        mTitle = mView.findViewById(R.id.id_overview_todo_textView_title);
        mNote = mView.findViewById(R.id.id_overview_todo_textView_text);
        mCounter = mView.findViewById(R.id.id_overview_todo_textView_counter);
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

    // Sets Counter
    public void setCounter(String pCounter) {
        mNote.setText(pCounter);
    }

    // Sets Color
    public void setColor(int pColor){
        mOverviewTodo.setBackgroundColor(pColor);
    }
}
