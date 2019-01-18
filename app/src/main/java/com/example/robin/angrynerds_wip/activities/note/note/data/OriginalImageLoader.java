package com.example.robin.angrynerds_wip.activities.note.note.data;

import android.os.AsyncTask;
import android.util.Log;

import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.services.ImageService;

public class OriginalImageLoader {

    NoteData mNoteData;
    int mIndexToBeLoaded;
    boolean mIsPriority;

    public OriginalImageLoader(NoteData noteData, boolean isPriority) {
        this.mNoteData = noteData;
        this.mIndexToBeLoaded = 0;
        this.mIsPriority = isPriority;
    }

    public void loadOriginalImage() {
        Log.i("NoteRemake", "loadOriginalImage was called");
        if (this.mNoteData.getNote().getPictures().size() > mIndexToBeLoaded) {
            if (this.mNoteData.getNote().getPictures().get(mIndexToBeLoaded).getBitmap() == null) {
                LoadOriginalImageTask loadOriginalImageTask = new LoadOriginalImageTask();
                loadOriginalImageTask.execute(this.mIndexToBeLoaded);
            }
            this.mIndexToBeLoaded++;
        }


    }


    public void loadOriginalImage(int index) {
        Log.i("NoteRemake", "PRIORITY OBject" + this);
        if (this.mNoteData.getNote().getPictures().size() > index) {
            LoadOriginalImageTask loadOriginalImageTask = new LoadOriginalImageTask();
            loadOriginalImageTask.execute(index);
        }
    }

    private class LoadOriginalImageTask extends AsyncTask<Integer, Integer, Image> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if (mIsPriority) {
                mNoteData.getmNoteApplicationLogic().startLoadingSpinner();
            }
        }

        @Override
        protected Image doInBackground(Integer... ints) {

            Log.i("NoteRemake", "Index: " + ints[0]);
            Image image = mNoteData.getNote().getPictures().get(ints[0]);

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
                mNoteData.getNote().imageNotFound(image);
            } else {
                mNoteData.getNote().addImage(image);

                if (mIsPriority) {
                    mNoteData.getmNoteApplicationLogic().openImagePopup(image.getBitmap());
                    mNoteData.getmNoteApplicationLogic().stopLoadingSpinner();
                } else {
                    loadOriginalImage();
                }
            }

            // Log.i("Testdata", "Bild hinzugefügt!");

            // Hintergrundberechnungen sind jetzt beendet, darüber informieren wir den Benutzer
        }
    }
}
