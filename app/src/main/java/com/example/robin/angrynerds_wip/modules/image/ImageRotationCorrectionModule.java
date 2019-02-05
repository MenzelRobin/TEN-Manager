package com.example.robin.angrynerds_wip.modules.image;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.Log;

import java.io.IOException;

public class ImageRotationCorrectionModule {

    public Bitmap correctImageRotation(String pPhotoPath, Bitmap pImage){
        ExifInterface mExifInterface = null;

        try {
            mExifInterface = new ExifInterface(pPhotoPath);
        }
        catch(IOException e){
            Log.e("Error", e.getMessage());
        }

        if(mExifInterface != null){
            Bitmap rotatedBitmap;
            int orientation = mExifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_UNDEFINED);
            switch(orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotatedBitmap = rotateImage(pImage, 90);
                    break;

                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotatedBitmap = rotateImage(pImage, 180);
                    break;

                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotatedBitmap = rotateImage(pImage, 270);
                    break;

                case ExifInterface.ORIENTATION_NORMAL:
                default:
                    rotatedBitmap = pImage;
            }
            return rotatedBitmap;
        }
        else
            return pImage;
    }

    private Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }
}
