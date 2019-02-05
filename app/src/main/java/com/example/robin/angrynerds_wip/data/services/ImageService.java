package com.example.robin.angrynerds_wip.data.services;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;

import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.repository.filesystem.FileRepository;
import com.example.robin.angrynerds_wip.data.repository.filesystem.FileSystemConstants;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ImageService {

    public static void saveImage(Image image) {
        try {
            FileRepository fileRepository = new FileRepository();
            fileRepository.saveImagePersistent(image);
        } catch (IOException e) {
            Log.e("ImageService", e.getMessage());
        }
    }

    public static Image getImage(Image image) {
        FileRepository fileRepository = new FileRepository();
        Image result = fileRepository.readImageFromDirectory(image, FileSystemConstants.IMAGE_ORIGINAL_FOLDER);
        return result;
    }

    public static Image getPreviewImage(Image image) {
        FileRepository fileRepository = new FileRepository();
        Image result = fileRepository.readImageFromDirectory(image, FileSystemConstants.IMAGE_PREVIEW_FOLDER);
        return result;
    }

    public static void deleteImage(Image image) {
        FileRepository fileRepository = new FileRepository();
        fileRepository.deleteImageFromDirectories(image);
    }

    public static void deleteImage(String path) {
        FileRepository fileRepository = new FileRepository();
        fileRepository.deleteImageFromDirectories(path);
    }

    public static File createImageFile(Activity pActivity) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.GERMANY).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = pActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }
}
