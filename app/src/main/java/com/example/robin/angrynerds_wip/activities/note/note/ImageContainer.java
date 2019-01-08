package com.example.robin.angrynerds_wip.activities.note.note;

import android.graphics.Bitmap;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

class ImageContainer implements IContainer{

    private NoteActivity mActivity;
    private String path;
    private Bitmap image;
    private LinearLayout imageContainer;
    private int width = 750;
    private int height = 750;

    ImageContainer(NoteActivity mActivity, int id, Bitmap originalImage){
        this.mActivity = mActivity;
        this.image = originalImage;
        Bitmap scaledImage;

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
        scaledImage=Bitmap.createScaledBitmap(originalImage, width, height ,true);
        this.imageContainer = new LinearLayout(mActivity.getApplicationContext());
        imageContainer.setId(id);
        initiateView(scaledImage);
        mActivity.registerForContextMenu(imageContainer);
    }

    public LinearLayout getImageContainer() {
        return imageContainer;
    }
    public String getPath() { return path; }
    public Bitmap getImage() {return image;}
    public void setImageContainerId(int id){ imageContainer.setId(id);
    }

    private void initiateView(Bitmap scaledImage){
        imageContainer.setLayoutParams(new LinearLayout.LayoutParams(width + 50, height + 50));
        imageContainer.setGravity(Gravity.CENTER);

        ImageView imageView = new ImageView(mActivity.getApplicationContext());
        imageView.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageBitmap(scaledImage);
        imageContainer.addView(imageView);
    }
    /*
    ImageContainer(NoteActivity mActivity, int id, String path){
        this.mActivity = mActivity;
        this.path = path;
        this.image = decodeSampledBitmapFromUri(width, height);
        this.imageContainer = new LinearLayout(mActivity.getApplicationContext());
        imageContainer.setId(id);
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
    */
}
