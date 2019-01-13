package com.example.robin.angrynerds_wip.data.services;

import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.repository.filesystem.FileManager;

import java.io.IOException;

public class ImageService {

    public static void saveImage(Image image) throws IOException {
        FileManager fileManager = new FileManager();
        fileManager.saveImageToDirectory(image);
    }

    public static Image getImage(Image image) {
        FileManager fileManager = new FileManager();
        Image result = fileManager.readImageFromDirectory(image);
        return result;
    }

    public static void deleteImage(String imageID){
        FileManager fileManager = new FileManager();
        fileManager.deleteImageFromDirectory(imageID);
    }
}
