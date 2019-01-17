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
        Log.i("NoteRemake", "NO PRIORITY OBject" + this);
        if (this.mNoteData.getNote().getPictures().size() > mIndexToBeLoaded) {
            Image image = this.mNoteData.getNote().getPictures().get(mIndexToBeLoaded);
            Log.i("NoteRemake", "Original Image should be loaded(1/2): " + image.getId());
            if (image.getBitmap() == null) {
                Log.i("NoteRemake", "Original Image should be loaded(2/2): " + image.getId());
                LoadOriginalImageTask loadOriginalImageTask = new LoadOriginalImageTask();
                loadOriginalImageTask.execute(image);
            }
            this.mIndexToBeLoaded++;
            loadOriginalImage();

        }

    }

    public void loadOriginalImage(int index) {
        Log.i("NoteRemake", "PRIORITY OBject" + this);
        if (this.mNoteData.getNote().getPictures().size() > index) {
            Image image = this.mNoteData.getNote().getPictures().get(index);
            Log.i("NoteRemake", "Original Image should be loaded (PRIORITY) 1: " + image.getId());
            if (image.getBitmap() == null) {
                Log.i("NoteRemake", "Original Image should be loaded (PRIORITY) 2: " + image.getId());
                LoadOriginalImageTask loadOriginalImageTask = new LoadOriginalImageTask();
                loadOriginalImageTask.execute(image);
            }
        }
    }

    private class LoadOriginalImageTask extends AsyncTask<Image, Integer, Image> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if (mIsPriority) {
                mNoteData.getmNoteApplicationLogic().startLoadingSpinner();
            }
        }

        @Override
        protected Image doInBackground(Image... images) {
            Image image = ImageService.getImage(images[0]);
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
