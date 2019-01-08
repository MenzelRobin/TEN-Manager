package com.example.robin.angrynerds_wip.data.repository;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.couchbase.lite.Blob;

import java.io.ByteArrayOutputStream;

public class ImageConverter {

    public Blob BitmapToBlob(Bitmap imageBitMap) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imageBitMap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        imageBitMap.recycle();

        Blob imageBlob = new Blob("image/jpeg", byteArray);
        return imageBlob;
    }

    public Bitmap BlobToBitmap(Blob imageBlob) {
        byte[] byteArray = imageBlob.getContent();
        Bitmap imageBitMap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        return imageBitMap;
    }
}
