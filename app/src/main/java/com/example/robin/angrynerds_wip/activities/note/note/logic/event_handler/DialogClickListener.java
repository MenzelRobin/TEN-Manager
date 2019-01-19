package com.example.robin.angrynerds_wip.activities.note.note.logic.event_handler;

import android.content.DialogInterface;

import com.example.robin.angrynerds_wip.activities.note.note.data.gui_oriented.ImageImport;

public class DialogClickListener implements DialogInterface.OnClickListener{

    private ImageImport imageImport;

    public DialogClickListener(ImageImport imageImport) {
        this.imageImport = imageImport;
    }

    @Override
    public void onClick(DialogInterface dialog, int option) {

        switch ( option ) {
            case 0:
                imageImport.importImageFromGallery();
                break;
            case 1:
                imageImport.importImageFromCamera();
                break;
            default:
                break;
        }
    }
}
