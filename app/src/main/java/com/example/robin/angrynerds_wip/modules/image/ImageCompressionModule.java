package com.example.robin.angrynerds_wip.modules.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

//Compresses Images that are too big for the RAM of the Tablet
// Author: Jan Beilfuß
public class ImageCompressionModule {

    //Imports Image from File, with Sizecheck
    public Bitmap importCompressedImage(String pPath){
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(pPath, options);

            // Calculate inSampleSize
            options.inSampleSize = calculateInSampleSize(options, 1000, 1000);

            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeFile(pPath, options);
    }

    //calculates factor that scales the image down
    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
