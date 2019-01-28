package com.example.robin.angrynerds_wip.activities.note.note.data;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.robin.angrynerds_wip.activities.note.note.NoteActivity;
import com.example.robin.angrynerds_wip.activities.note.note.data.backend_oriented.NoteDataBackend;
import com.example.robin.angrynerds_wip.activities.note.note.gui.GraphicsContainer;
import com.example.robin.angrynerds_wip.activities.note.note.data.gui_oriented.NoteDataGui;
import com.example.robin.angrynerds_wip.activities.note.note.logic.NoteApplicationLogic;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.utils.Image;

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
        mNoteDataBackend = new NoteDataBackend(this);
        mNoteDataGui = new NoteDataGui(this);
    }

    public NoteData(NoteActivity activity, String pId) {
        mActivity = activity;
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

    public void setNote(Note note) { mNote = note; }

    public Note getNote() { return mNote; }

    public NoteApplicationLogic getNoteApplicationLogic() {
        return mNoteApplicationLogic;
    }

    public void setNoteApplicationLogic(NoteApplicationLogic pNoteApplicationLogic) {
        this.mNoteApplicationLogic = pNoteApplicationLogic;
    }

    public ArrayList<GraphicsContainer> getNoteImageContainers() { return mNoteDataGui.getNoteImageContainers(); }

    public NoteActivity getActivity() { return mActivity; }

    public Bitmap getImage(int pId) { return mNoteDataGui.getOriginalImage(pId); }

    public boolean checkImageID(int id) { return mNoteDataGui.checkImageListForId(id); }

    public void addImageFromCamera(Bitmap image, String formerPath) {
        mNoteDataGui.addImageFromCamera(image, formerPath);
    }

    public void addImageFromGallery(Bitmap image) { mNoteDataGui.addImageFromGallery(image); }

    public void deleteImage(int id) {
        mNoteDataBackend.deleteImage(id);
        mNoteDataGui.deleteImage(id);
    }

    public void addImageContainer(Image image) { mNoteDataGui.addImageContainer(image); }

    public void executeSaveRoutine() { mNoteDataBackend.executeSaveRoutine(); }

    public void deleteNote() { mNoteDataBackend.deleteNote(); }

    public void loadNote(String pNoteId) { mNoteDataBackend.loadNote(pNoteId); }

    public void resetNoteBitmaps() {
        for(Image image: mNote.getPictures()) image.setBitmap(null);
    }
}
