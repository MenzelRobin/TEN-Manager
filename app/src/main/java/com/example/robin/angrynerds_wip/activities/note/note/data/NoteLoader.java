package com.example.robin.angrynerds_wip.activities.note.note.data;

import android.os.AsyncTask;
import android.util.Log;

import com.example.robin.angrynerds_wip.activities.note.note.logic.NoteApplicationLogic;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.services.ImageService;
import com.example.robin.angrynerds_wip.data.services.Read;

public class NoteLoader {

    private NoteApplicationLogic mNoteApplicationLogic;
    private NoteData mNoteData;
    private NoteLoader mNoteLoader;

    public NoteLoader() {
        mNoteLoader = this;
    }

    public NoteLoader(NoteApplicationLogic noteApplicationLogic, NoteData noteData) {
        mNoteApplicationLogic = noteApplicationLogic;
        mNoteData = noteData;
        mNoteLoader = this;
    }

    public void loadNote(String noteId) {
        LoadNoteTask loadNoteTask = new LoadNoteTask();
        loadNoteTask.execute(noteId);
    }

    public void loadPreviewImage(Image image) {
        Log.i("NoteRemake", "Preview Image should be loaded: " + image.getId());
        LoadPreviewImageTask loadPreviewImageTaskImageTask = new LoadPreviewImageTask();
        loadPreviewImageTaskImageTask.execute(image);
    }

    private class LoadNoteTask extends AsyncTask<String, Integer, Note> {

        private final String LOG_TAG = LoadNoteTask.class.getSimpleName();

        @Override
        protected Note doInBackground(String... strings) {
            Note note = Read.getNoteByID(strings[0]);
            return note;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
        }

        @Override
        protected void onPostExecute(Note note) {
            mNoteData.setNote(note);
            Log.i("Clicklistener1", "addImage Container was called From Load Note Task");
            mNoteApplicationLogic.dataToGui();
            mNoteData.loadImages(mNoteLoader);
        }
    }

    //Async Task that loads the Images
    private class LoadPreviewImageTask extends AsyncTask<Image, Integer, Image> {
        private final String LOG_TAG = LoadPreviewImageTask.class.getSimpleName();

        @Override
        protected Image doInBackground(Image... images) {
            Image copy = new Image(images[0]);
            copy = ImageService.getPreviewImage(copy);
            Log.i("NoteRemake", "Previewfotobitmap: " + copy.getBitmap());
            return copy;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
        }

        @Override
        protected void onPostExecute(Image image) {
            Log.i("NoteRemake", "Previewiamge loaded");
            if (image.getBitmap() == null) {
                mNoteData.getNote().imageNotFound(image);
            } else {
                mNoteApplicationLogic.addAsyncPreviewImage(image);
                mNoteApplicationLogic.initListener();
            }

            // Log.i("Testdata", "Bild hinzugefügt!");

            // Hintergrundberechnungen sind jetzt beendet, darüber informieren wir den Benutzer
        }
    }


}
