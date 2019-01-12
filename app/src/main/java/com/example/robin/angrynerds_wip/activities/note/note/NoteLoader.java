package com.example.robin.angrynerds_wip.activities.note.note;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.services.Read;

public class NoteLoader {

    private ApplicationLogic mApplicationLogic;
    private NoteData mNoteData;
    private NoteLoader mNoteLoader;

    public NoteLoader(){
        mNoteLoader = this;
    }

    public NoteLoader(ApplicationLogic applicationLogic, NoteData noteData){
        mApplicationLogic = applicationLogic;
        mNoteData = noteData;
        mNoteLoader = this;
    }

    public void loadNote(String noteId){
        LoadNoteTask loadNoteTask = new LoadNoteTask();
        loadNoteTask.execute(noteId);
    }

    public void loadImage(String noteId, String imageId){
        LoadImageTask loadImageTask = new LoadImageTask();
        loadImageTask.execute(noteId, imageId);
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
            Log.i(LOG_TAG, "Note: " + note + " NoteData: " + mNoteData);
            mNoteData.setNote(note);
            mApplicationLogic.dataToGui();
            mNoteData.loadImages(mNoteData.getNote().getID(), mNoteLoader);
        }
    }

    //Async Task that loads the Images
    private class LoadImageTask extends AsyncTask<String, Integer, Bitmap> {
        private final String LOG_TAG = LoadImageTask.class.getSimpleName();

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = Read.getImageOfNote(strings[0], strings[1]);
            return bitmap;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            mNoteData.getNote().getPictures().add(bitmap);
            mNoteData.imagesToImageContainer();
            mApplicationLogic.dataToGui();
            mApplicationLogic.initListener();
            Log.i("Testdata", "Bild hinzugefügt!");

            // Hintergrundberechnungen sind jetzt beendet, darüber informieren wir den Benutzer
        }
    }
}
