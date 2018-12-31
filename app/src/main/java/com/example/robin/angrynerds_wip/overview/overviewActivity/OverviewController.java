package com.example.robin.angrynerds_wip.overview.overviewActivity;

import android.support.v4.app.Fragment;

import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;
import com.example.robin.angrynerds_wip.overview.noteFragment.OverviewNoteInit;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class OverviewController {

    private OverviewData mData;
    private OverviewGui mGui;

    OverviewController(OverviewData pData, OverviewGui pGui) {
        mData = pData;
        mGui = pGui;
        mData.filterNotes();
        mGui.insertFragments(generateFragments());
    }

    // Generates an ArrayList containing Fragments
    public ArrayList<Fragment> generateFragments(){
        ArrayList<Fragment> fragments = new ArrayList();
        ArrayList<TEN> TENs = mData.getData();
        for(TEN ten : TENs) {
            if(ten.getClass() == Todo.class){
                //Todo: Implement
            }
            if(ten.getClass() == Event.class){
                //Todo: Implement
            }
            if(ten.getClass() == Note.class) {
                OverviewNoteInit fragment = new OverviewNoteInit();
                fragment.addData((Note) ten);
                fragments.add(new OverviewNoteInit());
            }
        }
        return fragments;
    }
}
