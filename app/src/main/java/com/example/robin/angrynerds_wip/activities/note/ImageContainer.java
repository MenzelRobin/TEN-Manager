package com.example.robin.angrynerds_wip.activities.note;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.robin.angrynerds_wip.R;

public class ImageContainer {

    private Init mActivity;
    private Bitmap image;
    private LinearLayout imageContainer;

    //TODO this constructor is only for testing purposes
    public ImageContainer(Init mActivity, int id){
        this.mActivity = mActivity;
        this.image = BitmapFactory.decodeResource(mActivity.getResources(), R.drawable.note_sample_image1);
        this.imageContainer = new LinearLayout(mActivity.getApplicationContext());
        imageContainer.setId(id);
        initiateView();
    }

    public ImageContainer(Init mActivity, int id, String path){
        this.mActivity = mActivity;
        this.image = decodeSampledBitmapFromUri(path, 400, 400);
        this.imageContainer = new LinearLayout(mActivity.getApplicationContext());
        imageContainer.setId(id);
    }

    public LinearLayout getPicture() {
        return imageContainer;
    }

    private void initiateView(){
        imageContainer.setLayoutParams(new LinearLayout.LayoutParams(450, 450));
        imageContainer.setGravity(Gravity.CENTER);

        ImageView imageView = new ImageView(mActivity.getApplicationContext());
        imageView.setLayoutParams(new LinearLayout.LayoutParams(400, 400));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageBitmap(image);

        imageContainer.addView(imageView);
    }

    private Bitmap decodeSampledBitmapFromUri(String path, int reqWidth, int reqHeight) {
        Bitmap bitmap;

        //First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        //Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        //Decode bitmap with inSampleSize
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeFile(path, options);

        return bitmap;
    }

    private int calculateInSampleSize(

        BitmapFactory.Options options, int reqWidth, int reqHeight) {
        //height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float)height / (float)reqHeight);
            } else {
                inSampleSize = Math.round((float)width / (float)reqWidth);
            }
        }
        return inSampleSize;
    }
}
