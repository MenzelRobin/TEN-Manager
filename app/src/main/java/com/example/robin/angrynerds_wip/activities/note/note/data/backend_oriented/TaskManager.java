package com.example.robin.angrynerds_wip.activities.note.note.data.backend_oriented;

import android.util.Log;

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

    public void saveImage(Image originalImage) {
        ImageSaver imageSaver = new ImageSaver(mNoteDataBackend);
        imageSaver.saveImage(originalImage);
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
