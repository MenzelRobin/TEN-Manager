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

    //TODO define ifs
    public boolean isNoteSavable(){
        String title = mNoteData.getNote().getTitle();
        String description = mNoteData.getNote().getDescription();

        if(title == null || description == null){
            return false;
        }
        else if(title.equals("") && description.equals("")){
            return false;
        }
        return  true;
    }
}
