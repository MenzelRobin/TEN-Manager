package com.example.robin.angrynerds_wip.activities.note.note;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.util.Log;

import com.example.robin.angrynerds_wip.R;

class ImageOverlay {

    private Bitmap mImage;
    private int frameWidth;
    private int frameHeight;

    ImageOverlay(Bitmap image) {
        mImage = image;
    }

    void display(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        AlertDialog dialog = builder.create();
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.activity_note_imageoverlay, null);
        dialog.setView(dialogLayout);
        ImageView imageView = dialogLayout.findViewById(R.id.id_note_imageContainer);
        imageView.setImageBitmap(mImage);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        View displayMetrics = activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT);
        calculateSize(displayMetrics.getWidth(), displayMetrics.getHeight());
        //imageView.setImageBitmap(Bitmap.createScaledBitmap(mImage, frameWidth, frameHeight ,true));
        dialog.show();
        try {
            dialog.getWindow().setLayout((int)(frameWidth - 1), (int)(frameHeight - 1));
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
        }
    }

    private void calculateSize(int displayWidth, int displayHeight) {
        int imageWidth = mImage.getWidth();
        int imageHeight = mImage.getHeight();
        double imageAspectRatio = (double) imageWidth / imageHeight;
        double displayAspectRatio = (double) displayWidth / displayHeight;

        if(imageWidth <= displayWidth && imageHeight <= displayHeight){
            frameWidth = imageWidth;
            frameHeight = imageHeight;
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
            frameHeight = (int)(frameHeight * 1.075);
        } else {
            frameHeight = displayHeight;
            frameWidth = (int) ((double) frameHeight * imageAspectRatio);
            frameWidth = (int)(frameWidth * 1.05);
        }
        //Jans Teil
        Log.i("AspectRatio", "Bildbreite" + imageWidth);
        Log.i("AspectRatio", "Bildhöhe" + imageHeight);
        Log.i("AspectRatio", "Displaybreite" + displayWidth);
        Log.i("AspectRatio", "Displayhöhe" + displayHeight);
        Log.i("AspectRatio", "Framehöhe" + frameHeight);
        Log.i("AspectRatio", "Framebreite" + frameWidth);
        Log.i("AspectRatio", "Debuggerzeile");


        //Ende Jan, denn Jan hat kein Bock auf das Notepad
        /*if ((imageWidth / displayWidth) >= (imageHeight / displayHeight)) {
            //if frame needs to be on full width
            frameWidth = displayWidth;
            frameHeight = (int) (displayWidth / imageAspectRatio);
            // vorherige Logik: frameHeight = width * imageHeight / imageWidth;
        } else {
            //if frame need to be on full height
            frameHeight = displayHeight;
            frameWidth = (int) (displayHeight * imageAspectRatio);
        }*/
    }
}
