package com.example.robin.angrynerds_wip.activities.note.note.data.backend_oriented.async_tasks;

import android.os.AsyncTask;

import com.example.robin.angrynerds_wip.activities.note.note.data.backend_oriented.NoteDataBackend;
import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.services.ImageService;

// Authored by Jan Beilfuss
public class NotePreviewImageLoader {

    private NoteDataBackend mNoteDataBackend;

    public NotePreviewImageLoader(NoteDataBackend pNoteDataBackend) {
        this.mNoteDataBackend = pNoteDataBackend;
    }

    public void loadPreviewImage(Image image) {
        LoadPreviewImageTask loadPreviewImageTask = new LoadPreviewImageTask();
        loadPreviewImageTask.execute(image);
    }

    private class LoadPreviewImageTask extends AsyncTask<Image, Integer, Image> {

        @Override
        protected Image doInBackground(Image... images) {
            Image copy = new Image(images[0]);
            copy = ImageService.getPreviewImage(copy);
            return copy;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
        }

        @Override
        protected void onPostExecute(Image image) {
            if (image.getBitmap() == null) {
                mNoteDataBackend.getmNoteData().getNote().imageNotFound(image);
            } else {
                mNoteDataBackend.getmNoteData().getNoteApplicationLogic().getNoteAsyncLoadingLogic().addAsyncPreviewImage(image);
            }

        }
    }
}