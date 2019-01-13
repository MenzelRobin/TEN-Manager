package com.example.robin.angrynerds_wip.data.repository.filesystem;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.services.Delete;

import java.io.File;

public class ImageDeleter {

    FileManager fileManager;

    public ImageDeleter(FileManager fileManager) {
        this.fileManager = fileManager;

    }

    public void execute(Image image){
        DeleteImageTask deleteImageTask = new DeleteImageTask();
        deleteImageTask.execute(image);

    }

    private class DeleteImageTask extends AsyncTask<Image, Integer, Boolean> {


        @Override
        protected Boolean doInBackground(Image... images) {
            String directoryPath = fileManager.getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
            String filePath = directoryPath + "/" + images[0].getId() + ".jpg";
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
                return true;
            }
            return false;
        }

    }
}
