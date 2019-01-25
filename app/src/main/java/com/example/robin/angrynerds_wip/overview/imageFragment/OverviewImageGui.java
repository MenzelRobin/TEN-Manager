package com.example.robin.angrynerds_wip.overview.imageFragment;

import android.view.View;
import android.widget.RelativeLayout;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentGui;

public class OverviewImageGui extends OverviewFragmentGui {

    private RelativeLayout mOverviewImage;
    //private TextView mTitle;
    //private TextView mDescription;

    // Adds the Fragment view to this Object
    public void addView(View pView){
        super.addView(pView);
        mChecked = mView.findViewById(R.id.id_overview_note_imageView_checked);
        mUnchecked = mView.findViewById(R.id.id_overview_note_imageView_unchecked);
        mOverviewImage = mView.findViewById(R.id.id_overview_image);
        //mTitle = mView.findViewById(R.id.id_overview_note_textView_title);
        //mDescription = mView.findViewById(R.id.id_overview_note_textView_description);
    }

    // Returns mOverviewImage
    public RelativeLayout getOverviewImage(){
        return mOverviewImage;
    }

    // Sets Title
    //public void setTitle(String pTitle){
    //    mTitle.setText(pTitle);
    //}

    // Sets Color
    public void setColor(int pColor){
        mOverviewImage.setBackgroundColor(pColor);
    }

    // Sets Description
    //public void setDescription(String pDescription){
    //    mDescription.setText(pDescription);
    //}
}
