package com.example.robin.angrynerds_wip.activities.note.note;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.example.robin.angrynerds_wip.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

class ImageImport {

    private NoteActivity mActivity;
    private String mCurrentPhotoPath;

    ImageImport(NoteActivity activity) {
        this.mActivity = activity;
        requestImageSource();
    }

    public String getmCurrentPhotoPath() {
        return mCurrentPhotoPath;
    }

    private void requestImageSource(){
        DialogClickListener clickListener = new DialogClickListener(this);
        String[] options;

        if (mActivity.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            options = new String[]{"Gallery", "Camera"};
        }
        else{
            options = new String[]{"Gallery"};
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("Choose Image Source");
        builder.setIcon(R.drawable.ic_add_a_photo_darkgrey_24dp);
        builder.setItems(options, clickListener);
        builder.show();
    }

    void importImageFromCamera() {
        dispatchTakePictureIntent();
    }

    void importImageFromGallery(){
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        mActivity.startActivityForResult(pickPhoto , 1);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(mActivity.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                Log.e("Error", e.getMessage());
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(mActivity,
                        "com.example.robin.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                mActivity.startActivityForResult(takePictureIntent, 0);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.GERMANY).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = mActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
}
