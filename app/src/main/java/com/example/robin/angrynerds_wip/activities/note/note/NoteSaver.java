package com.example.robin.angrynerds_wip.activities.note.note;

import android.os.AsyncTask;
import android.util.Log;

import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.services.Update;

public class NoteSaver {

    NoteSaver() {

    }

    public void saveNote(Note note) {
        LoadNoteTask loadNoteTask = new LoadNoteTask();
        loadNoteTask.execute(note);
    }

    private class LoadNoteTask extends AsyncTask<Note, Integer, Boolean> {


        @Override
        protected Boolean doInBackground(Note... notes) {
            Update.saveTEN(notes[0]);
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
        }

        @Override
        protected void onPostExecute(Boolean bool) {
            Log.i("NoteRemake", "Successfully saved");
        }
    }
}
