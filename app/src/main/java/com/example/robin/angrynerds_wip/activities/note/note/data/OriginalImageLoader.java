package com.example.robin.angrynerds_wip.activities.note.note.data;

import android.os.AsyncTask;
import android.util.Log;

import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.services.ImageService;

public class OriginalImageLoader {

    NoteData mNoteData;
    int indexToBeLoaded;

    public OriginalImageLoader(NoteData noteData) {
        this.mNoteData = noteData;
        this.indexToBeLoaded = 0;
    }

    public void loadOriginalImage() {

        if (this.mNoteData.getNote().getPictures().size() > indexToBeLoaded) {
            Image image = this.mNoteData.getNote().getPictures().get(indexToBeLoaded);
            Log.i("NoteRemake", "Original Image should be loaded(1/2): " + image.getId());
            if (image.getBitmap() == null) {
                Log.i("NoteRemake", "Original Image should be loaded(2/2): " + image.getId());
                LoadOriginalImageTask loadOriginalImageTask = new LoadOriginalImageTask();
                loadOriginalImageTask.execute(image);
            }
            this.indexToBeLoaded++;
            loadOriginalImage();

        }

    }

    public LoadOriginalImageTask loadOriginalImage(int index) {
        if (this.mNoteData.getNote().getPictures().size() > indexToBeLoaded) {
            Image image = this.mNoteData.getNote().getPictures().get(indexToBeLoaded);
            if (image.getBitmap() == null) {
                Log.i("NoteRemake", "Original Image should be loaded: " + image.getId());
                LoadOriginalImageTask loadOriginalImageTask = new LoadOriginalImageTask();
                loadOriginalImageTask.execute(image);
                this.loadOriginalImage();
                return loadOriginalImageTask;
            }
        }
        this.loadOriginalImage();
        return null;
    }

    private class LoadOriginalImageTask extends AsyncTask<Image, Integer, Image> {

        @Override
        protected Image doInBackground(Image... images) {
            Image image = ImageService.getImage(images[0]);
            Log.i("NoteRemake", "Originalfotobitmap: " + image.getBitmap());
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
            }
            loadOriginalImage();

            // Log.i("Testdata", "Bild hinzugefügt!");

            // Hintergrundberechnungen sind jetzt beendet, darüber informieren wir den Benutzer
        }
    }
}
