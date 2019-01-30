package com.example.robin.angrynerds_wip.activities.note.note.data.backend_oriented;

import android.os.AsyncTask;

import com.example.robin.angrynerds_wip.activities.note.note.data.NoteData;
import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.services.ImageService;

public class ImageSaver {

    NoteData mNoteData;

    public ImageSaver(NoteDataBackend pNoteDataBackend) {
        this.mNoteData = pNoteDataBackend.getmNoteData();
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

        @Override
        protected void onPostExecute(Boolean boole) {
            mNoteData.resetNoteBitmaps();
        }

    }
}
