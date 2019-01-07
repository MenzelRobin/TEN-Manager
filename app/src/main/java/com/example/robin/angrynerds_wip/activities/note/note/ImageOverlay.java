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
    private int frameWidth;
    private int frameHeight;
    private double edgeFactor = 0.95;

    ImageOverlay(Bitmap image) {
        mImage = image;
    }

    void display(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.CustomDialog);
        AlertDialog dialog = builder.create();
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.activity_note_imageoverlay, null);
        ImageView imageView = dialogLayout.findViewById(R.id.id_note_imageContainer);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View displayMetrics = activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT);
        if(calculateSize(displayMetrics.getWidth(), displayMetrics.getHeight()))
            imageView.setImageBitmap(Bitmap.createScaledBitmap(mImage, (int)(frameWidth*edgeFactor), (int)(frameHeight*edgeFactor) ,true));
        else
            imageView.setImageBitmap(mImage);
        dialog.setView(dialogLayout);
        dialog.show();
    }

    private boolean calculateSize(int displayWidth, int displayHeight) {
        int imageWidth = mImage.getWidth();
        int imageHeight = mImage.getHeight();
        double imageAspectRatio = (double) imageWidth / imageHeight;
        double displayAspectRatio = (double) displayWidth / displayHeight;

        if(imageWidth <= displayWidth*edgeFactor && imageHeight <= displayHeight*edgeFactor){
            return false;
        }
        else if (imageWidth == imageHeight) {
            if (displayHeight > displayWidth) {
                frameWidth = displayWidth;
                frameHeight = displayWidth;
            } else {
                frameWidth = displayHeight;
                frameHeight = displayHeight;
            }
        } else if (imageAspectRatio > displayAspectRatio) {
            frameWidth = displayWidth;
            frameHeight = (int) ((double) frameWidth / imageAspectRatio);
        } else {
            frameHeight = displayHeight;
            frameWidth = (int) ((double) frameHeight * imageAspectRatio);
        }
        return true;
    }
}
