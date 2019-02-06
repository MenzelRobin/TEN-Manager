package com.example.robin.angrynerds_wip.activities.note.note.data;

import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.services.Delete;

// Authored by Jan Beilfuss
public class NoteDataHelper {

    private NoteData mNoteData;

    public NoteDataHelper(NoteData pNoteData){
        mNoteData = pNoteData;
    }

    public boolean isSaveable() {
        Note note = mNoteData.getNote();
        if(note.getTitle().equals("")){
            if (note.getDescription().equals("")){
                if(note.getPictures().size() == 0){
                    if (note.getTags().size() == 0){
                        Delete.deleteTEN(note.getID());
                        return false;
                    }
                }
            }
        }
        return  true;
    }
}
