package com.example.robin.angrynerds_wip.activities.note.note.logic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.robin.angrynerds_wip.activities.note.note.data.NoteConstants;
import com.example.robin.angrynerds_wip.activities.note.notetags.NoteTagActivity;
import com.example.robin.angrynerds_wip.modules.image.ImageToolsModule;

// Authored by Joscha Nassenstein
public class NoteNavigationLogic {

    NoteApplicationLogic mNoteApplicationLogic;

    public NoteNavigationLogic(NoteApplicationLogic pNoteApplicationLogic) {
        this.mNoteApplicationLogic = pNoteApplicationLogic;
    }

    //Returns to overview activity
    public void returnToOverview() {
        mNoteApplicationLogic.getNoteData().getActivity().finish();
    }

    //Saves Note and returns to overview activity
    public void saveAndReturnToOverview() {
        mNoteApplicationLogic.getNoteData().executeSaveRoutine();
        returnToOverview();
    }


    //Called when activity is returned, e.g. Image Import or NoteTagActivity results
    public void onActivityReturned(int pRequestCode, int pResultCode, Intent pData) {
        ImageToolsModule imageToolsModule = new ImageToolsModule();

        switch (pRequestCode) {
            case NoteConstants.CAMERA_IMPORT_ACTIVITY_REQUESTCODE:
                if (pResultCode == -1) {
                    String path = mNoteApplicationLogic.getImageImport().getCurrentPhotoPath();
                    Bitmap cameraImage = imageToolsModule.correctImageRotation(path, BitmapFactory.decodeFile(path));
                    mNoteApplicationLogic.getNoteData().addImageFromCamera(cameraImage, path);
                    mNoteApplicationLogic.getNoteGuiRefresherLogic().refreshImages();
                }
                break;
            case NoteConstants.GALLERY_IMPORT_ACTIVITY_REQUESTCODE:
                if (pResultCode == -1) {
                    NoteGalleryImportLogic noteGalleryImportLogic = new NoteGalleryImportLogic(mNoteApplicationLogic);
                    noteGalleryImportLogic.importImageFromGallery(pData);
                }
                break;
                
            case NoteConstants.TAGEDITOR_ACTIVITY_REQUESTCODE:
                if (pResultCode == -1) {
                    try {
                        mNoteApplicationLogic.getNoteData().getNote().setTags(pData.getStringArrayListExtra("taglist"));
                        mNoteApplicationLogic.getNoteGui().setNoteTags(mNoteApplicationLogic.getNoteData().getNote().getTags());
                    } catch (NullPointerException e) {
                        Log.e("Error", e.getMessage());
                        mNoteApplicationLogic.getNoteGui().displayToast(mNoteApplicationLogic.getNoteData().getActivity(),"Stichworte konnten nicht übertragen werden");
                    }
                }
        }
    }

    //Starts NoteTagActivity with tagList
    public void startTagActivity() {
        Intent intent = new Intent(mNoteApplicationLogic.getNoteData().getActivity(), NoteTagActivity.class);
        intent.putExtra(NoteConstants.INTENT_ID_TAGLIST, mNoteApplicationLogic.getNoteData().getNote().getTags());
        intent.putExtra(NoteConstants.INTENT_ID_COLOR, mNoteApplicationLogic.getNoteData().getNote().getColor());
        intent.putExtra(NoteConstants.INTENT_ID_ACCENTCOLOR, mNoteApplicationLogic.getNoteData().getNote().getAccentColor());
        mNoteApplicationLogic.getNoteData().getActivity().startActivityForResult(intent, NoteConstants.TAGEDITOR_ACTIVITY_REQUESTCODE);
    }
}
