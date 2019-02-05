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

// Authored by Jan Beilfuss
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
        addPreviewImageFromOriginal(originalImage);
        mNoteData.getNoteDataBackend().saveImage(originalImage);
        refreshImages();
    }

    //Create smaller sized preview image
    public void addPreviewImageFromOriginal(Image pOriginalImage) {
        PreviewImageCreator previewImageCreator = new PreviewImageCreator();
        Image previewImage = new Image(pOriginalImage.getId());
        previewImage.setBitmap(previewImageCreator.getPreviewImage(pOriginalImage.getBitmap()));
        mPreviewImages.add(previewImage);
    }

    public void addImageFromCamera(Bitmap image, String pFormerPath) {
        addImageFromGallery(image);
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

    private void refreshImages(){
        mNoteData.getNoteApplicationLogic().getNoteGuiRefresherLogic().refreshImages();
    }

    public Image getLatestImage() {
        if(mPreviewImages.size()>0){
            return mPreviewImages.get(mPreviewImages.size()-1);
        }
        else return null;
    }
}