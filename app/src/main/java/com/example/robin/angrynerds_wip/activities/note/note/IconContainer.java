package com.example.robin.angrynerds_wip.activities.note.note;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

class IconContainer implements IContainer {

    private NoteActivity mActivity;
    private Drawable drawable;
    private LinearLayout imageContainer;

    IconContainer(NoteActivity mActivity, int id, Drawable drawable){
        this.mActivity = mActivity;
        this.imageContainer = new LinearLayout(mActivity.getApplicationContext());
        imageContainer.setId(id);
        this.drawable = drawable;
        initiateView();
    }

    public LinearLayout getImageContainer() {
        return imageContainer;
    }
    public Bitmap getImage(){ return ((BitmapDrawable)drawable).getBitmap();}
    public void setImageContainerId(int id){
        imageContainer.setId(id);
    }

    //Initiates Icon in ImageView
    private void initiateView(){
        if(mActivity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            //Portrait Mode
            imageContainer.setLayoutParams(new LinearLayout.LayoutParams(400, 800));
        }
        else{
            //Landscape Mode
            imageContainer.setLayoutParams(new LinearLayout.LayoutParams(800, 400));
        }
        imageContainer.setGravity(Gravity.CENTER);

        ImageView imageView = new ImageView(mActivity.getApplicationContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageDrawable(drawable);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(300, 300));
        imageContainer.addView(imageView);
    }
}
