package com.example.robin.angrynerds_wip.activities.note;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;

import com.example.robin.angrynerds_wip.R;

class MediaImport {

    private Init mActivity;

    MediaImport(Init activity) {
        this.mActivity = activity;
        requestImageSource();
    }

    private void requestImageSource(){
        DialogClickListener clickListener = new DialogClickListener(this);
        String[] options = {"Camera", "Gallery"};
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("Choose Image Source");
        builder.setIcon(R.drawable.ic_add_a_photo_darkgrey_24dp);
        builder.setItems(options, clickListener);
        builder.show();
    }

    void importImageFromCamera() {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mActivity.startActivityForResult(takePicture, 0);
    }

    void importImageFromGallery(){
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        mActivity.startActivityForResult(pickPhoto , 1);
    }
}
