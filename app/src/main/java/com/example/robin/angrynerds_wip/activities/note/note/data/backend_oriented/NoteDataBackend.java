package com.example.robin.angrynerds_wip.activities.note.note.data.backend_oriented;

import android.util.Log;

import com.example.robin.angrynerds_wip.activities.note.note.data.NoteData;
import com.example.robin.angrynerds_wip.activities.note.note.data.gui_oriented.ImageContainer;
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

    public void finallyDeleteImages() {
        for (Image image : mImagesToBeDeleted) {
            ImageService.deleteImage(image);
        }
    }

    //Repository
    public void loadImages(NoteLoader noteLoader) {
        Log.i("NoteRemake", "" + mNoteData.getNote().getPictures().size());
        for (Image image : this.mNoteData.getNote().getPictures()) {
            //noteLoader.loadPreviewImage(image);
        }
        //OriginalImageLoader originalImageLoader = new OriginalImageLoader(this, false);
        //originalImageLoader.loadOriginalImage();
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

    public void triggerOriginalImageLoad() {
        OriginalImageLoader originalImageLoader = new OriginalImageLoader(this, true);
        originalImageLoader.loadOriginalImage(index);
    }
}
