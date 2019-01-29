package com.example.robin.angrynerds_wip.activities.note.note.data.gui_oriented;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.note.note.data.NoteData;
import com.example.robin.angrynerds_wip.activities.note.note.gui.GraphicsContainer;
import com.example.robin.angrynerds_wip.activities.note.note.gui.IconContainer;
import com.example.robin.angrynerds_wip.activities.note.note.gui.ImageContainer;
import com.example.robin.angrynerds_wip.data.models.utils.Image;

import java.util.ArrayList;

public class NoteDataGui {

    private NoteData mNoteData;
    private ArrayList<Image> mPreviewImages;

    public NoteDataGui(NoteData pNoteData) {
        mNoteData = pNoteData;
        mPreviewImages = new ArrayList<>();
    }

    public ArrayList<Image> getPreviewImages() {
        return this.mPreviewImages;
    }

    public void addImageFromGallery(Bitmap pImage) {
        Image originalImage = mNoteData.getNote().addImage(pImage);
        Log.i("cool", originalImage.getId());
        addPreviewImageFromOriginal(originalImage);
        refreshImages();
        mNoteData.getNoteDataBackend().saveImage(originalImage);
    }

    public void addPreviewImageFromOriginal(Image pOriginalImage) {
        PreviewImageCreator previewImageCreator = new PreviewImageCreator();
        Image previewImage = new Image(pOriginalImage.getId());
        previewImage.setBitmap(previewImageCreator.getPreviewImage(pOriginalImage.getBitmap()));
        mPreviewImages.add(previewImage);
    }

    public void addImageFromCamera(Bitmap image, String pFormerPath) {
        addImageFromGallery(image);
        refreshImages();
        mNoteData.getNoteDataBackend().deleteImageFromDisk(pFormerPath);
    }

    public Bitmap getOriginalImage(int pId) {
        int index = pId - 1;
        Bitmap bitmap = mNoteData.getNote().getPictures().get(index).getBitmap();
        if (bitmap == null)
            mNoteData.getNoteDataBackend().triggerOriginalImageLoad(index);
        return bitmap;
    }

    public void deleteImage(int id) {
        mPreviewImages.remove(id - 1);
        refreshImages();
    }

    public void refreshImages(){
        mNoteData.getNoteApplicationLogic().getNoteGuiRefresherLogic().refreshImages();
    }
    //
    //
    //
    //
    // public ArrayList<GraphicsContainer> getNoteImageContainers() {
    //     return mNoteImageContainers;
    // }
//
    // public void imagesToImageContainer() {
    //     mNoteImageContainers = new ArrayList<>();
    //     int i = 1;
    //     for (Image image : mNoteData.getNote().getPictures()){
    //         Log.d("Kamera", "itic: " + image.getId());
    //         mNoteImageContainers.add(new ImageContainer(mNoteData.getActivity(), i++, image));
    //     }
    //     addImageButton();
    // }
//
    // public void addImageButton() {
    //     if (mNoteImageContainers.size() > 0) {
    //         if (mNoteImageContainers.get(mNoteImageContainers.size() - 1) instanceof IconContainer)
    //             mNoteImageContainers.remove(mNoteImageContainers.size() - 1);
    //     }
    //     Drawable drawable = ContextCompat.getDrawable(mNoteData.getActivity(), R.drawable.ic_add_a_photo_grey_24dp);
    //     mNoteImageContainers.add(new IconContainer(mNoteData.getActivity(), 0, drawable));
    // }
//
    // public boolean checkImageListForId(int pId) {
    //     for (GraphicsContainer mImage : mNoteImageContainers) {
    //         if (mImage.getImageContainer().getId() == pId)
    //             return true;
    //     }
    //     return false;
    // }
//
    // public void addImageFromGallery(Bitmap pImage) {
    //     Log.d("Kamera", "" + pImage);
    //     Image originalImage = mNoteData.getNote().addImage(pImage);
//
    //     Log.d("Kamera", "added Image To note" + pImage);
    //     Log.d("Kamera", "id: " + originalImage.getId());
    //     Log.d("Kamera", "bm: " + originalImage.getBitmap());
    //     imagesToImageContainer();
    //     mNoteData.getNoteDataBackend().saveImage(originalImage);
    // }
//
    // public void addImageFromCamera(Bitmap image, String pFormerPath) {
    //     addImageFromGallery(image);
    //     mNoteData.getNoteDataBackend().deleteImageFromDisk(pFormerPath);
    // }
//
    // public void addImageContainer(Image pImage) {
    //     int newImageContainerID = mNoteImageContainers.size();
    //     ImageContainer imageContainer = new ImageContainer(mNoteData.getActivity(), newImageContainerID, pImage);
    //     mNoteImageContainers.add(mNoteImageContainers.size() - 1, imageContainer);
    // }
//
    // public Bitmap getOriginalImage(int pId) {
    //     int index = pId - 1;
    //     Bitmap bitmap = mNoteData.getNote().getPictures().get(index).getBitmap();
    //     if (bitmap == null)
    //         mNoteData.getNoteDataBackend().triggerOriginalImageLoad(index);
    //     return bitmap;
    // }
}