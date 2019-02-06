package com.example.robin.angrynerds_wip.activities.overview.overviewActivity.fragmentManager;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.robin.angrynerds_wip.data.models.tens.*;
import com.example.robin.angrynerds_wip.activities.overview.eventFragment.OverviewEventInit;
import com.example.robin.angrynerds_wip.activities.overview.header.createFragment.OverviewHeaderCreateFragment;
import com.example.robin.angrynerds_wip.activities.overview.header.deleteFragment.OverviewHeaderDeleteFragment;
import com.example.robin.angrynerds_wip.activities.overview.header.searchFragment.OverviewHeaderSearchFragment;
import com.example.robin.angrynerds_wip.activities.overview.imageFragment.OverviewImageInit;
import com.example.robin.angrynerds_wip.activities.overview.noteFragment.OverviewNoteInit;
import com.example.robin.angrynerds_wip.activities.overview.todoFragment.OverviewTodoInit;

import java.util.ArrayList;

public class OverviewFragmentFactory {
    /* Yannick-Luca Ruettgers
    Creates different Type of Fragments for later use.
     */

    public Fragment createHeaderCreateFragment(){
        return new OverviewHeaderCreateFragment();
    }

    public Fragment createHeaderDeleteFragment() {
        return new OverviewHeaderDeleteFragment();
    }

    public Fragment createHeaderSearchFragment() {
        return new OverviewHeaderSearchFragment();
    }

    // Generates Fragments from a List of TENs
    public ArrayList<Fragment> createTENFragments(ArrayList<TEN> pTENs){
        ArrayList<Fragment> fragments = new ArrayList();
        for(TEN ten : pTENs) {
            if(ten.getClass() == Todo.class){
                OverviewTodoInit fragment = new OverviewTodoInit();
                fragment.setArguments(ten.getBundle());
                fragments.add(fragment);
            }
            if(ten.getClass() == Event.class){
                OverviewEventInit fragment = new OverviewEventInit();
                fragment.setArguments(ten.getBundle());
                fragments.add(fragment);
            }
            if(ten.getClass() == Note.class) {
                if(((Note)ten).getPictures().isEmpty()) {
                    OverviewNoteInit fragment = new OverviewNoteInit();
                    fragment.setArguments(ten.getBundle());
                    fragments.add(fragment);
                } else {
                    OverviewImageInit fragment = new OverviewImageInit();
                    fragment.setArguments(ten.getBundle());
                    fragments.add(fragment);
                }
            }
        }
        return fragments;
    }
}
