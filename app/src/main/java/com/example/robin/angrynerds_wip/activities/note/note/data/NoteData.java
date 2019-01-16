package com.example.robin.angrynerds_wip.activities.note.note.data;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.note.note.NoteActivity;
import com.example.robin.angrynerds_wip.activities.note.note.logic.NoteApplicationLogic;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.services.ImageService;
import com.example.robin.angrynerds_wip.data.services.Read;
import com.example.robin.angrynerds_wip.data.services.Update;

import java.util.ArrayList;

public class NoteData {

    private NoteApplicationLogic mNoteApplicationLogic;
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
            mNoteImageContainers.add(new ImageContainer(mActivity, i++, image));
        }
        addImageButton();
    }

    public void setmNoteApplicationLogic(NoteApplicationLogic mNoteApplicationLogic){
        this.mNoteApplicationLogic = mNoteApplicationLogic;
    }

    public NoteApplicationLogic getmNoteApplicationLogic() {
        return mNoteApplicationLogic;
    }
    //Adds the addImageButton to mNoteContainers

    public void addImageButton() {
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

    public void saveDataInBundle(Bundle outState) {
        //outState = mNote.getBundle();
    }

    public void restoreDataFromBundle(Bundle savedInstanceState) {
    }

    public ArrayList<IContainer> getNoteImageContainers() {
        return mNoteImageContainers;
    }

    public IContainer getImageContainer(int id) {
        return mNoteImageContainers.get(id);
    }

    public NoteActivity getActivity() {
        return mActivity;
    }

    public Note getNote() {
        return mNote;
    }

    public void setTitle(String title) {
        mNote.setTitle(title);
    }

    public void setDescription(String title) {
        mNote.setDescription(title);
    }
    //returns image from mNoteImageContainers

    public Bitmap getImage(int id) {
        //TODO get original sized image from database
        int index = id-1;
        Bitmap bitmap = mNote.getPictures().get(index).getBitmap();
        if(bitmap == null){
            OriginalImageLoader originalImageLoader = new OriginalImageLoader(this, true);
            originalImageLoader.loadOriginalImage(index);
        }

        return bitmap;
    }
    //Checks ImageContainer for specific ID

    public boolean checkImageID(int id) {
        for (IContainer mImage : mNoteImageContainers) {
            if (mImage.getImageContainer().getId() == id)
                return true;
        }
        return false;
    }
    //Adds image as ImageContainer to mNoteImageContainers

    public void addImageFromCamera(Bitmap image, String formerPath) {
        mNote.addImage(image);
        imagesToImageContainer();
        // ImageContainer imageContainer = new ImageContainer(mActivity, mNoteImageContainers.size(), image);
        // mNoteImageContainers.add(mNoteImageContainers.size() - 1, imageContainer);
        Log.i("NoDelete", formerPath);
        ImageService.deleteImage(formerPath);
    }

    public void addImageFromGallery(Bitmap image) {
        mNote.addImage(image);
        imagesToImageContainer();
    }
    //Deletes specific ImageContainer from mNoteImageContainers

    public void deleteImage(int id) {
        Image image = new Image(mNote.getPictures().get(id - 1));
        mNote.getPictures().remove(id - 1);
        mImagesToBeDeleted.add(image);
        imagesToImageContainer();
    }

    public void finallyDeleteImages() {
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
            noteLoader.loadPreviewImage(image);
        }
        OriginalImageLoader originalImageLoader = new OriginalImageLoader(this, false);
        originalImageLoader.loadOriginalImage();
    }

    public void setColors(String noteId) {
        int[] colors = Read.getColors(noteId);
        if (mNote == null) {
            mNote = new Note();
        }
        mNote.setColor(colors[0]);
        mNote.setAccentColor(colors[1]);
    }

    public void executeSaveRoutine() {

        Update.saveTEN(this.mNote);
        for(IContainer iContainer: this.mNoteImageContainers){

        }


        //Update.saveTEN(mNote);
        Toast.makeText(getActivity().getApplicationContext(), "Konnte nicht gespeichert werden!", Toast.LENGTH_LONG);
    }

    public void addImageContainer(Image image) {
        int newImageContainerID = mNoteImageContainers.size();
        ImageContainer imageContainer = new ImageContainer(getActivity(), newImageContainerID, image);
        mNoteImageContainers.add(mNoteImageContainers.size()-1, imageContainer);
    }

}
