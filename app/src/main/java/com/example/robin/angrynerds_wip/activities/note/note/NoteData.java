package com.example.robin.angrynerds_wip.activities.note.note;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.repository.DatabaseConstants;
import com.example.robin.angrynerds_wip.data.services.Read;
import com.example.robin.angrynerds_wip.data.services.Update;

import java.util.ArrayList;

class NoteData {
    private NoteActivity mActivity;

    private Note mNote;
    private ArrayList<IContainer> mNoteImageContainers;
    private int loadingProgress;

    //TODO gegen Note und Images aus Database austauschen
    public NoteData(NoteActivity activity) {
        mActivity = activity;
        mNoteImageContainers = new ArrayList<>();
        mNote = new Note();


        //

        // int i = 1;
        // for (Bitmap bm : mNote.getPictures()) {
        //     mNoteImageContainers.add(new ImageContainer(mActivity, i++, bm));
        // }
        //mNoteImageContainers.add(new ImageContainer(mActivity,1, BitmapFactory.decodeResource(mActivity.getResources(), R.drawable.note_sample_image1)));
        //mNoteImageContainers.add(new ImageContainer(mActivity,2, BitmapFactory.decodeResource(mActivity.getResources(), R.drawable.note_sample_image2)));
        //mNoteImageContainers.add(new ImageContainer(mActivity,3, BitmapFactory.decodeResource(mActivity.getResources(), R.drawable.note_sample_image3)));
        addImageButton();
    }

    public void setNote(Note note) {
        mNote = note;
    }

    public void imagesToImageContainer(){

        mNoteImageContainers = new ArrayList<IContainer>();

        Log.i("ImageButton", "" + mNoteImageContainers.size());
        int i = 1;

         for (Bitmap bm : mNote.getPictures()) {
             mNoteImageContainers.add(new ImageContainer(mActivity, i++, bm));
         }
         addImageButton();
    }

    public void addImageContainer(Bitmap bitmap){
        ImageContainer imageContainer = new ImageContainer(getActivity(), mNoteImageContainers.size(), bitmap);
        mNoteImageContainers.remove(mNoteImageContainers.size()-1);
        mNoteImageContainers.add(imageContainer);
        addImageButton();
    }


    //Adds the addImageButton to mNoteContainers

    void addImageButton() {
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
        return mNoteImageContainers.get(id - 1).getImage();
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

    void saveImage(Bitmap image) {
        ImageContainer imageContainer = new ImageContainer(mActivity, mNoteImageContainers.size(), image);
        mNoteImageContainers.add(mNoteImageContainers.size() - 1, imageContainer);
    }
    //Deletes specific ImageContainer from mNoteImageContainers

    void deleteImage(int id) {
        mNote.getPictures().remove(id - 1);
        imagesToImageContainer();
    }

    public void loadImages(String noteId, NoteLoader noteLoader) {
        int numberOfImages = Read.getNumberOfImages(noteId);
        Log.i("Testdata", "Anzahl an Bildern die geladen werden sollen: " + numberOfImages);
        for (int i = 1; i <= numberOfImages; i++) {
            String imageId = DatabaseConstants.IMAGE_CORE_ID + i;
            noteLoader.loadImage(noteId, imageId);
        }
    }

    public void setColors(String noteId) {
        int[] colors = Read.getColors(noteId);
        if(mNote == null){
            mNote = new Note();
        }
        mNote.setColor(colors[0]);
        mNote.setAccentColor(colors[1]);
    }

    public void saveNoteToDatabase(){
        if(this.loadingProgress == Read.getNumberOfImages(mNote.getID())+1){
            NoteSaver noteSaver = new NoteSaver();
            noteSaver.saveNote(mNote);
        } else Toast.makeText(getActivity().getApplicationContext(), "Konnte nicht gespeichert werden!", Toast.LENGTH_LONG);

    }

    public void incrementLoadingProgess() {
        this.loadingProgress++;

    }

}
