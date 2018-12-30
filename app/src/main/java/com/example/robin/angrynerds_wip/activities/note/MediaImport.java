package com.example.robin.angrynerds_wip.activities.note;

import android.content.Intent;
import android.provider.MediaStore;

public class MediaImport {

    private Init mActivity;

    MediaImport(Init mActivity) {
        this.mActivity = mActivity;
    }

    void getImageFromCamera() {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mActivity.startActivityForResult(takePicture, 0);//zero can be replaced with any action code
    }

    void getImageFromGallery(){
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        mActivity.startActivityForResult(pickPhoto , 1);//one can be replaced with any action code
    }




}
