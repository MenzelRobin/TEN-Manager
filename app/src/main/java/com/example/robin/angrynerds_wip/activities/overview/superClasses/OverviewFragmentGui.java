package com.example.robin.angrynerds_wip.activities.overview.superClasses;

import android.view.View;
import android.widget.ImageView;

public abstract class OverviewFragmentGui {
    /* Yannick-Luca Ruettgers
    Superclass for [Fragment]Gui Classes. Applies markedstate to checkbox during deletion process.
    */

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
