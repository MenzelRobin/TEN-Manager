package com.example.robin.angrynerds_wip.overview.overviewActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentInit;

import java.util.ArrayList;
import java.util.List;

public class OverviewController {

    private OverviewInit mActivity;
    private OverviewData mData;
    private OverviewGui mGui;
    private OverviewFragmentFactory mFragmentFactory;
    private FragmentManager mFragmentManager;

    OverviewController(OverviewInit pActivity, OverviewData pData, OverviewGui pGui) {
        mActivity = pActivity;
        mData = pData;
        mGui = pGui;
        mFragmentFactory = new OverviewFragmentFactory();
        mFragmentManager = mActivity.getSupportFragmentManager();

        initHeader();
        initOnClickListener();
        //Todo: Implement natural way to load fragments (When buttons are pressed) -> New Method
        show(Note.class);
        //mData.filter(Note.class);
        // mData.filterNotes();
        //insertFragments(mFragmentFactory.createTENFragments(mData.getData()));
    }

    public void initHeader(){
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.add(mGui.getHeaderId(), mFragmentFactory.createHeaderCreateFragment(), "HEADER_FRAGMENT");
        fragmentTransaction.commit();
    }

    public void initOnClickListener(){
        OverviewClickListener clickListener = new OverviewClickListener(this);
        mGui.getShowAll().setOnClickListener(clickListener);
        mGui.getShowTodo().setOnClickListener(clickListener);
        mGui.getShowEvent().setOnClickListener(clickListener);
        mGui.getShowNote().setOnClickListener(clickListener);
    }

    /* Todo: Check in Data wether Objects have changed? Refresh if so, add Remove Contents Method to GUI
    public void onResume(){}*/

    public void insertFragments(ArrayList<Fragment> pFragments){
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        int counter = 0;
        for(Fragment fragment : pFragments) {
            fragmentTransaction.add(counter%2==0?mGui.getContainer1Id():mGui.getContainer2Id(), fragment);
            counter++;
        }
        fragmentTransaction.commit();
    }

    //Clickhandler:
    public void newTodo(){
        Log.d("LOGTAG", "Create new Todo"); //TODO: newTodo
    }

    public void newEvent(){
        Log.d("LOGTAG", "Create new Event"); //TODO: newEvent
    }

    public void newNote(){
        Log.d("LOGTAG", "Create new Note"); //TODO: newNote
    }

    public void longClick(){
        List<Fragment> fragments = mActivity.getSupportFragmentManager().getFragments();
        for(Fragment fragment : fragments){
            if(fragment.getTag() != "HEADER_FRAGMENT") ((OverviewFragmentInit)fragment).setDeleteState(true);
        }
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(mGui.getHeaderId(), mFragmentFactory.createHeaderDeleteFragment(), "HEADER_FRAGMENT");
        fragmentTransaction.commit();
        mGui.hideFooter();
    }

    public void back(){
        List<Fragment> fragments = mActivity.getSupportFragmentManager().getFragments();
        for(Fragment fragment : fragments){
            if(fragment.getTag() != "HEADER_FRAGMENT") ((OverviewFragmentInit)fragment).setDeleteState(false);
        }
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(mGui.getHeaderId(), mFragmentFactory.createHeaderCreateFragment(), "HEADER_FRAGMENT");
        fragmentTransaction.commit();
        mGui.showFooter();
    }

    public void delete(){

    }

    public void show(Class pClass){
        mGui.clearContainer();
        mData.filter(pClass);
        insertFragments(mFragmentFactory.createTENFragments(mData.getData()));
    }
}