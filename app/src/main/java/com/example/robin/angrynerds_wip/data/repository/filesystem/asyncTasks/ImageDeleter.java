package com.example.robin.angrynerds_wip.data.repository.filesystem.asyncTasks;

import android.os.AsyncTask;
import android.os.Environment;

import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.repository.RepositoryConstants;
import com.example.robin.angrynerds_wip.data.repository.filesystem.FileManager;

import java.io.File;

public class ImageDeleter {

    FileManager mFileManager;

    //Class that creates async Task to delete Images from the Filesystem
    //Author: Jan Beilfuß
    public ImageDeleter(FileManager pFileManager) {
        this.mFileManager = pFileManager;
    }

    public void execute(Image image) {
        DeleteImageTask deleteImageTask = new DeleteImageTask();
        deleteImageTask.execute(image);
    }

    private class DeleteImageTask extends AsyncTask<Image, Integer, Void> {

        @Override
        protected Void doInBackground(Image... images) {
            String[] folders = {RepositoryConstants.IMAGE_ORIGINAL_FOLDER, RepositoryConstants.IMAGE_PREVIEW_FOLDER};

            for (String folder : folders) {
                String directoryPath = mFileManager.getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/" + folder + "/";
                String filePath = directoryPath + images[0].getId() + ".jpg";

                File file = new File(filePath);
                if (file.exists()) {
                    file.delete();
                }
            }
            return null;
        }
    }
}
