package com.example.robin.angrynerds_wip.activities.note.note.data;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.note.note.NoteActivity;
import com.example.robin.angrynerds_wip.activities.note.note.logic.event_handler.DialogClickListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ImageImport {

    private NoteActivity mActivity;
    private String mCurrentPhotoPath;

    public ImageImport(NoteActivity activity) {
        this.mActivity = activity;
        requestImageSource();
    }

    //Returns Path of Image
    public String getCurrentPhotoPath() {
        return mCurrentPhotoPath;
    }

    //Shows AlertDialog to request image source
    private void requestImageSource(){
        DialogClickListener clickListener = new DialogClickListener(this);
        String[] options;

        //Checks if device has a camera
        if (mActivity.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            options = new String[]{"Galerie", "Kamera"};
        }
        else{
            options = new String[]{"Galerie"};
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("Bildquelle auswählen");
        builder.setIcon(R.drawable.ic_add_a_photo_darkgrey_24dp);
        builder.setItems(options, clickListener);
        builder.show();
    }

    //Starts Camera activity
    public void importImageFromCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(mActivity.getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                Log.e("Error", e.getMessage());
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(mActivity,
                        "com.example.robin.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                mActivity.startActivityForResult(takePictureIntent, 0);
            }
        }
    }

    //Starts Gallery activity to pick an image
    public void importImageFromGallery(){
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        mActivity.startActivityForResult(pickPhoto , 1);
    }

    //Creates the image file to pass into the camera activity
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.GERMANY).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = mActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
}
