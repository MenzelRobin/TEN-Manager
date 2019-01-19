package com.example.robin.angrynerds_wip.activities.note.note.data;

import android.os.AsyncTask;
import android.util.Log;

import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.services.ImageService;

public class ImageSaver {

    ImageSaver() {

    }

    public void saveImage(Image image) {
        SaveImageTask saveImageTask = new SaveImageTask();
        saveImageTask.execute(image);
    }

    private class SaveImageTask extends AsyncTask<Image, Integer, Boolean> {


        @Override
        protected Boolean doInBackground(Image... images) {
            ImageService.saveImage(images[0]);
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
        }

        @Override
        protected void onPostExecute(Boolean bool) {
            Log.i("ImageSaver", "Image saved saved");
        }
    }
}
