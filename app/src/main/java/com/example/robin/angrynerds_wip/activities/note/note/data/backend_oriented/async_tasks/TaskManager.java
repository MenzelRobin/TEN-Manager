package com.example.robin.angrynerds_wip.activities.note.note.data.backend_oriented.async_tasks;

import com.example.robin.angrynerds_wip.activities.note.note.data.backend_oriented.NoteDataBackend;
import com.example.robin.angrynerds_wip.activities.note.note.data.backend_oriented.async_tasks.NoteLoader;
import com.example.robin.angrynerds_wip.activities.note.note.data.backend_oriented.async_tasks.NotePreviewImageLoader;
import com.example.robin.angrynerds_wip.activities.note.note.data.backend_oriented.async_tasks.PriorityOriginalImageLoader;
import com.example.robin.angrynerds_wip.data.models.utils.Image;

public class TaskManager {

    private NoteDataBackend mNoteDataBackend;

    public TaskManager(NoteDataBackend pNoteDataBackend){
        this.mNoteDataBackend = pNoteDataBackend;
    }

    public NoteDataBackend getNoteDataBackend() {
        return mNoteDataBackend;
    }

    public void triggerOriginalImagePriorityLoad(int pIndex) {
        PriorityOriginalImageLoader priorityOriginalImageLoader = new PriorityOriginalImageLoader(mNoteDataBackend);
        priorityOriginalImageLoader.loadOriginalImage(pIndex);
    }

    public void loadPreviewImages() {
        NotePreviewImageLoader notePreviewImageLoader = new NotePreviewImageLoader(mNoteDataBackend);
        for (Image image : this.mNoteDataBackend.getmNoteData().getNote().getPictures()) {
            notePreviewImageLoader.loadPreviewImage(image);
        }
    }

    public void loadNote(String pNoteId) {
    mNoteDataBackend.setColors(pNoteId);
    NoteLoader noteLoader = new NoteLoader(this);
        noteLoader.loadNote(pNoteId);

    }
}
