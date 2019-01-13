package com.example.robin.angrynerds_wip.data.services;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.repository.filesystem.FileManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageService {

    public static void saveImage(Image image) throws IOException {
        FileManager fileManager = new FileManager();
        fileManager.saveImageToDirectory(image);
    }

    public Image getImage(Image image) {
        FileManager fileManager = new FileManager();
        Image result = fileManager.readImageFromDirectory(image);
        return result;
    }
}
