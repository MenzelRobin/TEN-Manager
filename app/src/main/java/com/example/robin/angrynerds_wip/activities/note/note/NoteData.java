package com.example.robin.angrynerds_wip.activities.note.note;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.services.ImageService;
import com.example.robin.angrynerds_wip.data.services.Read;
import com.example.robin.angrynerds_wip.data.services.Update;

import java.util.ArrayList;
import java.util.List;

class NoteData {
    private NoteActivity mActivity;
    private ArrayList<Image> mImagesToBeDeleted;
    private Note mNote;
    private ArrayList<IContainer> mNoteImageContainers;

    //TODO gegen Note und Images aus Database austauschen
    public NoteData(NoteActivity activity) {

        Log.i("Clicklistener1", "NoteData was called");
        mActivity = activity;
        mNoteImageContainers = new ArrayList<>();
        mNote = new Note();
        mImagesToBeDeleted = new ArrayList<>();
        addImageButton();
    }

    public void setNote(Note note) {
        mNote = note;
    }

    public void imagesToImageContainer() {
        Log.i("Clicklistener1", "imagesToImageContainer was called ");
        mNoteImageContainers = new ArrayList<IContainer>();

        Log.i("ImageButton", "" + mNoteImageContainers.size());
        int i = 1;

        for (Image image : mNote.getPictures()) {
            mNoteImageContainers.add(new ImageContainer(mActivity, i++, image.getBitmap()));
        }
        addImageButton();
    }

    public void addImageContainer(Bitmap bitmap) {
        Log.i("Clicklistener1", "addImageContainer was called");
        ImageContainer imageContainer = new ImageContainer(getActivity(), mNoteImageContainers.size(), bitmap);
        mNoteImageContainers.remove(mNoteImageContainers.size() - 1);
        mNoteImageContainers.add(imageContainer);
        addImageButton();
    }


    //Adds the addImageButton to mNoteContainers

    void addImageButton() {
        Log.i("Clicklistener1", "AddImageButton was called " + mNoteImageContainers.size());
        if (mNoteImageContainers.size() > 0) {
            if (mNoteImageContainers.get(mNoteImageContainers.size() - 1) instanceof IconContainer) {
                mNoteImageContainers.remove(mNoteImageContainers.size() - 1);
            }
        }
        Drawable drawable = ContextCompat.getDrawable(mActivity, R.drawable.ic_add_a_photo_grey_24dp);
        mNoteImageContainers.add(new IconContainer(mActivity, 0, drawable));
    }

    private void updateImageContainer() {

    }

    void saveDataInBundle(Bundle outState) {
        //outState = mNote.getBundle();
    }

    void restoreDataFromBundle(Bundle savedInstanceState) {
    }

    ArrayList<IContainer> getNoteImageContainers() {
        return mNoteImageContainers;
    }

    IContainer getImageContainer(int id) {
        return mNoteImageContainers.get(id);
    }

    NoteActivity getActivity() {
        return mActivity;
    }

    Note getNote() {
        return mNote;
    }

    void setTitle(String title) {
        mNote.setTitle(title);
    }

    void setDescription(String title) {
        mNote.setDescription(title);
    }
    //returns image from mNoteImageContainers

    Bitmap getImage(int id) {
        //TODO get original sized image from database
        return mNote.getPictures().get(id - 1).getBitmap();
    }
    //Checks ImageContainer for specific ID

    boolean checkImageID(int id) {
        for (IContainer mImage : mNoteImageContainers) {
            if (mImage.getImageContainer().getId() == id)
                return true;
        }
        return false;
    }
    //Adds image as ImageContainer to mNoteImageContainers

    void addImageFromCamera(Bitmap image, String formerPath) {
        mNote.addImage(image);
        ImageContainer imageContainer = new ImageContainer(mActivity, mNoteImageContainers.size(), image);
        mNoteImageContainers.add(mNoteImageContainers.size() - 1, imageContainer);
        Log.i("NoDelete", formerPath);
        ImageService.deleteImage(formerPath);
    }

    void addImageFromGallery(Bitmap image) {
        mNote.addImage(image);
        ImageContainer imageContainer = new ImageContainer(mActivity, mNoteImageContainers.size(), image);
        mNoteImageContainers.add(mNoteImageContainers.size() - 1, imageContainer);
    }
    //Deletes specific ImageContainer from mNoteImageContainers

    void deleteImage(int id) {
        Image image = new Image(mNote.getPictures().get(id - 1));
        mNote.getPictures().remove(id - 1);
        mNoteImageContainers.remove(id - 1);
        mImagesToBeDeleted.add(image);
        imagesToImageContainer();
    }

    void finallyDeleteImages() {
        for (Image image : mImagesToBeDeleted) {
            ArrayList<Image> noteImages = mNote.getPictures();

            for (int i = 0; i < noteImages.size(); i++){
                if(noteImages.get(i).getId().equals(image.getId())){
                    noteImages.remove(i);
                }
            }

            ImageService.deleteImage(image);
        }
    }

    public void loadImages(NoteLoader noteLoader) {
        Log.i("NoteRemake", "" + mNote.getPictures().size());
        for (Image image : this.mNote.getPictures()) {
            noteLoader.loadImage(image);
        }
    }

    public void setColors(String noteId) {
        int[] colors = Read.getColors(noteId);
        if (mNote == null) {
            mNote = new Note();
        }
        mNote.setColor(colors[0]);
        mNote.setAccentColor(colors[1]);
    }

    public void saveNoteToDatabase() {
        NoteSaver noteSaver = new NoteSaver();
        noteSaver.saveNote(mNote);

        //Update.saveTEN(mNote);
        Toast.makeText(getActivity().getApplicationContext(), "Konnte nicht gespeichert werden!", Toast.LENGTH_LONG);
    }
}
