package com.example.robin.angrynerds_wip.activities.overview.imageFragment;

import android.graphics.Bitmap;
import android.support.v4.graphics.ColorUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.overview.superClasses.OverviewFragmentGui;

public class OverviewImageGui extends OverviewFragmentGui {
    /* Yannick-Luca Ruettgers
    Manages the GUI for the Imagefragment.
     */

    private RelativeLayout mOverviewImage;
    private TextView mTitle;
    private ImageView mImage;

    // Adds the Fragment view to this Object
    public void addView(View pView){
        super.addView(pView);
        mChecked = mView.findViewById(R.id.id_overview_image_imageView_checked);
        mUnchecked = mView.findViewById(R.id.id_overview_image_imageView_unchecked);
        mOverviewImage = mView.findViewById(R.id.id_overview_image);
        mTitle = mView.findViewById(R.id.id_overview_noteImage_textView_title);
        mImage = mView.findViewById(R.id.id_overview_noteImage_imageView_image);
    }

    // Returns mOverviewImage
    public RelativeLayout getOverviewImage(){
        return mOverviewImage;
    }

    //Sets Title
    public void setTitle(String pTitle){
        mTitle.setText(pTitle);
    }

    // Sets Color
    public void setColor(int pColor){
        mOverviewImage.setBackgroundColor(pColor);

        mTitle.setBackgroundColor(ColorUtils.setAlphaComponent(pColor, 150));
    }

    // Sets Image
    public void setImage(Bitmap pImage){
        mImage.setImageBitmap(pImage);
    }
}
