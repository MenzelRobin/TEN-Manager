package com.example.robin.angrynerds_wip.activities.note.note;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

class IconContainer extends IContainer {

    private Init mActivity;
    private Drawable drawable;
    private LinearLayout imageContainer;

    IconContainer(Init mActivity, int id, Drawable drawable){
        this.mActivity = mActivity;
        this.imageContainer = new LinearLayout(mActivity.getApplicationContext());
        imageContainer.setId(id);
        this.drawable = drawable;
        initiateView();
    }

    public LinearLayout getImageContainer() {
        return imageContainer;
    }
    public Bitmap getImage(){
        return ((BitmapDrawable)drawable).getBitmap();}
    public void setImageContainerId(int id){
        imageContainer.setId(id);
    }

    private void initiateView(){
        imageContainer.setLayoutParams(new LinearLayout.LayoutParams(400, 800));
        imageContainer.setGravity(Gravity.CENTER);

        ImageView imageView = new ImageView(mActivity.getApplicationContext());
        imageView.setLayoutParams(new LinearLayout.LayoutParams(300, 300));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageDrawable(drawable);

        imageContainer.addView(imageView);
    }
}
