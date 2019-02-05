package com.example.robin.angrynerds_wip.activities.note.note.logic;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.robin.angrynerds_wip.modules.image.ImageRotationCorrectionModule;
import com.example.robin.angrynerds_wip.modules.image.ImageCompressionModule;
import com.example.robin.angrynerds_wip.modules.image.ImageToolsModule;

// Authored by Jan Beilfuss
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
        ImageToolsModule imageToolsModule = new ImageToolsModule();

        Uri selectedImage = pData.getData();
        String path = getPath(mNoteApplicationLogic.getNoteData().getActivity().getApplicationContext() ,selectedImage);
            Bitmap bitmap = imageToolsModule.importCompressedImage(path);
            bitmap = imageToolsModule.correctImageRotation(path, bitmap);

            mNoteApplicationLogic.getNoteData().addImageFromGallery(bitmap);

            mNoteApplicationLogic.getNoteGuiRefresherLogic().refreshImages();
    }
}
