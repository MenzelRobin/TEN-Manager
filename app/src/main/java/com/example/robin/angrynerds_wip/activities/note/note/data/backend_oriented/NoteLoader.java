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

    NoteDataBackend mNoteDataBackend;

    public NoteLoader(NoteDataBackend pNoteDataBackend) {
        mNoteDataBackend = pNoteDataBackend;
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
            mNoteDataBackend.getmNoteData().setNote(pNote);
            mNoteDataBackend.getmNoteData().getmNoteApplicationLogic().dataToGui();
            mNoteDataBackend.loadImages();
        }
    }
}
