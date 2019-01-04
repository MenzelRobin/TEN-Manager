package com.example.robin.angrynerds_wip.overview.todoFragment;

import android.content.Intent;

import com.example.robin.angrynerds_wip.MainActivity;
import com.example.robin.angrynerds_wip.overview.noteFragment.OverviewNoteData;
import com.example.robin.angrynerds_wip.overview.noteFragment.OverviewNoteGui;
import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentController;
import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentData;
import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentGui;
import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentInit;

public class OverviewTodoController extends OverviewFragmentController {

    // Calls the superconstructor
    public OverviewTodoController(OverviewFragmentInit pFragment, OverviewFragmentData pData, OverviewFragmentGui pGui){
        super(pFragment, pData, pGui);
    }

    // Applies saved Data to the Gui
    public void applyData(){
        ((OverviewTodoGui)mGui).setTitle(mData.getTitle());
        ((OverviewTodoGui)mGui).setColor(mData.getColor());
        ((OverviewTodoGui)mGui).setNote(((OverviewTodoData)mData).getNote());
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
    public void clicked(){
        if(mDeleteState) {
            toggleMark();
        } else {
            //Todo: Add Create Todo Activity
            Intent intent = new Intent(mFragment.getActivity(), MainActivity.class);
            intent.putExtra("ID", mData.getID());
            mFragment.startActivity(intent);
        }
    }
}
