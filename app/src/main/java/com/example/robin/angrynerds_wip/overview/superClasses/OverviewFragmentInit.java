package com.example.robin.angrynerds_wip.overview.superClasses;

import android.support.v4.app.Fragment;

import com.example.robin.angrynerds_wip.overview.noteFragment.OverviewNoteController;
import com.example.robin.angrynerds_wip.overview.noteFragment.OverviewNoteData;
import com.example.robin.angrynerds_wip.overview.noteFragment.OverviewNoteGui;

public abstract class OverviewFragmentInit extends Fragment {

    protected OverviewFragmentData mData;
    protected OverviewFragmentGui mGui;
    protected OverviewFragmentController mController;
}
