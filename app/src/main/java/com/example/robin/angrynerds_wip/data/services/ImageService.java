package com.example.robin.angrynerds_wip.data.services;

import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.repository.RepositoryConstants;
import com.example.robin.angrynerds_wip.data.repository.filesystem.FileManager;

import java.io.IOException;

public class ImageService {

    public static void saveImage(Image image, String folder)  {
    }

    public static Image getImage(Image image) {
        FileManager fileManager = new FileManager();
        Image result = fileManager.readImageFromDirectory(image, RepositoryConstants.IMAGE_ORIGINAL_FOLDER);
        return result;
    }

    public static Image getPreviewImage(Image image){
        FileManager fileManager = new FileManager();
        Image result = fileManager.readImageFromDirectory(image, RepositoryConstants.IMAGE_PREVIEW_FOLDER);
        return result;
    }

    public static void deleteImage(Image image){
        FileManager fileManager = new FileManager();
        fileManager.deleteImageFromDirectory(image);
    }
    public static void deleteImage(String path){
        FileManager fileManager = new FileManager();
        fileManager.deleteImageFromDirectory(path);
    }

    public static void savePreviewImage(Image image) throws IOException {
        FileManager fileManager = new FileManager();
        fileManager.saveImageToDirectory(image);
    }
}
