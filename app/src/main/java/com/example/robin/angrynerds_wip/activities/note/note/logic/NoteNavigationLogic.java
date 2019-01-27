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
import com.example.robin.angrynerds_wip.activities.note.tageditor.NoteTagActivity;

import java.io.IOException;

public class NoteNavigationLogic {

    NoteApplicationLogic mNoteApplicationLogic;
    NoteData mNoteData;

    public NoteNavigationLogic(NoteApplicationLogic pNoteApplicationLogic) {
        this.mNoteApplicationLogic = pNoteApplicationLogic;
        this.mNoteData = pNoteApplicationLogic.getNoteData();
    }

    public void returnToOverview() {
        mNoteData.getActivity().finish();
    }

    public void onActivityReturned(int pRequestCode, int pResultCode, Intent pData) {
        switch (pRequestCode) {
            case NoteConstants.CAMERA_IMPORT_ACTIVITY_REQUESTCODE:
                if (pResultCode == -1) {
                    //TODO
                    String path = mImageImport.getCurrentPhotoPath();
                    Bitmap cameraImage = BitmapFactory.decodeFile(path);
                    mNoteData.addImageFromCamera(cameraImage, path);
                    refreshImages();
                }
                break;
            case NoteConstants.GALLERY_IMPORT_ACTIVITY_REQUESTCODE:
                if (pResultCode == -1) {
                    Uri selectedImage = pData.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(mNoteData.getActivity().getContentResolver(), selectedImage);
                        mNoteData.addImageFromGallery(bitmap);

                        refreshImages();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case NoteConstants.TAGEDITOR_ACTIVITY_REQUESTCODE:
                if (pResultCode == -1) {
                    Bundle extras = pData.getExtras();
                    try {
                        mNoteData.getNote().setTags(extras.getStringArrayList("taglist"));
                        mNoteGui.setNoteTags(mNoteData.getNote().getTags());
                    } catch (NullPointerException e) {
                        Log.e("Error", e.getMessage());
                        displayToast("Error getting TagList from NoteTagActivity.");
                    }
                }
        }
    }

    //NoteNavigationLogic
    public void saveAndReturnToOverview() {
        mNoteData.executeSaveRoutine();
        returnToOverview();
    }

    //NoteNavigationLogic
    public void onBackPressed() {
        saveAndReturnToOverview();
    }

    //NoteNavigationLogic
    public void onTagsClicked() {
        Intent intent = new Intent(mNoteData.getActivity(), NoteTagActivity.class);
        //TODO Als Konstanten irgendwo ablegen
        intent.putExtra("taglist", mNoteData.getNote().getTags());
        intent.putExtra("color", mNoteData.getNote().getColor());
        mNoteData.getActivity().startActivityForResult(intent, NoteConstants.TAGEDITOR_ACTIVITY_REQUESTCODE);
    }
}
