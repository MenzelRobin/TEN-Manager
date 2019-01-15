package com.example.robin.angrynerds_wip.activities.note.note.data;

import android.graphics.Bitmap;

public class PreviewImageCreator {



    public PreviewImageCreator(){}

    public Bitmap getPreviewImage(Bitmap originalBitmap){
        //Crop image to square
        if (originalBitmap.getWidth() > originalBitmap.getHeight()) {
            originalBitmap = Bitmap.createBitmap(
                    originalBitmap,
                    originalBitmap.getWidth() / 2 - originalBitmap.getHeight() / 2,
                    0,
                    originalBitmap.getHeight(),
                    originalBitmap.getHeight()
            );

        } else if (originalBitmap.getWidth() < originalBitmap.getHeight()) {
            originalBitmap = Bitmap.createBitmap(
                    originalBitmap,
                    0,
                    originalBitmap.getHeight() / 2 - originalBitmap.getWidth() / 2,
                    originalBitmap.getWidth(),
                    originalBitmap.getWidth()
            );
        }

        //scale image to preset
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, NoteConstants.IMAGE_PREVIEW_CONTAINER_WIDTH - 50, NoteConstants.IMAGE_PREVIEW_CONTAINER_HEIGHT - 50, true);
        return scaledBitmap;
    }

}
