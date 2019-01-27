package com.example.robin.angrynerds_wip.activities.note.note.logic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import com.example.robin.angrynerds_wip.activities.note.note.data.NoteConstants;
import com.example.robin.angrynerds_wip.activities.note.note.data.NoteData;
import com.example.robin.angrynerds_wip.activities.note.note.data.gui_oriented.ImageImport;
import com.example.robin.angrynerds_wip.activities.note.tageditor.NoteTagActivity;

import java.io.IOException;

public class NoteNavigationLogic {

    NoteApplicationLogic mNoteApplicationLogic;

    public NoteNavigationLogic(NoteApplicationLogic pNoteApplicationLogic) {
        this.mNoteApplicationLogic = pNoteApplicationLogic;
    }

    public void returnToOverview() {
        //mActivity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.slide_out_right);
        mNoteApplicationLogic.getNoteData().getActivity().finish();
    }

    public void onActivityReturned(int pRequestCode, int pResultCode, Intent pData) {
        switch (pRequestCode) {
            case NoteConstants.CAMERA_IMPORT_ACTIVITY_REQUESTCODE:
                if (pResultCode == -1) {
                    //TODO
                    String path = mNoteApplicationLogic.getImageImport().getCurrentPhotoPath();
                    Bitmap cameraImage = BitmapFactory.decodeFile(path);
                    mNoteApplicationLogic.getNoteData().addImageFromCamera(cameraImage, path);
                    mNoteApplicationLogic.getNoteGuiRefresherLogic().refreshImages();
                }
                break;
            case NoteConstants.GALLERY_IMPORT_ACTIVITY_REQUESTCODE:
                if (pResultCode == -1) {
                    Uri selectedImage = pData.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(mNoteApplicationLogic.getNoteData().getActivity().getContentResolver(), selectedImage);
                        mNoteApplicationLogic.getNoteData().addImageFromGallery(bitmap);

                        mNoteApplicationLogic.getNoteGuiRefresherLogic().refreshImages();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
                
            case NoteConstants.TAGEDITOR_ACTIVITY_REQUESTCODE:
                if (pResultCode == -1) {
                    Bundle extras = pData.getExtras();
                    try {
                        mNoteApplicationLogic.getNoteData().getNote().setTags(extras.getStringArrayList("taglist"));
                        mNoteApplicationLogic.getNoteGui().setNoteTags(mNoteApplicationLogic.getNoteData().getNote().getTags());
                    } catch (NullPointerException e) {
                        Log.e("Error", e.getMessage());
                        //displayToast("Error getting TagList from NoteTagActivity.");
                    }
                }
        }
    }

    //NoteNavigationLogic
    public void saveAndReturnToOverview() {
        mNoteApplicationLogic.getNoteData().executeSaveRoutine();
        returnToOverview();
    }

    //NoteNavigationLogic
    public void onBackPressed() {
        saveAndReturnToOverview();
    }

    public void startImageImport(){
        mNoteApplicationLogic.initImageImportObject();
    }

    //NoteNavigationLogic
    public void startTagActivity() {
        Intent intent = new Intent(mNoteApplicationLogic.getNoteData().getActivity(), NoteTagActivity.class);
        //TODO Als Konstanten irgendwo ablegen
        intent.putExtra("taglist", mNoteApplicationLogic.getNoteData().getNote().getTags());
        intent.putExtra("color", mNoteApplicationLogic.getNoteData().getNote().getColor());
        mNoteApplicationLogic.getNoteData().getActivity().startActivityForResult(intent, NoteConstants.TAGEDITOR_ACTIVITY_REQUESTCODE);
    }
}
