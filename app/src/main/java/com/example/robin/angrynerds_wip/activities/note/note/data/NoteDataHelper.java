package com.example.robin.angrynerds_wip.activities.note.note.data;

public class NoteDataHelper {

    private NoteData mNoteData;

    public NoteDataHelper(NoteData pNoteData){
        mNoteData = pNoteData;
    }

    public NoteDataHelper(){
    }

    public int getNoteImageIndexFromContainerIndex(int pContainerIndex){
        return pContainerIndex - 1;
    }

}
