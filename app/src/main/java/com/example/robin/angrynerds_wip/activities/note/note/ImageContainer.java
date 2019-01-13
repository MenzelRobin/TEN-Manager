package com.example.robin.angrynerds_wip.activities.note.note;

import android.graphics.Bitmap;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

class ImageContainer extends IContainer{

    private ImageView mImageView;

    ImageContainer(NoteActivity activity, int id, Bitmap originalImage){
        super(activity, id);

        Bitmap scaledImage;

        //Crop image to square
        if (originalImage.getWidth() >= originalImage.getHeight()){
            originalImage = Bitmap.createBitmap(
                    originalImage,
                    originalImage.getWidth()/2 - originalImage.getHeight()/2,
                    0,
                    originalImage.getHeight(),
                    originalImage.getHeight()
            );

        }else{
            originalImage = Bitmap.createBitmap(
                    originalImage,
                    0,
                    originalImage.getHeight()/2 - originalImage.getWidth()/2,
                    originalImage.getWidth(),
                    originalImage.getWidth()
            );
        }

        //scale image to preset
        scaledImage=Bitmap.createScaledBitmap(originalImage, WIDTH - 50, HEIGHT - 50 ,true);

        initiateView(scaledImage);
        activity.registerForContextMenu(mImageContainer);
    }

    //Initiates Image in ImageView
    private void initiateView(Bitmap scaledImage){
        mImageContainer.setLayoutParams(new LinearLayout.LayoutParams(WIDTH, HEIGHT));
        mImageContainer.setGravity(Gravity.CENTER);

        ImageView imageView = new ImageView(mActivity.getApplicationContext());
        imageView.setLayoutParams(new LinearLayout.LayoutParams(WIDTH - 50, HEIGHT - 50));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mImageView = imageView;
        imageView.setImageBitmap(scaledImage);

        mImageContainer.addView(imageView);
    }

    public ImageView getImageView() { return this.mImageView; }
}
