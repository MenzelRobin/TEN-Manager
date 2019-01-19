package com.example.robin.angrynerds_wip.activities.note.note.data;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.note.note.NoteActivity;
import com.example.robin.angrynerds_wip.activities.note.note.data.backend_oriented.ImageSaver;
import com.example.robin.angrynerds_wip.activities.note.note.data.backend_oriented.NoteLoader;
import com.example.robin.angrynerds_wip.activities.note.note.data.backend_oriented.OriginalImageLoader;
import com.example.robin.angrynerds_wip.activities.note.note.data.gui_oriented.IContainer;
import com.example.robin.angrynerds_wip.activities.note.note.data.gui_oriented.IconContainer;
import com.example.robin.angrynerds_wip.activities.note.note.data.gui_oriented.ImageContainer;
import com.example.robin.angrynerds_wip.activities.note.note.data.gui_oriented.NoteDataGui;
import com.example.robin.angrynerds_wip.activities.note.note.logic.NoteApplicationLogic;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.services.Delete;
import com.example.robin.angrynerds_wip.data.services.ImageService;
import com.example.robin.angrynerds_wip.data.services.Read;
import com.example.robin.angrynerds_wip.data.services.Update;

import java.util.ArrayList;

public class NoteData {

    private NoteDataGui mNoteDataGui;
    private NoteApplicationLogic mNoteApplicationLogic;
    private NoteActivity mActivity;
    private ArrayList<Image> mImagesToBeDeleted;
    private Note mNote;

    public NoteData(NoteActivity activity) {

        Log.i("Clicklistener1", "NoteData was called");
        mActivity = activity;
        mNote = new Note();
        mImagesToBeDeleted = new ArrayList<>();
        mNoteDataGui = new NoteDataGui(this);
        addImageButton();
    }

    public void setNote(Note note) {
        mNote = note;
    }

    //TODO to NoteDATAGUI
    public void imagesToImageContainer() {
        mNoteDataGui.imagesToImageContainer();
    }

    //stay
    public void setmNoteApplicationLogic(NoteApplicationLogic mNoteApplicationLogic) {
        this.mNoteApplicationLogic = mNoteApplicationLogic;
    }

    //stay
    public NoteApplicationLogic getmNoteApplicationLogic() {
        return mNoteApplicationLogic;
    }
    //Adds the addImageButton to mNoteContainers

    //GUI
    public void addImageButton() {
        mNoteDataGui.addImageButton();
    }

    //delete
    public void saveDataInBundle(Bundle outState) {
        //outState = mNote.getBundle();
    }

    //delete
    public void restoreDataFromBundle(Bundle savedInstanceState) {
    }

    //keep until setDescription
    public ArrayList<IContainer> getNoteImageContainers() {
        return mNoteDataGui.getNoteImageContainers();
    }

    public IContainer getImageContainer(int index) {
        return mNoteDataGui.getImageContainer(index);
    }

    public NoteActivity getActivity() {
        return mActivity;
    }

    public Note getNote() {
        return mNote;
    }

    // evaluate delete
    public void setTitle(String title) {
        mNote.setTitle(title);
    }
    //evaluate delete
    public void setDescription(String title) {
        mNote.setDescription(title);
    }
    //returns image from mNoteImageContainers

    //rename, GUI | gibt Originales Bild zurück
    public Bitmap getImage(int id) {
        //TODO get original sized image from database

        int index = id - 1;
        Bitmap bitmap = mNote.getPictures().get(index).getBitmap();
        if (bitmap == null) {
            //in NoteRep
            OriginalImageLoader originalImageLoader = new OriginalImageLoader(this, true);
            originalImageLoader.loadOriginalImage(index);
        }

        return bitmap;
    }

    //Checks ImageContainer for specific ID, GUI
    public boolean checkImageID(int id) {
        return mNoteDataGui.checkImageListForId(id);
    }

    //Adds image as ImageContainer to mNoteImageContainers
    //GUI
    public void addImageFromCamera(Bitmap image, String formerPath) {
        mNoteDataGui.addImageFromGallery(image);

        //TODO NoteDataRep or NoteDataGui?
        Image originalImage = mNote.getPictures().get(mNote.getPictures().size() - 1);
        ImageSaver imageSaver = new ImageSaver();
        imageSaver.saveImage(originalImage);

        ImageService.deleteImage(formerPath);
    }

    //Deletes specific ImageContainer from mNoteImageContainers
    //TODO NoteDataGui or Rep?
    public void deleteImage(int id) {
        Image image = new Image(mNote.getPictures().get(id - 1));
        mNote.getPictures().remove(id - 1);
        mImagesToBeDeleted.add(image);
        imagesToImageContainer();
    }

    //Repository
    public void finallyDeleteImages() {
        for (Image image : mImagesToBeDeleted) {
            ImageService.deleteImage(image);
        }
    }

    public void addImageContainer(Image image) {
        mNoteDataGui.addImageContainer(image);
    }

    public void addImageFromGallery(Bitmap image) {
        mNoteDataGui.addImageFromGallery(image);
    }

    //Repository
    public void loadImages(NoteLoader noteLoader) {
        Log.i("NoteRemake", "" + mNote.getPictures().size());
        for (Image image : this.mNote.getPictures()) {
            noteLoader.loadPreviewImage(image);
        }
        OriginalImageLoader originalImageLoader = new OriginalImageLoader(this, false);
        originalImageLoader.loadOriginalImage();
    }

    //Repository
    public void setColors(String noteId) {
        int[] colors = Read.getColors(noteId);
        if (mNote == null) {
            mNote = new Note();
        }
        mNote.setColor(colors[0]);
        mNote.setAccentColor(colors[1]);
    }

    //Repository
    public void executeSaveRoutine() {
        Update.saveTEN(this.mNote);
    }

    //Repository
    public void deleteNote() {
        Delete.deleteTEN(mNote.getID());
    }
}
