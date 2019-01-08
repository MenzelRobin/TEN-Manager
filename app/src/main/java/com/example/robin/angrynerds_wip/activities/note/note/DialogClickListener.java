package com.example.robin.angrynerds_wip.activities.note.note;

import android.content.DialogInterface;

class DialogClickListener implements DialogInterface.OnClickListener{

    private ImageImport imageImport;

    DialogClickListener(ImageImport imageImport) {
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
