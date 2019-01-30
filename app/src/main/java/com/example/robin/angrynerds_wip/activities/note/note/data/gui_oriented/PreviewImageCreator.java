package com.example.robin.angrynerds_wip.activities.note.note.data.gui_oriented;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.robin.angrynerds_wip.activities.note.note.data.NoteConstants;

public class PreviewImageCreator {

    public PreviewImageCreator(){}

    public Bitmap getPreviewImage(Bitmap pOriginalBitmap){
        //Crop image to square
        if (pOriginalBitmap.getWidth() > pOriginalBitmap.getHeight()) {
            pOriginalBitmap = Bitmap.createBitmap(
                    pOriginalBitmap,
                    pOriginalBitmap.getWidth() / 2 - pOriginalBitmap.getHeight() / 2,
                    0,
                    pOriginalBitmap.getHeight(),
                    pOriginalBitmap.getHeight()
            );

        } else if (pOriginalBitmap.getWidth() < pOriginalBitmap.getHeight()) {
            pOriginalBitmap = Bitmap.createBitmap(
                    pOriginalBitmap,
                    0,
                    pOriginalBitmap.getHeight() / 2 - pOriginalBitmap.getWidth() / 2,
                    pOriginalBitmap.getWidth(),
                    pOriginalBitmap.getWidth()
            );
        }

        //scale image to preset
        return Bitmap.createScaledBitmap(pOriginalBitmap, NoteConstants.IMAGE_PREVIEW_CONTAINER_WIDTH - 50,
                NoteConstants.IMAGE_PREVIEW_CONTAINER_HEIGHT - 50, true);
    }

}
