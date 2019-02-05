package com.example.robin.angrynerds_wip.activities.note.note.gui;

import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.note.note.data.NoteConstants;
import com.example.robin.angrynerds_wip.activities.note.note.logic.NoteApplicationLogic;
import com.example.robin.angrynerds_wip.activities.note.note.logic.listener_watcher.ImageOverlayListener;
import com.example.robin.angrynerds_wip.activities.note.note.logic.listener_watcher.MotionListener;

// Authored by Joscha Nassenstein
public class ImageOverlay {

    private Bitmap mImage;
    private AlertDialog mDialog;
    private int mDisplayWidth;
    private int mDisplayHeight;
    private int mFrameWidth;
    private int mFrameHeight;
    private ImageOverlayListener mImageOverlayListener;
    private NoteApplicationLogic mNoteApplicationLogic;
    private int mImageID;

    public ImageOverlay(Bitmap pImage, int pDisplayWidth, int pDisplayHeight, ImageOverlayListener pImageOverlayListener, NoteApplicationLogic pNoteApplicationLogic, int pId) {
        this.mImage = pImage;
        this.mImageOverlayListener = pImageOverlayListener;
        this.mDisplayWidth = pDisplayWidth;
        this.mDisplayHeight = pDisplayHeight;
        this.mNoteApplicationLogic = pNoteApplicationLogic;
        this.mImageID = pId;
    }

    //Displays the image in an AlertDialog
    public void display() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mNoteApplicationLogic.getNoteData().getActivity(), R.style.CustomDialog);
        builder.setOnCancelListener(mImageOverlayListener);
        mDialog = builder.create();
        LayoutInflater inflater = mNoteApplicationLogic.getNoteData().getActivity().getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.activity_note_imageoverlay, null);
        ImageView imageView = dialogLayout.findViewById(R.id.id_note_imageOverlay_imageContainer);
        imageView.setId(mImageID);
        imageView.setOnTouchListener(new MotionListener(mNoteApplicationLogic));
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if(calculateSize()) {
            imageView.setImageBitmap(Bitmap.createScaledBitmap(mImage, (int) (mFrameWidth * NoteConstants.IMAGE_OVERLAY_FILL_FACTOR),
                    (int) (mFrameHeight * NoteConstants.IMAGE_OVERLAY_FILL_FACTOR), true));
        }
        else
            imageView.setImageBitmap(mImage);
        mDialog.setView(dialogLayout);
        mDialog.show();
    }

    //Calculates size of frame according to display metrics
    private boolean calculateSize() {
        int imageWidth = mImage.getWidth();
        int imageHeight = mImage.getHeight();
        double imageAspectRatio = (double) imageWidth / imageHeight;
        double displayAspectRatio = (double) mDisplayWidth / mDisplayHeight;

        //Checks if image is smaller than display * edgeFactor
        if(imageWidth <= mDisplayWidth *NoteConstants.IMAGE_OVERLAY_FILL_FACTOR && imageHeight <= mDisplayHeight *NoteConstants.IMAGE_OVERLAY_FILL_FACTOR){
            return false;
        }
        //Checks if image is a square
        else if (imageWidth == imageHeight) {
            if (mDisplayHeight > mDisplayWidth) {
                mFrameWidth = mDisplayWidth;
                mFrameHeight = mDisplayWidth;
            } else {
                mFrameWidth = mDisplayHeight;
                mFrameHeight = mDisplayHeight;
            }
        }
        //Checks if image exceeds display in width
        else if (imageAspectRatio > displayAspectRatio) {
            mFrameWidth = mDisplayWidth;
            mFrameHeight = (int) ((double) mFrameWidth / imageAspectRatio);
        }
        //Checks if image exceeds display in height
        else {
            mFrameHeight = mDisplayHeight;
            mFrameWidth = (int) ((double) mFrameHeight * imageAspectRatio);
        }
        return true;
    }

    //returns whether AlertDialog is displayed or not
    public boolean  isDisplayed() {
        return mDialog.isShowing();
    }

    //Rescales AlertDialog on Orientation change
    public void changeOrientation(int pDisplayWidth, int pDisplayHeight) {
        mDialog.dismiss();
        this.mDisplayWidth = pDisplayWidth;
        this.mDisplayHeight = pDisplayHeight;
        display();
    }

    public void dismiss(){
        mDialog.dismiss();
    }
}