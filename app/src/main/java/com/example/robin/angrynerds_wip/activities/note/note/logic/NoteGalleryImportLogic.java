package com.example.robin.angrynerds_wip.activities.note.note.logic;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.example.robin.angrynerds_wip.data.services.ImageRotationCorrectionService;
import com.example.robin.angrynerds_wip.data.services.ImageService;
import com.example.robin.angrynerds_wip.modules.image_compression.ImageCompressionModule;

import java.io.IOException;

public class NoteGalleryImportLogic {

    private NoteApplicationLogic mNoteApplicationLogic;

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
        String path = getPath(mNoteApplicationLogic.getNoteData().getActivity().getApplicationContext() ,selectedImage);
            Bitmap bitmap = ImageCompressionModule.importCompressedImage(path);
            bitmap = ImageRotationCorrectionService.correctImageRotation(path, bitmap);

            mNoteApplicationLogic.getNoteData().addImageFromGallery(bitmap);

            mNoteApplicationLogic.getNoteGuiRefresherLogic().refreshImages();
    }
}
