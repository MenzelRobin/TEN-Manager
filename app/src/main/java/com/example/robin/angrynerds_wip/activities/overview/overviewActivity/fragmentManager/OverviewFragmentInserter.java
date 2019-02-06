package com.example.robin.angrynerds_wip.activities.overview.overviewActivity.fragmentManager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

public class OverviewFragmentInserter {
    /* Yannick-Luca Ruettgers
    Inserts Fragments into the GUI using a FragmentManager
     */

    private FragmentManager mFragmentManager;

    public OverviewFragmentInserter(FragmentManager pFragmentManager){
        mFragmentManager = pFragmentManager;
    }

    // Inserts a Fragment into a container
    public void insertFragment(int pContainerID, Fragment pFragment, String pTag) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.add(pContainerID, pFragment, pTag);
        fragmentTransaction.commit();
    }

    // Replaces a Fragment in a container
    public void replaceFragment(int pContainerID, Fragment pFragment, String pTag) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(pContainerID, pFragment, pTag);
        fragmentTransaction.commit();
    }

    // Inserts multiple Fragments into multiple Containers while cycling though containers
    public void insertFragments(int[] pContainerIDs, ArrayList<Fragment> pFragments){
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        for(Fragment fragment : mFragmentManager.getFragments()){
            if(fragment.getTag() != "HEADER_FRAGMENT") fragmentTransaction.remove(fragment);
        }
        int counter = 0;
        for(Fragment fragment : pFragments) {
            fragmentTransaction.add(pContainerIDs[counter%pContainerIDs.length], fragment);
            counter++;
        }
        fragmentTransaction.commit();
    }
}
