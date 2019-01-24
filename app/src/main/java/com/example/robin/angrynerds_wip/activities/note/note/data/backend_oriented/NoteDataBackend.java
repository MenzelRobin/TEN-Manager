package com.example.robin.angrynerds_wip.activities.note.note.data.backend_oriented;

import com.example.robin.angrynerds_wip.activities.note.note.data.NoteData;
import com.example.robin.angrynerds_wip.activities.note.note.data.NoteDataHelper;
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
    private TaskManager mTaskManager;

    public NoteDataBackend(NoteData pNoteData) {
        mImagesToBeDeleted = new ArrayList<>();
        this.mNoteData = pNoteData;
        this.mTaskManager = new TaskManager(this);
    }

    public NoteData getmNoteData() {
        return mNoteData;
    }

    public void deleteImage(int id) {
        Image image = new Image(mNoteData.getNote().getPictures().get(id - 1));
        mNoteData.getNote().getPictures().remove(id - 1);
        mImagesToBeDeleted.add(image);
        mNoteData.getNoteDataGui().imagesToImageContainer();
    }

    public void triggerOriginalImageLoad(int pIndex) {
        mTaskManager.triggerOriginalImagePriorityLoad(pIndex);
    }

    public void setColors(String pNoteId) {
        int[] colors = Read.getColors(pNoteId);
        if (mNoteData.getNote() == null) {
            mNoteData.setNote(new Note());
        }
        mNoteData.getNote().setColor(colors[0]);
        mNoteData.getNote().setAccentColor(colors[1]);
    }

    public void executeSaveRoutine() {
        finallyDeleteImages();
        NoteDataHelper noteDataHelper = new NoteDataHelper(this.mNoteData);
        if (noteDataHelper.isNoteSavable()) {
            Update.saveTEN(mNoteData.getNote());
        }
    }

    public void finallyDeleteImages() {
        for (Image image : mImagesToBeDeleted) {
            ImageService.deleteImage(image);
        }
    }

    public void deleteNote() {
        Delete.deleteTEN(mNoteData.getNote().getID());
    }

    public void loadNote(String pNoteId) {
        mTaskManager.loadNote(pNoteId);
    }

    public void deleteImageFromDisk(String pFormerPath) {
        ImageService.deleteImage(pFormerPath);
    }

    public void saveImage(Image pOriginalImage) {
        mTaskManager.saveImage(pOriginalImage);
    }
}
