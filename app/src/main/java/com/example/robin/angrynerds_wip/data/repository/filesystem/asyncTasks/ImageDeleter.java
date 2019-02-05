package com.example.robin.angrynerds_wip.data.repository.filesystem.asyncTasks;

import android.os.AsyncTask;
import android.os.Environment;

import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.repository.filesystem.FileRepository;
import com.example.robin.angrynerds_wip.data.repository.filesystem.FileSystemConstants;

import java.io.File;

public class ImageDeleter {

    FileRepository mFileRepository;

    //Class that creates async Task to delete Images from the Filesystem
    //Author: Jan Beilfuß
    public ImageDeleter(FileRepository pFileRepository) {
        this.mFileRepository = pFileRepository;
    }

    public void execute(Image image) {
        DeleteImageTask deleteImageTask = new DeleteImageTask();
        deleteImageTask.execute(image);
    }

    private class DeleteImageTask extends AsyncTask<Image, Integer, Void> {

        //Deletes Image in preview and original folder
        @Override
        protected Void doInBackground(Image... images) {
            String[] folders = {FileSystemConstants.IMAGE_ORIGINAL_FOLDER, FileSystemConstants.IMAGE_PREVIEW_FOLDER};

            for (String folder : folders) {
                String directoryPath = mFileRepository.getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/" + folder + "/";
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
