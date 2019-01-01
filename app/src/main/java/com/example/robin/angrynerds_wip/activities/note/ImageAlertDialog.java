package com.example.robin.angrynerds_wip.activities.note;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.util.Log;

import com.example.robin.angrynerds_wip.R;

class ImageAlertDialog {

    private Bitmap mImage;
    private int mWidth;
    private int mHeight;

    ImageAlertDialog(Bitmap image){
        mImage = image;
    }

    void display(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        AlertDialog dialog = builder.create();
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.activity_note_image_overlay, null);
        dialog.setView(dialogLayout);
        ImageView imageView = dialogLayout.findViewById(R.id.id_note_imageContainer);
        imageView.setImageBitmap(mImage);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        calculateSize(activity.getWindowManager().getDefaultDisplay());
        dialog.show();
        try {
            dialog.getWindow().setLayout((int) (mWidth), (int) (mHeight));
        }catch(Exception e){
            Log.e("Exception", e.getMessage());
        }
    }

    private void calculateSize(Display display){
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        int imageWidth = mImage.getWidth();
        int imageHeight = mImage.getHeight();
        double aspectRatio = (double)imageWidth / imageHeight;

        /*if(imageWidth==imageHeight){
            if(height>width){
                mWidth = width;
                mHeight = width;
            }
            else{
                mWidth = height;
                mHeight = height;
            }
        }*/
        if((imageWidth/width)>=(imageHeight/height)){
            //if frame needs to be on full width
            mWidth = width;
            mHeight = (int)(width / aspectRatio);
            //mHeight = width * imageHeight / imageWidth;
        }
        else{
            //TODO does not work
            //if frame need to be on full height
            mHeight = height;
            mWidth = (int)(height * aspectRatio);
        }
    }
}
