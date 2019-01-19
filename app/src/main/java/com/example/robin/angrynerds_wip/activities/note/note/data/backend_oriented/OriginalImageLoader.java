package com.example.robin.angrynerds_wip.activities.note.note.data.backend_oriented;

import android.os.AsyncTask;
import android.util.Log;

import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.services.ImageService;

public class OriginalImageLoader {

    NoteDataBackend mNoteDataBackend;
    int mIndexToBeLoaded;
    boolean mIsPriority;

    public OriginalImageLoader(NoteDataBackend pNoteDataBackend, boolean pIsPriority) {
        this.mNoteDataBackend = pNoteDataBackend;
        this.mIndexToBeLoaded = 0;
        this.mIsPriority = pIsPriority;
    }

    public void loadOriginalImage() {
        Log.i("NoteRemake", "loadOriginalImage was called");
        if (this.mNoteDataBackend.getmNoteData().getNote().getPictures().size() > mIndexToBeLoaded) {
            if (this.mNoteDataBackend.getmNoteData().getNote().getPictures().get(mIndexToBeLoaded).getBitmap() == null) {
                LoadOriginalImageTask loadOriginalImageTask = new LoadOriginalImageTask();
                loadOriginalImageTask.execute(this.mIndexToBeLoaded);
            }
            this.mIndexToBeLoaded++;
        }


    }


    public void loadOriginalImage(int index) {
        Log.i("NoteRemake", "PRIORITY OBject" + this);
        if (this.mNoteDataBackend.getmNoteData().getNote().getPictures().size() > index) {
            LoadOriginalImageTask loadOriginalImageTask = new LoadOriginalImageTask();
            loadOriginalImageTask.execute(index);
        }
    }

    private class LoadOriginalImageTask extends AsyncTask<Integer, Integer, Image> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if (mIsPriority) {
                mNoteDataBackend.getmNoteData().getmNoteApplicationLogic().startLoadingSpinner();
            }
        }

        @Override
        protected Image doInBackground(Integer... ints) {

            Log.i("NoteRemake", "Index: " + ints[0]);
            Image image = mNoteDataBackend.getmNoteData().getNote().getPictures().get(ints[0]);

            if (image.getBitmap() == null) {
                Log.i("NoteRemake", "Musste nicht geladen werden");
                image = ImageService.getImage(image);
            }

            //Log.i("NoteRemake", "Originalfotobitmap: " + image.getBitmap());
            return image;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
        }

        @Override
        protected void onPostExecute(Image image) {

            if (image.getBitmap() == null) {
                mNoteDataBackend.getmNoteData().getNote().imageNotFound(image);
            } else {
                mNoteDataBackend.getmNoteData().getNote().addImage(image);

                if (mIsPriority) {
                    mNoteDataBackend.getmNoteData().getmNoteApplicationLogic().openImagePopup(image.getBitmap());
                    mNoteDataBackend.getmNoteData().getmNoteApplicationLogic().stopLoadingSpinner();
                } else {
                    loadOriginalImage();
                }
            }

            // Log.i("Testdata", "Bild hinzugefügt!");

            // Hintergrundberechnungen sind jetzt beendet, darüber informieren wir den Benutzer
        }
    }
}
