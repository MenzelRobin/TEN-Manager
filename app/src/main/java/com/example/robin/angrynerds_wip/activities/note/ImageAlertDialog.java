package com.example.robin.angrynerds_wip.activities.note;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.example.robin.angrynerds_wip.R;

class ImageAlertDialog {

    private Bitmap mImage;

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
        dialog.show();
    }
}
