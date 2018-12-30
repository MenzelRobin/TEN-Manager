package com.example.robin.angrynerds_wip.activities.note;

import android.content.DialogInterface;

import com.example.robin.angrynerds_wip.R;

public class DialogClickListener implements DialogInterface.OnClickListener{

    Gui mGui;

    DialogClickListener(Gui gui) {
        mGui = gui;
    }

    @Override
    public void onClick(DialogInterface dialog, int option) {

        switch ( option ) {
            case 0:
                mGui.importImageFromCamera();
                break;
            case 1:
                mGui.importImageFromGallery();
                break;
            default:
                break;
        }
    }
}
