package com.example.robin.angrynerds_wip.overview.overviewActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;
import com.example.robin.angrynerds_wip.overview.eventFragment.OverviewEventInit;
import com.example.robin.angrynerds_wip.overview.noteFragment.OverviewNoteInit;
import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentInit;
import com.example.robin.angrynerds_wip.overview.todoFragment.OverviewTodoInit;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class OverviewController {

    private OverviewInit mActivity;
    private OverviewData mData;
    private OverviewGui mGui;

    OverviewController(OverviewInit pActivity, OverviewData pData, OverviewGui pGui) {
        mActivity = pActivity;
        mData = pData;
        mGui = pGui;
        mData.filterNotes();
        insertFragments(generateFragments());
    }

    public void onResume(){
        // Todo: Check in Data wether Objects have changed? Refresh if so, add Remove Contents Method to GUI
        //mGui.insertFragments(generateFragments());
    }

    // Generates an ArrayList containing Fragments which differ depending on their type
    public ArrayList<Fragment> generateFragments(){
        ArrayList<Fragment> fragments = new ArrayList();
        ArrayList<TEN> TENs = mData.getData();
        for(TEN ten : TENs) {
            if(ten.getClass() == Todo.class){
                OverviewTodoInit fragment = new OverviewTodoInit();
                fragment.setArguments(ten.getBundle());
                //fragment.addData((Todo) ten);
                fragments.add(fragment);
            }
            if(ten.getClass() == Event.class){
                OverviewEventInit fragment = new OverviewEventInit();
                fragment.setArguments(ten.getBundle());
                //fragment.addData((Event) ten);
                fragments.add(fragment);
            }
            if(ten.getClass() == Note.class) {
                //Todo: If the Note only contains an Image, create an OverviewImage Fragment instead.
                OverviewNoteInit fragment = new OverviewNoteInit();
                fragment.setArguments(ten.getBundle());
                //fragment.addData((Note) ten);
                fragments.add(fragment);
            }
        }
        return fragments;
    }

    public void insertFragments(ArrayList<Fragment> pFragments){
        FragmentManager fragmentManager = mActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        int counter = 0;
        for(Fragment fragment : pFragments) {
            fragmentTransaction.add(counter%2==0?mGui.getContainer1Id():mGui.getContainer2Id(), fragment);
            counter++;
        }
        fragmentTransaction.commit();
    }

    public void longClick(){
        List<Fragment> fragments = mActivity.getSupportFragmentManager().getFragments();
        Log.d("LOGTAG", "Setting Delete State");
        for(Fragment fragment : fragments){
            ((OverviewFragmentInit)fragment).setDeleteState(true);
        }
        //Todo: Switch Header Fragment
    }
}
