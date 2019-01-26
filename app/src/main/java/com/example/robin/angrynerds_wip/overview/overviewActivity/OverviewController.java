package com.example.robin.angrynerds_wip.overview.overviewActivity;

import android.support.v4.app.Fragment;

import com.example.robin.angrynerds_wip.data.models.tens.TEN;
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
        mGui.markButton(TEN.class);
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

    // Activates the Deletionmode
    public void longClick(){
        mDeleteHandler.longClick();
        mFragmentInserter.replaceFragment(mGui.getHeaderId(), mFragmentFactory.createHeaderDeleteFragment(), "HEADER_FRAGMENT");
        mGui.hideFooter();
    }

    // Goes back to normal State
    public void back(){
        mDeleteHandler.back();
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

    // Shows TENs depending on selection
    public void show(Class pClass){
        mData.setCurrentClass(pClass);
        mData.refresh();
        refreshFragments();
        mGui.markButton(pClass);
    }

    // Populates the Containers with the existing Data
    public void refreshFragments(){
        mFragmentInserter.insertFragments(mGui.getContainerIDs(), mFragmentFactory.createTENFragments(mData.getData()));
    }
}