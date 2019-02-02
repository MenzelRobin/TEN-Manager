package com.example.robin.angrynerds_wip.activities.note.note.data;

import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.note.note.NoteActivity;
import com.example.robin.angrynerds_wip.activities.note.note.data.backend_oriented.NoteDataBackend;
import com.example.robin.angrynerds_wip.activities.note.note.gui.GraphicsContainer;
import com.example.robin.angrynerds_wip.activities.note.note.data.gui_oriented.NoteDataGui;
import com.example.robin.angrynerds_wip.activities.note.note.logic.NoteApplicationLogic;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.services.Update;

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

    public ArrayList<Image> getNotePreviewImages() { return mNoteDataGui.getPreviewImages(); }

    public NoteActivity getActivity() { return mActivity; }

    public Bitmap getImage(int pId) { return mNoteDataGui.getOriginalImage(pId); }

    public void addImageFromCamera(Bitmap image, String formerPath) {
        mNoteDataGui.addImageFromCamera(image, formerPath);
    }

    public void addImageFromGallery(Bitmap image) { mNoteDataGui.addImageFromGallery(image); }

    public void deleteImage(int id) {
        mNoteDataBackend.deleteImage(id);
        mNoteDataGui.deleteImage(id);
    }

    public void executeSaveRoutine() { mNoteDataBackend.executeSaveRoutine(); }

    public void deleteNote() { mNoteDataBackend.deleteNote(); }

    public void shareNote() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        String shareText = mNote.getTitle();
        shareText = shareText + "\n\n" + mNote.getDescription();

        String tags = "";
        for(String tag : mNote.getTags())
            tags = tags + "#" + tag + " ";

        shareText = shareText + "\n\n" + tags;
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        mActivity.startActivity(Intent.createChooser(shareIntent, "Teilen mit"));
    }

    public void loadNote(String pNoteId) { mNoteDataBackend.loadNote(pNoteId); }

    public void resetNoteBitmaps() {
        for(Image image: mNote.getPictures()) image.setBitmap(null);
    }
}
