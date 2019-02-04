package com.example.robin.angrynerds_wip.overview.overviewActivity;

import com.example.robin.angrynerds_wip.overview.overviewActivity.fragmentManager.*;

public class OverviewController {

    private OverviewInit mActivity;
    private OverviewData mData;
    private OverviewGui mGui;
    private OverviewFragmentFactory mFragmentFactory;
    private OverviewFragmentInserter mFragmentInserter;
    private OverviewNewClickHandler mNewClickHandler;
    private OverviewDeleteHandler mDeleteHandler;

    OverviewController(OverviewInit pActivity, OverviewData pData, OverviewGui pGui) {
        mActivity = pActivity;
        mData = pData;
        mGui = pGui;
        mFragmentFactory = new OverviewFragmentFactory();
        mFragmentInserter = new OverviewFragmentInserter(mActivity.getSupportFragmentManager());
        mNewClickHandler = new OverviewNewClickHandler(mActivity);
        mDeleteHandler = new OverviewDeleteHandler(mActivity);
        initHeader();
        new OverviewClickListener(this, mGui);
        refreshFragments();
        mGui.markButton();
    }

    // Inserts the Headerfragment
    public void initHeader(){
        mFragmentInserter.insertFragment(mGui.getHeaderId(), mFragmentFactory.createHeaderCreateFragment(), "HEADER_FRAGMENT");
    }

    public void onResume(){
        mData.refresh();
        refreshFragments();
    }

    public void newTEN(Class pClass){
        mNewClickHandler.newTEN(pClass);
    }

    public void activateSearch(){
        mFragmentInserter.replaceFragment(mGui.getHeaderId(), mFragmentFactory.createHeaderSearchFragment(), "HEADER_FRAGMENT");
        mGui.hideFooter();
    }

    // Activates the Deletionmode
    public void longClick(){
        mDeleteHandler.longClick();
        mFragmentInserter.replaceFragment(mGui.getHeaderId(), mFragmentFactory.createHeaderDeleteFragment(), "HEADER_FRAGMENT");
        mGui.hideFooter();
    }

    // Goes back to normal State From Deletion
    public void backDelete(){
        mDeleteHandler.back();
        mFragmentInserter.replaceFragment(mGui.getHeaderId(), mFragmentFactory.createHeaderCreateFragment(), "HEADER_FRAGMENT");
        mGui.showFooter();
    }

    // Goes back to normal Sate from Search
    public void backSearch(){
        mData.refresh();
        refreshFragments();
        mFragmentInserter.replaceFragment(mGui.getHeaderId(), mFragmentFactory.createHeaderCreateFragment(), "HEADER_FRAGMENT");
        mGui.showFooter();
    }

    // Deletes selected Items and goes back to normal State
    public void delete(){
        mDeleteHandler.delete();
        mData.refresh();
        refreshFragments();
        mFragmentInserter.replaceFragment(mGui.getHeaderId(), mFragmentFactory.createHeaderCreateFragment(), "HEADER_FRAGMENT");
        mGui.showFooter();
    }

    // Searches for all Items
    public void search(String pSearchString){
        mData.search(pSearchString);
        refreshFragments();
    }

    // Shows TENs depending on selection
    public void show(Class pClass){
        mData.setCurrentClass(pClass);
        mData.refresh();
        refreshFragments();
        mGui.markButton(pClass);
    }

    // Populates the Containers with the existing EventData
    public void refreshFragments(){
        mFragmentInserter.insertFragments(mGui.getContainerIDs(), mFragmentFactory.createTENFragments(mData.getData()));
    }
}