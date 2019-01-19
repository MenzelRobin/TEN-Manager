package com.example.robin.angrynerds_wip.activities.note.note.data.backend_oriented;

import android.os.AsyncTask;
import android.util.Log;

import com.example.robin.angrynerds_wip.activities.note.note.data.NoteData;
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
            mNoteApplicationLogic.dataToGui();
            mNoteData.loadImages(mNoteLoader);
        }
    }
}