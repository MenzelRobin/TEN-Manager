package com.example.robin.angrynerds_wip.activities.note.note.data.backend_oriented;

import android.os.AsyncTask;

import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.services.ImageService;
//TODO delete
public class RegularOriginalImageLoader {

    NoteDataBackend mNoteDataBackend;
    int mIndexToBeLoaded;

    public RegularOriginalImageLoader(NoteDataBackend pNoteDataBackend) {
        this.mNoteDataBackend = pNoteDataBackend;
        this.mIndexToBeLoaded = 0;
    }

    public void loadOriginalImage() {
        if (this.mNoteDataBackend.getmNoteData().getNote().getPictures().size() > mIndexToBeLoaded) {
            if (this.mNoteDataBackend.getmNoteData().getNote().getPictures().get(mIndexToBeLoaded).getBitmap() == null) {
                LoadOriginalImageTask loadOriginalImageTask = new LoadOriginalImageTask();
                loadOriginalImageTask.execute(this.mIndexToBeLoaded);
            }
            this.mIndexToBeLoaded++;
        }
    }

    private class LoadOriginalImageTask extends AsyncTask<Integer, Integer, Image> {

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
                loadOriginalImage();
            }
        }
    }
}
