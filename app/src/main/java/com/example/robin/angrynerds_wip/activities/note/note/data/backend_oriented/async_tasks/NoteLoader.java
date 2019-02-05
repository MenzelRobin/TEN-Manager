package com.example.robin.angrynerds_wip.activities.note.note.data.backend_oriented.async_tasks;

import android.os.AsyncTask;

import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.services.Read;

// Authored by Jan Beilfuss
public class NoteLoader {

    TaskManager mTaskmanager;

    public NoteLoader(TaskManager pTaskmanager) {
        mTaskmanager = pTaskmanager;
    }

    public void loadNote(String pNoteId) {
        LoadNoteTask loadNoteTask = new LoadNoteTask();
        loadNoteTask.execute(pNoteId);
    }

    private class LoadNoteTask extends AsyncTask<String, Integer, Note> {

        @Override
        protected Note doInBackground(String... pStrings) {
            Note note = Read.getNoteByID(pStrings[0]);
            return note;
        }

        @Override
        protected void onPostExecute(Note pNote) {
            mTaskmanager.getNoteDataBackend().getmNoteData().setNote(pNote);
            mTaskmanager.getNoteDataBackend().getmNoteData().getNoteApplicationLogic().dataToGui();
            mTaskmanager.loadPreviewImages();
        }
    }
}
