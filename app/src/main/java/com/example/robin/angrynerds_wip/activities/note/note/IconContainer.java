package com.example.robin.angrynerds_wip.activities.note.note;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

class IconContainer extends IContainer {

    private Drawable drawable;

    IconContainer(NoteActivity activity, int id, Drawable drawable){
        super(activity, id);
        this.drawable = drawable;
        initiateView();
    }

    //Initiates Icon in ImageView
    private void initiateView(){
        if(mActivity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            //Portrait Mode
            mImageContainer.setLayoutParams(new LinearLayout.LayoutParams(WIDTH /2, HEIGHT));
        }
        else{
            //Landscape Mode
            mImageContainer.setLayoutParams(new LinearLayout.LayoutParams(WIDTH, HEIGHT /2));
        }
        mImageContainer.setGravity(Gravity.CENTER);

        ImageView imageView = new ImageView(mActivity.getApplicationContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageDrawable(drawable);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(300, 300));
        mImageContainer.addView(imageView);
    }
}
