package com.example.robin.angrynerds_wip.activities.note.note;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.example.robin.angrynerds_wip.R;

class ImageOverlay {

    private Bitmap mImage;
    private AlertDialog dialog;
    private int displayWidth;
    private int displayHeight;
    private int frameWidth;
    private int frameHeight;
    private double edgeFactor = 0.95;

    ImageOverlay(Bitmap image, int displayWidth, int displayHeight) {
        mImage = image;
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
    }

    //Displays the image in an AlertDialog
    void display(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.CustomDialog);
        dialog = builder.create();
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.activity_note_imageoverlay, null);
        ImageView imageView = dialogLayout.findViewById(R.id.id_note_imageContainer);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if(calculateSize())
            imageView.setImageBitmap(Bitmap.createScaledBitmap(mImage, (int)(frameWidth*edgeFactor), (int)(frameHeight*edgeFactor) ,true));
        else
            imageView.setImageBitmap(mImage);
        dialog.setView(dialogLayout);
        dialog.show();
    }

    //Calculates size of frame according to display metrics
    private boolean calculateSize() {
        int imageWidth = mImage.getWidth();
        int imageHeight = mImage.getHeight();
        double imageAspectRatio = (double) imageWidth / imageHeight;
        double displayAspectRatio = (double) displayWidth / displayHeight;

        //Checks if image is smaller than display * edgeFactor
        if(imageWidth <= displayWidth*edgeFactor && imageHeight <= displayHeight*edgeFactor){
            return false;
        }
        //Checks if image is a square
        else if (imageWidth == imageHeight) {
            if (displayHeight > displayWidth) {
                frameWidth = displayWidth;
                frameHeight = displayWidth;
            } else {
                frameWidth = displayHeight;
                frameHeight = displayHeight;
            }
        }
        //Checks if image exceeds display in WIDTH
        else if (imageAspectRatio > displayAspectRatio) {
            frameWidth = displayWidth;
            frameHeight = (int) ((double) frameWidth / imageAspectRatio);
        }
        //Checks if image exceeds display in HEIGHT
        else {
            frameHeight = displayHeight;
            frameWidth = (int) ((double) frameHeight * imageAspectRatio);
        }
        return true;
    }

    //returns whether AlertDialog is displayed or not
    boolean  isDisplayed() {
        return dialog.isShowing();
    }

    //Rescales AlertDialog on Orientation change
    void changeOrientation(Activity activity) {
        dialog.dismiss();
        int saveValue = displayWidth;
        displayWidth = displayHeight;
        displayHeight = saveValue;
        display(activity);
    }
}
