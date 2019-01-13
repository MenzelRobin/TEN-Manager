package com.example.robin.angrynerds_wip.activities.note.note;

import android.os.AsyncTask;
import android.util.Log;

import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.services.ImageService;
import com.example.robin.angrynerds_wip.data.services.Read;

public class NoteLoader {

    private ApplicationLogic mApplicationLogic;
    private NoteData mNoteData;
    private NoteLoader mNoteLoader;
    private ImageService imageService;

    public NoteLoader(){
        mNoteLoader = this;
        imageService = new ImageService();
    }

    public NoteLoader(ApplicationLogic applicationLogic, NoteData noteData){
        mApplicationLogic = applicationLogic;
        mNoteData = noteData;
        mNoteLoader = this;
        imageService = new ImageService();
    }

    public void loadNote(String noteId){
        LoadNoteTask loadNoteTask = new LoadNoteTask();
        loadNoteTask.execute(noteId);
    }

    public void loadImage(Image image){
        Log.i("NoteRemake", "Image should be loaded: " + image.getId());
        LoadImageTask loadImageTask = new LoadImageTask();
        loadImageTask.execute(image);
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
            mApplicationLogic.dataToGui();
            mNoteData.loadImages(mNoteLoader);
        }
    }

    //Async Task that loads the Images
    private class LoadImageTask extends AsyncTask<Image, Integer, Image> {
        private final String LOG_TAG = LoadImageTask.class.getSimpleName();

        @Override
        protected Image doInBackground(Image... images) {
            Image image = imageService.getImage(images[0]);
            return image;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
        }

        @Override
        protected void onPostExecute(Image image) {

            mNoteData.getNote().addImage(image);
            mApplicationLogic.addSingleImage(image);
            mApplicationLogic.initListener();
            // Log.i("Testdata", "Bild hinzugefügt!");

            // Hintergrundberechnungen sind jetzt beendet, darüber informieren wir den Benutzer
        }
    }
}
