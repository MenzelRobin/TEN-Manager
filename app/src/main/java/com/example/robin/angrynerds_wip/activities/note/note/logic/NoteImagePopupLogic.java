package com.example.robin.angrynerds_wip.activities.note.note.logic;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.example.robin.angrynerds_wip.activities.note.note.gui.ImageOverlay;
import com.example.robin.angrynerds_wip.activities.note.note.logic.listener_watcher.ImageOverlayListener;

// Authored by Jan Beilfuss and Joscha Nassenstein
public class NoteImagePopupLogic {

    NoteApplicationLogic mNoteApplicationLogic;
    ImageOverlay mImageOverlay;
    ImageOverlayListener mImageOverlayListener;

    public NoteImagePopupLogic(NoteApplicationLogic pNoteApplicationLogic) {
        this.mNoteApplicationLogic = pNoteApplicationLogic;
        this.mImageOverlayListener = new ImageOverlayListener(pNoteApplicationLogic);
    }

    // Authored by Joscha Nassenstein
    public void openImagePopup(Bitmap pBitmap, int pId) {
        if (mNoteApplicationLogic.getNoteData().getActivity().isActive()) {
            View displayMetrics = mNoteApplicationLogic.getNoteData().getActivity().getWindow().findViewById(Window.ID_ANDROID_CONTENT);
            mImageOverlay = new ImageOverlay(pBitmap, displayMetrics.getWidth(), displayMetrics.getHeight(), mImageOverlayListener, mNoteApplicationLogic, pId);
            mImageOverlay.display();
        }
    }

    // Authored by Jan Beilfuss
    //Called when configuration changes and ImageOverlay is displayed
    public void changeConfiguration(Configuration pConfiguration){
        float dpRatio = this.mNoteApplicationLogic
                .getNoteData()
                .getActivity()
                .getApplicationContext()
                .getResources()
                .getDisplayMetrics()
                .density;
        int displayHeight = (int)(pConfiguration.screenHeightDp*dpRatio)+1;
        int displayWidth = (int)(pConfiguration.screenWidthDp*dpRatio)+1;

        if (mImageOverlay != null && mImageOverlay.isDisplayed()) {
            mImageOverlay.changeOrientation(displayWidth, displayHeight);
        }
    }

    public void closePopup() {
        mImageOverlay.dismiss();
        resetImageOverlay();
    }

    public void resetImageOverlay(){
        this.mImageOverlay = null;
    }

    //Displays the next image if feasible
    public void displayNextImage(int pCurrentImageId){
        if(++pCurrentImageId <= mNoteApplicationLogic.getNoteData().getNotePreviewImages().size())
        {
            displayNewImage(pCurrentImageId);
        }
    }

    //Displays the previous image if feasible
    public void displayPreviousImage(int pCurrentImageId){
        if(--pCurrentImageId >= 1){
            displayNewImage(pCurrentImageId);
        }
    }

    private void displayNewImage(int pCurrentImageId) {
        closePopup();
        mNoteApplicationLogic.getNoteData().resetNoteBitmaps();
        mNoteApplicationLogic.getNoteClickHandler().onImageClicked(pCurrentImageId);
    }
}
