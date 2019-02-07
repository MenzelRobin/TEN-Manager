package com.example.robin.angrynerds_wip.activities.note.note.data.backend_oriented.async_tasks;

import android.os.AsyncTask;
import android.widget.Toast;

import com.example.robin.angrynerds_wip.activities.note.note.data.backend_oriented.NoteDataBackend;
import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.services.ImageService;

// Authored by Jan Beilfuss
public class PriorityOriginalImageLoader {

    private NoteDataBackend mNoteDataBackend;
    private int mIndexToBeLoaded;

    public PriorityOriginalImageLoader(NoteDataBackend pNoteDataBackend) {
        this.mNoteDataBackend = pNoteDataBackend;
        this.mIndexToBeLoaded = 0;
    }

    public void loadOriginalImage(int pIndex) {
        mIndexToBeLoaded = pIndex;
        if (this.mNoteDataBackend.getmNoteData().getNote().getPictures().size() > pIndex) {
            LoadOriginalImageTask loadOriginalImageTask = new LoadOriginalImageTask();
            loadOriginalImageTask.execute(pIndex);
        }
    }

    private class LoadOriginalImageTask extends AsyncTask<Integer, Integer, Image> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mNoteDataBackend.getmNoteData().getNoteApplicationLogic().getNoteAsyncLoadingLogic().startLoadingSpinner();
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
                mNoteDataBackend.getmNoteData().getNoteApplicationLogic().getNoteAsyncLoadingLogic().onOriginalImageLoadFailed(pImage, mIndexToBeLoaded);

            } else {
                mNoteDataBackend.getmNoteData().getNoteApplicationLogic().getNoteAsyncLoadingLogic().afterOriginalImageLoad(pImage, mIndexToBeLoaded);

            }
        }
    }
}
