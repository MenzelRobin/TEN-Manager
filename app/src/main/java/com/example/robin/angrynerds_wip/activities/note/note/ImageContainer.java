package com.example.robin.angrynerds_wip.activities.note.note;

import android.graphics.Bitmap;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

class ImageContainer extends IContainer {


    ImageContainer(NoteActivity activity, int id, Bitmap originalImage) {
        super(activity, id);
        private NoteActivity mActivity;
        private String path; //TODO check usage, delete if not needed
        private Bitmap image;
        private LinearLayout imageContainer;
        private ImageView imageView;
        private int width = 750;
        private int height = 750;

        Bitmap scaledImage;

        //Crop image to square
        if (originalImage.getWidth() >= originalImage.getHeight()) {
            originalImage = Bitmap.createBitmap(
                    originalImage,
                    originalImage.getWidth() / 2 - originalImage.getHeight() / 2,
                    0,
                    originalImage.getHeight(),
                    originalImage.getHeight()
            );

        } else {
            originalImage = Bitmap.createBitmap(
                    originalImage,
                    0,
                    originalImage.getHeight() / 2 - originalImage.getWidth() / 2,
                    originalImage.getWidth(),
                    originalImage.getWidth()
            );
        }

        //scale image to preset
        scaledImage=Bitmap.createScaledBitmap(originalImage, width - 50, height - 50 ,true);

        initiateView(scaledImage);
        mActivity.registerForContextMenu(imageContainer);
    }

    public LinearLayout getImageContainer() {
        return imageContainer;
    }

    public String getPath() {
        return path;
    }

    public Bitmap getImage() {
        return image;
    }

    public ImageView getImageView() {
        return this.imageView;
    }

    public void setImageContainerId(int id) {
        imageContainer.setId(id);
        activity.registerForContextMenu(mImageContainer);
    }

    //Initiates Image in ImageView
    private void initiateView(Bitmap scaledImage) {
        mImageContainer.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        mImageContainer.setGravity(Gravity.CENTER);

        ImageView imageView = new ImageView(mActivity.getApplicationContext());
        imageView.setLayoutParams(new LinearLayout.LayoutParams(width - 50, height - 50));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageBitmap(scaledImage);
        mImageContainer.addView(imageView);
        this.imageView = imageView;
        mImageContainer.addView(imageView);
    }
}
