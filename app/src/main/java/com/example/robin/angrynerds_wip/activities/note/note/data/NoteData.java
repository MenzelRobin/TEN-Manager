package com.example.robin.angrynerds_wip.activities.note.note.data;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.robin.angrynerds_wip.activities.note.note.NoteActivity;
import com.example.robin.angrynerds_wip.activities.note.note.data.backend_oriented.NoteDataBackend;
import com.example.robin.angrynerds_wip.activities.note.note.data.gui_oriented.NoteDataGui;
import com.example.robin.angrynerds_wip.activities.note.note.logic.NoteApplicationLogic;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.services.Update;
import com.example.robin.angrynerds_wip.modules.share.ShareModule;

import java.util.ArrayList;

public class NoteData {

    private NoteDataGui mNoteDataGui;
    private NoteApplicationLogic mNoteApplicationLogic;
    private NoteActivity mActivity;
    private Note mNote;
    private NoteDataBackend mNoteDataBackend;

    public NoteData(NoteActivity activity) {
        mActivity = activity;
        mNote = new Note();
        Update.saveTEN(mNote);
        mNoteDataBackend = new NoteDataBackend(this);
        mNoteDataGui = new NoteDataGui(this);
    }

    public NoteData(NoteActivity pActivity, String pId) {
        mActivity = pActivity;
        mNoteDataBackend = new NoteDataBackend(this);
        mNoteDataGui = new NoteDataGui(this);
        try{
            loadNote(pId);
        }
        catch (Exception e){
            Log.e("Error", e.getMessage());
            mNote = new Note();
        }
    }

    public NoteDataBackend getNoteDataBackend() { return mNoteDataBackend; }

    public NoteDataGui getNoteDataGui() { return mNoteDataGui; }

    public void setNote(Note pNote) { mNote = pNote; }

    public Note getNote() { return mNote; }

    public NoteApplicationLogic getNoteApplicationLogic() {
        return mNoteApplicationLogic;
    }

    public void setNoteApplicationLogic(NoteApplicationLogic pNoteApplicationLogic) {
        this.mNoteApplicationLogic = pNoteApplicationLogic;
    }

    public ArrayList<Image> getNotePreviewImages() { return mNoteDataGui.getPreviewImages(); }

    public NoteActivity getActivity() { return mActivity; }

    public Bitmap getImage(int pId) { return mNoteDataGui.getOriginalImage(pId); }

    public void addImageFromCamera(Bitmap pImage, String pFormerPath) {
        mNoteDataGui.addImageFromCamera(pImage, pFormerPath);
    }

    public void addImageFromGallery(Bitmap pImage) { mNoteDataGui.addImageFromGallery(pImage); }

    public void deleteImage(int pId) {
        mNoteDataBackend.deleteImage(pId);
        mNoteDataGui.deleteImage(pId);
    }

    public void executeSaveRoutine() { mNoteDataBackend.executeSaveRoutine(); }

    public void deleteNote() { mNoteDataBackend.deleteNote(); }

    public void shareNote() {
        ShareModule.shareNote(mActivity, mNote);
    }

    public void loadNote(String pNoteId) { mNoteDataBackend.loadNote(pNoteId); }

    public void resetNoteBitmaps() {
        for(Image image: mNote.getPictures()) image.setBitmap(null);
    }
}
