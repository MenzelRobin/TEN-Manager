package com.example.robin.angrynerds_wip.activities.note.note.data.backend_oriented;

import android.os.AsyncTask;
import android.util.Log;

import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.services.ImageService;

public class ImageSaver {
    public ImageSaver() {
    }

    public void saveImage(Image pImage) {
        SaveImageTask saveImageTask = new SaveImageTask();
        saveImageTask.execute(pImage);
    }

    private class SaveImageTask extends AsyncTask<Image, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Image... pImages) {
            ImageService.saveImage(pImages[0]);
            return true;
        }
    }
}
