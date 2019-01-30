package com.example.robin.angrynerds_wip.activities.note.note.logic;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.example.robin.angrynerds_wip.data.services.ImageService;

import java.io.IOException;

public class NoteGalleryImportLogic {
    NoteApplicationLogic mNoteApplicationLogic;

    public NoteGalleryImportLogic(NoteApplicationLogic pNoteApplicationLogic) {
        this.mNoteApplicationLogic = pNoteApplicationLogic;
    }

    private String getPath(Context context, Uri uri ) {
        String result = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver( ).query( uri, proj, null, null, null );
        if(cursor != null){
            if ( cursor.moveToFirst( ) ) {
                int column_index = cursor.getColumnIndexOrThrow( proj[0] );
                result = cursor.getString( column_index );
            }
            cursor.close( );
        }
        if(result == null) {
            result = "Not found";
        }
        return result;
    }

    public void importImageFromGallery(Intent pData){
        Uri selectedImage = pData.getData();
        try {
            Log.d("Kamera", "Pfad: " +selectedImage.getPath());
            //Bitmap bitmap = MediaStore.Images.Media.getBitmap(mNoteApplicationLogic.getNoteData().getActivity().getContentResolver(), selectedImage);
            Bitmap bitmap = ImageService.correctImageRotation(getPath(mNoteApplicationLogic.getNoteData().getActivity().getApplicationContext() ,selectedImage), MediaStore.Images.Media.getBitmap(mNoteApplicationLogic.getNoteData().getActivity().getContentResolver(), selectedImage));

            mNoteApplicationLogic.getNoteData().addImageFromGallery(bitmap);

            mNoteApplicationLogic.getNoteGuiRefresherLogic().refreshImages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
