package com.example.robin.angrynerds_wip.activities.overview.todoFragment;

import android.content.Intent;

import com.example.robin.angrynerds_wip.activities.overview.superClasses.OverviewFragmentController;
import com.example.robin.angrynerds_wip.activities.overview.superClasses.OverviewFragmentData;
import com.example.robin.angrynerds_wip.activities.overview.superClasses.OverviewFragmentGui;
import com.example.robin.angrynerds_wip.activities.overview.superClasses.OverviewFragmentInit;

public class OverviewTodoController extends OverviewFragmentController {
    /* Yannick-Luca Ruettgers
    Contains the Logic for Todofragment.
    */

    // Calls the superconstructor
    public OverviewTodoController(OverviewFragmentInit pFragment, OverviewFragmentData pData, OverviewFragmentGui pGui){
        super(pFragment, pData, pGui);
    }

    // Applies saved EventData to the EventGui
    public void applyData(){
        ((OverviewTodoGui)mGui).setTitle(mData.getTitle());
        ((OverviewTodoGui)mGui).setColor(mData.getColor());
        ((OverviewTodoGui)mGui).setNote(((OverviewTodoData)mData).getNote());
        generateCheckboxes();
        ((OverviewTodoGui)mGui).setCounter();
    }

    public void generateCheckboxes(){
        String[] description = ((OverviewTodoData)mData).getDescription();
        boolean[] status = ((OverviewTodoData)mData).getStatus();
        for(int i = 0; i < description.length; i++){
            ((OverviewTodoGui)mGui).addCheckbox(status[i],description[i]);
        }
    }

    // Adds an OnClickListener to the Fragment
    public void addOnClickListener(){
        ((OverviewTodoGui)mGui).getOverviewTodo().setOnClickListener(super.getOnClickListener());
    }

    // Adds an OnLongClickListener to the Fragment
    public void addOnLongClickListener(){
        ((OverviewTodoGui)mGui).getOverviewTodo().setOnLongClickListener(super.getOnLongClickListener());
    }

    // Called by the OnClickListener, starts a new Activity
    public void clicked() {
        if (mDeleteState) {
            toggleMark();
        } else {
            Intent intent = new Intent(mFragment.getActivity(), com.example.robin.angrynerds_wip.activities.todo.Init.class);
            intent.putExtra("ID", mData.getID());
            mFragment.startActivity(intent);
        }
    }
}
