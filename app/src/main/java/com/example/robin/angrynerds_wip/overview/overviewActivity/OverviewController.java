package com.example.robin.angrynerds_wip.overview.overviewActivity;

import android.support.v4.app.Fragment;

import com.example.robin.angrynerds_wip.data.services.Delete;
import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentInit;

import java.util.ArrayList;
import java.util.List;

public class OverviewController {

    private OverviewInit mActivity;
    private OverviewData mData;
    private OverviewGui mGui;
    private OverviewFragmentFactory mFragmentFactory;
    private OverviewFragmentInserter mFragmentInserter;

    OverviewController(OverviewInit pActivity, OverviewData pData, OverviewGui pGui) {
        mActivity = pActivity;
        mData = pData;
        mGui = pGui;
        mFragmentFactory = new OverviewFragmentFactory();
        mFragmentInserter = new OverviewFragmentInserter(mActivity.getSupportFragmentManager());
        initHeader();
        initOnClickListener();
        refreshFragments();
    }

    // Inserts the Headerfragment
    public void initHeader(){
        mFragmentInserter.insertFragment(mGui.getHeaderId(), mFragmentFactory.createHeaderCreateFragment(), "HEADER_FRAGMENT");
    }

    // Sets on ClickListeners for Filterbuttons
    public void initOnClickListener(){
        OverviewClickListener clickListener = new OverviewClickListener(this);
        mGui.getShowAll().setOnClickListener(clickListener);
        mGui.getShowTodo().setOnClickListener(clickListener);
        mGui.getShowEvent().setOnClickListener(clickListener);
        mGui.getShowNote().setOnClickListener(clickListener);
    }


    public void onResume(){
        mData.refresh();
        refreshFragments();
    }

    //Clickhandler:
    public void newTodo(){
        //Todo: Add Create Todo Activity
    }

    public void newEvent(){
        //Todo: Add Create Event Activity
    }

    public void newNote(){
         //Todo: Add Create Note Activity
    }

    // Activates the Deletionmode
    public void longClick(){
        List<Fragment> fragments = mActivity.getSupportFragmentManager().getFragments();
        for(Fragment fragment : fragments){
            if(fragment.getTag() != "HEADER_FRAGMENT") ((OverviewFragmentInit)fragment).getController().setDeleteState(true);
        }
        mFragmentInserter.replaceFragment(mGui.getHeaderId(), mFragmentFactory.createHeaderDeleteFragment(), "HEADER_FRAGMENT");
        mGui.hideFooter();
    }

    // Goes back to normal State
    public void back(){
        List<Fragment> fragments = mActivity.getSupportFragmentManager().getFragments();
        for(Fragment fragment : fragments){
            if(fragment.getTag() != "HEADER_FRAGMENT") ((OverviewFragmentInit)fragment).getController().setDeleteState(false);
        }
        mFragmentInserter.replaceFragment(mGui.getHeaderId(), mFragmentFactory.createHeaderCreateFragment(), "HEADER_FRAGMENT");
        mGui.showFooter();
    }

    // Deletes selected Items and goes back to normal State
    public void delete(){
        ArrayList<String> toDelete = new ArrayList();
        List<Fragment> fragments = mActivity.getSupportFragmentManager().getFragments();
        for(Fragment fragment : fragments){
            if(fragment.getTag() != "HEADER_FRAGMENT") {
                if(((OverviewFragmentInit)fragment).getController().getMarked()){
                    toDelete.add(((OverviewFragmentInit) fragment).getController().getTENID());
                }
                ((OverviewFragmentInit)fragment).getController().setDeleteState(false);
            }
        }
        Delete.deleteMultipleTENs(toDelete);
        refreshFragments();
        mFragmentInserter.replaceFragment(mGui.getHeaderId(), mFragmentFactory.createHeaderCreateFragment(), "HEADER_FRAGMENT");
        mGui.showFooter();
    }

    // Shows TENs depending on selection
    public void show(Class pClass){
        mData.refresh();
        mData.filter(pClass);
        refreshFragments();
    }

    // Populates the Containers with the existing Data
    public void refreshFragments(){
        mFragmentInserter.insertFragments(mGui.getContainerIDs(), mFragmentFactory.createTENFragments(mData.getData()));
    }
}