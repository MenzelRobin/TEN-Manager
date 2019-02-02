package com.example.robin.angrynerds_wip.data.repository.filesystem.asyncTasks;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;

import com.example.robin.angrynerds_wip.activities.note.note.data.gui_oriented.PreviewImageCreator;
import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.repository.DataContextManager;
import com.example.robin.angrynerds_wip.data.repository.RepositoryConstants;
import com.example.robin.angrynerds_wip.data.repository.filesystem.FileManager;
import com.example.robin.angrynerds_wip.data.repository.filesystem.FileSystemConstants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageSaver {

    FileManager mFileManager;

    //Class that creates async Task to save Images to the filesystem
    //Author: Jan Beilfuß
    public ImageSaver(FileManager pFileManager) {
        this.mFileManager = pFileManager;
    }

    public void execute(Image image) throws IOException {
        SaveImageTask saveImageTask = new SaveImageTask();
        saveImageTask.execute(image);
    }

    private class SaveImageTask extends AsyncTask<Image, Integer, Void> {

        @Override
        protected Void doInBackground(Image... images) {
            PreviewImageCreator previewImageCreator = new PreviewImageCreator();
            Context context = DataContextManager.context;
            String[] folders = {RepositoryConstants.IMAGE_ORIGINAL_FOLDER, RepositoryConstants.IMAGE_PREVIEW_FOLDER};

            for (String folder : folders) {
                String folderPath = Environment.DIRECTORY_PICTURES + "/" + folder;
                String imageName = images[0].getId() + FileSystemConstants.JPEG;

                File storageDir = context.getExternalFilesDir(folderPath);
                FileOutputStream fos = null;
                Bitmap bitmap = images[0].getBitmap();
                File image = new File(storageDir, imageName);

                if (!image.exists()) {
                    if (bitmap != null) {
                        if (folder.equals(RepositoryConstants.IMAGE_PREVIEW_FOLDER)) {
                            bitmap = previewImageCreator.getPreviewImage(bitmap);
                        }
                        try {
                            fos = new FileOutputStream(image);
                        } catch (FileNotFoundException e) {
                        }

                        bitmap.compress(Bitmap.CompressFormat.JPEG, FileSystemConstants.COMPRESSION_FACTOR, fos);
                        try {
                            fos.flush();
                            fos.close();
                        } catch (IOException e) {
                        }
                    }
                }
            }
            return null;
        }
    }
}
