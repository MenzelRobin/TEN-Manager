package com.example.robin.angrynerds_wip.activities.note.note.logic.listener_watcher;

import android.content.DialogInterface;

import com.example.robin.angrynerds_wip.activities.note.note.logic.NoteImageImportLogic;

// Authored by Joscha Nassenstein
public class DialogClickListener implements DialogInterface.OnClickListener{

    private NoteImageImportLogic mImageImport;

    public DialogClickListener(NoteImageImportLogic pImageImport) {
        this.mImageImport = pImageImport;
    }

    @Override
    public void onClick(DialogInterface pDialog, int pOption) {

        //Checks for image import decision of user
        switch ( pOption ) {
            case 0:
                mImageImport.importImageFromGallery();
                break;
            case 1:
                mImageImport.importImageFromCamera();
                break;
            case 2: mImageImport.importImageFromWeb();
            default:
                break;
        }
    }
}
