package com.example.robin.angrynerds_wip.activities.note.note.data.gui_oriented;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.note.note.data.NoteData;
import com.example.robin.angrynerds_wip.data.models.utils.Image;

import java.util.ArrayList;

public class NoteDataGui {

    private NoteData mNoteData;
    private ArrayList<IContainer> mNoteImageContainers;

    public NoteDataGui(NoteData pNoteData){
        mNoteData = pNoteData;
        mNoteImageContainers = new ArrayList<>();
    }

    public ArrayList<IContainer> getNoteImageContainers() {
        return mNoteImageContainers;
    }

    public void imagesToImageContainer() {
        mNoteImageContainers = new ArrayList<>();

        Log.i("ImageButton", "" + mNoteImageContainers.size());
        int i = 1;

        for (Image image : mNoteData.getNote().getPictures()) {
            mNoteImageContainers.add(new ImageContainer(mNoteData.getActivity(), i++, image));
        }
        addImageButton();
    }

    public void addImageButton() {
        if (mNoteImageContainers.size() > 0) {
            if (mNoteImageContainers.get(mNoteImageContainers.size() - 1) instanceof IconContainer) {
                mNoteImageContainers.remove(mNoteImageContainers.size() - 1);
            }
        }
        Drawable drawable = ContextCompat.getDrawable(mNoteData.getActivity(), R.drawable.ic_add_a_photo_grey_24dp);
        mNoteImageContainers.add(new IconContainer(mNoteData.getActivity(), 0, drawable));
    }

    public IContainer getImageContainer(int pIndex) {
        return mNoteImageContainers.get(pIndex);
    }

    public boolean checkImageListForId(int pId) {
        for (IContainer mImage : mNoteImageContainers) {
            if (mImage.getImageContainer().getId() == pId)
                return true;
        }
        return false;
    }

    public void addImageFromGallery(Bitmap pImage) {
        mNoteData.getNote().addImage(pImage);
        imagesToImageContainer();
    }

    public void addImageContainer(Image pImage) {
        int newImageContainerID = mNoteImageContainers.size();
        ImageContainer imageContainer = new ImageContainer(mNoteData.getActivity(), newImageContainerID, pImage);
        mNoteImageContainers.add(mNoteImageContainers.size() - 1, imageContainer);
    }
}