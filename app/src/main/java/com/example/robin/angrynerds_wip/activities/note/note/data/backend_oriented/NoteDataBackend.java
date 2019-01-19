package com.example.robin.angrynerds_wip.activities.note.note.data.backend_oriented;

import android.util.Log;

import com.example.robin.angrynerds_wip.activities.note.note.data.NoteData;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.services.Delete;
import com.example.robin.angrynerds_wip.data.services.ImageService;
import com.example.robin.angrynerds_wip.data.services.Read;
import com.example.robin.angrynerds_wip.data.services.Update;

import java.util.ArrayList;

public class NoteDataBackend {
    private ArrayList<Image> mImagesToBeDeleted;
    private NoteData mNoteData;


    public NoteDataBackend(NoteData pNoteData){
        mImagesToBeDeleted = new ArrayList<>();
        this.mNoteData = pNoteData;
    }

    public NoteData getmNoteData() {
        return mNoteData;
    }

    public void finallyDeleteImages() {
        for (Image image : mImagesToBeDeleted) {
            ImageService.deleteImage(image);
        }
    }

    public void deleteImage(int id){
        Image image = new Image(mNoteData.getNote().getPictures().get(id - 1));
        mNoteData.getNote().getPictures().remove(id - 1);
        mImagesToBeDeleted.add(image);
        mNoteData.getNoteDataGui().imagesToImageContainer();

    }

    public void loadImages() {
        NotePreviewImageLoader notePreviewImageLoader = new NotePreviewImageLoader(this);
        for (Image image : this.mNoteData.getNote().getPictures()) {
            notePreviewImageLoader.loadPreviewImage(image);
        }
        OriginalImageLoader originalImageLoader = new OriginalImageLoader(this, false);
        originalImageLoader.loadOriginalImage();
    }

    public void triggerOriginalImageLoad(int index) {
        OriginalImageLoader originalImageLoader = new OriginalImageLoader(this, true);
        originalImageLoader.loadOriginalImage(index);
    }

    //Repository
    public void setColors(String noteId) {
        int[] colors = Read.getColors(noteId);
        if (mNoteData.getNote() == null) {
            mNoteData.setNote(new Note());
        }
        mNoteData.getNote().setColor(colors[0]);
        mNoteData.getNote().setAccentColor(colors[1]);
    }

    //Repository
    public void executeSaveRoutine() {
        Update.saveTEN(mNoteData.getNote());
    }

    //Repository
    public void deleteNote() {
        Delete.deleteTEN(mNoteData.getNote().getID());
    }


    public void loadNote(String pNoteId) {
        setColors(pNoteId);
        NoteLoader noteLoader = new NoteLoader(this);
        noteLoader.loadNote(pNoteId);
    }
}
