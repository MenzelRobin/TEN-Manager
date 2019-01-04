package com.example.robin.angrynerds_wip.overview.superClasses;

import android.view.View;
import android.widget.ImageView;

public abstract class OverviewFragmentGui {

    protected View mView;
    protected ImageView mChecked;
    protected ImageView mUnchecked;

    public void addView(View pView){
        mView = pView;
    }

    public void hideCheckbox(){
            mChecked.setVisibility(View.INVISIBLE);
            mUnchecked.setVisibility(View.INVISIBLE);
    }

    public void applyMarked(boolean pMarked){
        if(pMarked){
            mChecked.setVisibility(View.VISIBLE);
            mUnchecked.setVisibility(View.INVISIBLE);
        } else {
            mUnchecked.setVisibility(View.VISIBLE);
            mChecked.setVisibility(View.INVISIBLE);
        }
    }
}
