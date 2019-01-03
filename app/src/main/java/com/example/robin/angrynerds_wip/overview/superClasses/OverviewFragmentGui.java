package com.example.robin.angrynerds_wip.overview.superClasses;

import android.view.View;

public abstract class OverviewFragmentGui {

    protected View mView;

    public void addView(View pView){
        mView = pView;
    }
}
