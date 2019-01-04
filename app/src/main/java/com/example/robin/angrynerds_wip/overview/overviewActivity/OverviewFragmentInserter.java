package com.example.robin.angrynerds_wip.overview.overviewActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class OverviewFragmentInserter {

    private FragmentManager mFragmentManager;

    public OverviewFragmentInserter(FragmentManager pFragmentManager){
        mFragmentManager = pFragmentManager;
    }

    public void insertFragment(int pContainerID, Fragment pFragment, String pTag) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.add(pContainerID, pFragment, pTag);
        fragmentTransaction.commit();
    }

    //TODO: Create Insert Multiple Fragments method that removes all active Fragments except Headerfragments

    //Todo: Replace a single Fragment
}
