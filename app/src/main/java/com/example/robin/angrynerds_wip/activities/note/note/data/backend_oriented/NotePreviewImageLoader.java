package com.example.robin.angrynerds_wip.activities.note.note.data.backend_oriented;

import android.os.AsyncTask;

import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.services.ImageService;

public class NotePreviewImageLoader {

    NoteDataBackend mNoteDataBackend;

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
                mNoteDataBackend.getmNoteData().getNoteApplicationLogic().addAsyncPreviewImage(image);
                mNoteDataBackend.getmNoteData().getNoteApplicationLogic().initListener();
            }

        }
    }
}