package com.example.robin.angrynerds_wip.activities.note;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.robin.angrynerds_wip.R;

class ImageContainer implements IContainer{

    private Init mActivity;
    private String path;
    private Bitmap image;
    private LinearLayout imageContainer;
    private int width = 750;
    private int height = 750;

    //TODO this constructor is only for testing purposes
    ImageContainer(Init mActivity, int id, Bitmap image){
        this.mActivity = mActivity;

        if (image.getWidth() >= image.getHeight()){
            image = Bitmap.createBitmap(
                    image,
                    image.getWidth()/2 - image.getHeight()/2,
                    0,
                    image.getHeight(),
                    image.getHeight()
            );

        }else{
            image = Bitmap.createBitmap(
                    image,
                    0,
                    image.getHeight()/2 - image.getWidth()/2,
                    image.getWidth(),
                    image.getWidth()
            );
        }
        this.image=Bitmap.createScaledBitmap(image, 400, 400 ,false);
        this.imageContainer = new LinearLayout(mActivity.getApplicationContext());
        imageContainer.setId(id);
        initiateView();
        mActivity.registerForContextMenu(imageContainer);
    }

    ImageContainer(Init mActivity, int id, String path){
        this.mActivity = mActivity;
        this.path = path;
        this.image = decodeSampledBitmapFromUri(width, height);
        this.imageContainer = new LinearLayout(mActivity.getApplicationContext());
        imageContainer.setId(id);
    }

    public LinearLayout getImageContainer() {
        return imageContainer;
    }
    public String getPath() { return path; }
    public Bitmap getImage() {return image;}

    private void initiateView(){
        imageContainer.setLayoutParams(new LinearLayout.LayoutParams(width + 50, height + 50));
        imageContainer.setGravity(Gravity.CENTER);

        ImageView imageView = new ImageView(mActivity.getApplicationContext());
        imageView.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageBitmap(image);
        imageView.setId(imageContainer.getId()*(-1));
        imageContainer.addView(imageView);
    }

    private Bitmap decodeSampledBitmapFromUri(int reqWidth, int reqHeight) {
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

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
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
