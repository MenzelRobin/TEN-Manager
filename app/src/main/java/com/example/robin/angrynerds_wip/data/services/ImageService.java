package com.example.robin.angrynerds_wip.data.services;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Environment;
import android.util.Log;

import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.repository.RepositoryConstants;
import com.example.robin.angrynerds_wip.data.repository.filesystem.FileManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ImageService {

    public static void saveImage(Image image) {
        try {
            FileManager fileManager = new FileManager();
            fileManager.saveImageToDirectory(image);
        } catch (IOException e) {
            Log.e("ImageService", e.getMessage());
        }
    }

    public static Image getImage(Image image) {
        FileManager fileManager = new FileManager();
        Image result = fileManager.readImageFromDirectory(image, RepositoryConstants.IMAGE_ORIGINAL_FOLDER);
        return result;
    }

    public static Image getPreviewImage(Image image) {
        FileManager fileManager = new FileManager();
        Image result = fileManager.readImageFromDirectory(image, RepositoryConstants.IMAGE_PREVIEW_FOLDER);
        return result;
    }

    public static void deleteImage(Image image) {
        FileManager fileManager = new FileManager();
        fileManager.deleteImageFromDirectory(image);
    }

    public static void deleteImage(String path) {
        FileManager fileManager = new FileManager();
        fileManager.deleteImageFromDirectory(path);
    }

    public static File createImageFile(Activity pActivity) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.GERMANY).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = pActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }

    public static Bitmap correctImageRotation(String pPhotoPath, Bitmap pImage){
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
            Log.d("Kamera", "orientation: " + orientation);
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

    private static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }
}
