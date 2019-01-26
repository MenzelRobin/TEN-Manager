package com.example.robin.angrynerds_wip.overview.overviewActivity;

import android.support.v4.app.Fragment;

import com.example.robin.angrynerds_wip.data.services.Delete;
import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentInit;

import java.util.ArrayList;
import java.util.List;

public class OverviewDeleteHandler {

    OverviewInit mActivity;

    public OverviewDeleteHandler(OverviewInit pActivity){
        mActivity = pActivity;
    }

    public void longClick(){
        List<Fragment> fragments = mActivity.getSupportFragmentManager().getFragments();
        for(Fragment fragment : fragments){
            if(fragment.getTag() != "HEADER_FRAGMENT") ((OverviewFragmentInit)fragment).getController().setDeleteState(true);
        }
    }

    public void back(){
        List<Fragment> fragments = mActivity.getSupportFragmentManager().getFragments();
        for(Fragment fragment : fragments){
            if(fragment.getTag() != "HEADER_FRAGMENT") ((OverviewFragmentInit)fragment).getController().setDeleteState(false);
        }
    }

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
    }
}
