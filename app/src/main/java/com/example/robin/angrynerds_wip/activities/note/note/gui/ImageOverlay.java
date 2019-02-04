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

public class ImageOverlay {

    private Bitmap mImage;
    private AlertDialog dialog;
    private int displayWidth;
    private int displayHeight;
    private int frameWidth;
    private int frameHeight;
    private ImageOverlayListener mImageOverlayListener;
    private NoteApplicationLogic mNoteApplicationLogic;
    private int mImageID;

    public ImageOverlay(Bitmap pImage, int pDisplayWidth, int pDisplayHeight, ImageOverlayListener pImageOverlayListener, NoteApplicationLogic pNoteApplicationLogic, int pId) {
        this.mImage = pImage;
        this.mImageOverlayListener = pImageOverlayListener;
        this.displayWidth = pDisplayWidth;
        this.displayHeight = pDisplayHeight;
        this.mNoteApplicationLogic = pNoteApplicationLogic;
        this.mImageID = pId;
    }

    //Displays the image in an AlertDialog
    public void display() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mNoteApplicationLogic.getNoteData().getActivity(), R.style.CustomDialog);
        builder.setOnCancelListener(mImageOverlayListener);
        dialog = builder.create();
        LayoutInflater inflater = mNoteApplicationLogic.getNoteData().getActivity().getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.activity_note_imageoverlay, null);
        ImageView imageView = dialogLayout.findViewById(R.id.id_note_imageOverlay_imageContainer);
        imageView.setId(mImageID);
        imageView.setOnTouchListener(new MotionListener(mNoteApplicationLogic));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if(calculateSize()) {
            imageView.setImageBitmap(Bitmap.createScaledBitmap(mImage, (int) (frameWidth * NoteConstants.IMAGE_OVERLAY_FILL_FACTOR),
                    (int) (frameHeight * NoteConstants.IMAGE_OVERLAY_FILL_FACTOR), true));
        }
        else
            imageView.setImageBitmap(mImage);
        dialog.setView(dialogLayout);
        dialog.show();
    }

    //Calculates size of frame according to display metrics
    private boolean calculateSize() {
        int imageWidth = mImage.getWidth();
        int imageHeight = mImage.getHeight();
        double imageAspectRatio = (double) imageWidth / imageHeight;
        double displayAspectRatio = (double) displayWidth / displayHeight;

        //Checks if image is smaller than display * edgeFactor
        if(imageWidth <= displayWidth*NoteConstants.IMAGE_OVERLAY_FILL_FACTOR && imageHeight <= displayHeight*NoteConstants.IMAGE_OVERLAY_FILL_FACTOR){
            return false;
        }
        //Checks if image is a square
        else if (imageWidth == imageHeight) {
            if (displayHeight > displayWidth) {
                frameWidth = displayWidth;
                frameHeight = displayWidth;
            } else {
                frameWidth = displayHeight;
                frameHeight = displayHeight;
            }
        }
        //Checks if image exceeds display in width
        else if (imageAspectRatio > displayAspectRatio) {
            frameWidth = displayWidth;
            frameHeight = (int) ((double) frameWidth / imageAspectRatio);
        }
        //Checks if image exceeds display in height
        else {
            frameHeight = displayHeight;
            frameWidth = (int) ((double) frameHeight * imageAspectRatio);
        }
        return true;
    }

    //returns whether AlertDialog is displayed or not
    public boolean  isDisplayed() {
        return dialog.isShowing();
    }

    //Rescales AlertDialog on Orientation change
    public void changeOrientation(int pDisplayWidth, int pDisplayHeight) {
        dialog.dismiss();
        this.displayWidth = pDisplayWidth;
        this.displayHeight = pDisplayHeight;
        display();
    }

    public void dismiss(){
        dialog.dismiss();
    }
}