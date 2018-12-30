package com.example.robin.angrynerds_wip.activities.note;

import android.content.DialogInterface;

class DialogClickListener implements DialogInterface.OnClickListener{

    MediaImport mediaImport;

    DialogClickListener(MediaImport mediaImport) {
        this.mediaImport = mediaImport;
    }

    @Override
    public void onClick(DialogInterface dialog, int option) {

        switch ( option ) {
            case 0:
                mediaImport.importImageFromCamera();
                break;
            case 1:
                mediaImport.importImageFromGallery();
                break;
            default:
                break;
        }
    }
}
