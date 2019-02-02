package com.example.robin.angrynerds_wip.data.repository.filesystem;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.repository.DataContextManager;
import com.example.robin.angrynerds_wip.data.repository.filesystem.asyncTasks.ImageDeleter;
import com.example.robin.angrynerds_wip.data.repository.filesystem.asyncTasks.ImageSaver;

import java.io.File;
import java.io.IOException;

//Class that handles everything regarding persistent Images
//Author: Jan Beilfuß
public class FileManager {
    public Context getContext() {
        return DataContextManager.context;
    }

    public void saveImagePersistent(Image pImage) throws IOException {
        ImageSaver imageSaver = new ImageSaver(this);
        imageSaver.execute(pImage);
    }

    public Image readImageFromDirectory(Image image, String directory) {
        Context context = DataContextManager.context;
        String directoryPath = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/" + directory + "/";
        String filePath = directoryPath + image.getId() + FileSystemConstants.JPEG;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        Image result = new Image(image.getId(), bitmap);
        return result;
    }

    public void deleteImageFromDirectory(String path) {
        File file = new File(path);
        if(file.exists()) file.delete();
    }

    public void deleteImageFromDirectory(Image image) {
        ImageDeleter imageDeleter = new ImageDeleter(this);
        imageDeleter.execute(image);
    }
}
