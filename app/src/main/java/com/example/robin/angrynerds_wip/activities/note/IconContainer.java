package com.example.robin.angrynerds_wip.activities.note;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.robin.angrynerds_wip.R;

public class IconContainer implements IContainer {

    private Init mActivity;
    private Drawable drawable;
    private LinearLayout imageContainer;

    public IconContainer(Init mActivity, int id, Drawable drawable){
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
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        return bitmap;}

    private void initiateView(){
        imageContainer.setLayoutParams(new LinearLayout.LayoutParams(450, 450));
        imageContainer.setGravity(Gravity.CENTER);

        ImageView imageView = new ImageView(mActivity.getApplicationContext());
        imageView.setLayoutParams(new LinearLayout.LayoutParams(400, 400));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageDrawable(drawable);

        imageContainer.addView(imageView);
    }
}
