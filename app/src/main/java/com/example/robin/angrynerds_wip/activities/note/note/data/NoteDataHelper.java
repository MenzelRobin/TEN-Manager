package com.example.robin.angrynerds_wip.activities.note.note.data;

public class NoteDataHelper {

    NoteData mNoteData;
    public NoteDataHelper(NoteData pNoteDate){
        mNoteData = pNoteDate;
    }

    public NoteDataHelper(){
    }

    public int getNoteImageIndexFromContainerIndex(int pContainerIndex){
        return pContainerIndex - 1;
    }

    //TODO define ifs
    public boolean isNoteSaveable(){
        String title = mNoteData.getNote().getTitle();
        String description = mNoteData.getNote().getDescription();

        if(title == null || description == null){
            return false;
        }
        else if(title.equals("") && description.equals("")){
            return  false;
        }
        return  true;
    }
}
