package com.example.robin.angrynerds_wip.activities.note.note.data.backend_oriented;

import android.os.AsyncTask;

import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.services.ImageService;

public class PriorityOriginalImageLoader {

    NoteDataBackend mNoteDataBackend;
    int mIndexToBeLoaded;

    public PriorityOriginalImageLoader(NoteDataBackend pNoteDataBackend) {
        this.mNoteDataBackend = pNoteDataBackend;
        this.mIndexToBeLoaded = 0;
    }

    public void loadOriginalImage(int pIndex) {
        if (this.mNoteDataBackend.getmNoteData().getNote().getPictures().size() > pIndex) {
            LoadOriginalImageTask loadOriginalImageTask = new LoadOriginalImageTask();
            loadOriginalImageTask.execute(pIndex);
        }
    }

    private class LoadOriginalImageTask extends AsyncTask<Integer, Integer, Image> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mNoteDataBackend.getmNoteData().getmNoteApplicationLogic().startLoadingSpinner();
        }

        @Override
        protected Image doInBackground(Integer... pInts) {
            Image image = mNoteDataBackend.getmNoteData().getNote().getPictures().get(pInts[0]);

            if (image.getBitmap() == null) {
                image = ImageService.getImage(image);
            }
            return image;
        }

        @Override
        protected void onPostExecute(Image pImage) {

            if (pImage.getBitmap() == null) {
                mNoteDataBackend.getmNoteData().getNote().imageNotFound(pImage);
            } else {
                mNoteDataBackend.getmNoteData().getNote().addImage(pImage);
                mNoteDataBackend.getmNoteData().getmNoteApplicationLogic().openImagePopup(pImage.getBitmap());
                mNoteDataBackend.getmNoteData().getmNoteApplicationLogic().stopLoadingSpinner();
            }
        }
    }
}
