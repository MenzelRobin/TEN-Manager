package com.example.robin.angrynerds_wip.data.repository.database;

import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.MutableDocument;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.repository.RepositoryConstants;
import com.example.robin.angrynerds_wip.data.repository.filesystem.FileManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class DocumentSaver {

    ObjectMapper mObjectMapper;
    FileManager mFileManager;

    public DocumentSaver() {
        this.mObjectMapper = new ObjectMapper();
        this.mFileManager = new FileManager();
    }

    public boolean updateCompleteDocument(TEN pTen, MutableDocument pMutableTENDocument) {
        pMutableTENDocument = prepareDocument(pMutableTENDocument, pTen);

        pMutableTENDocument.setDate(RepositoryConstants.CREATION_DATE_KEY, pTen.getDateOfCreation());
        pMutableTENDocument.setInt(RepositoryConstants.COLOR_KEY, pTen.getColor());
        pMutableTENDocument.setInt(RepositoryConstants.ACCENT_COLOR_KEY, pTen.getAccentColor());

        try {

            pMutableTENDocument.setString(RepositoryConstants.OBJECT_KEY, this.mObjectMapper.writeValueAsString(pTen));
            try {
                DataContextManager.getDatabase().save(pMutableTENDocument);
                return true;
            } catch (CouchbaseLiteException e) {
                return false;
            }
        } catch (JsonProcessingException e) {
            return false;
        }
    }

    private MutableDocument prepareDocument(MutableDocument pMutableDocument, TEN pTen) {

        if (pTen.getClass().getName().contains("Event")) {
            pMutableDocument.setString(RepositoryConstants.TYPE_KEY, RepositoryConstants.EVENT_TYPE);

        } else if (pTen.getClass().getName().contains("Note")) {
            pMutableDocument.setString(RepositoryConstants.TYPE_KEY, RepositoryConstants.NOTE_TYPE);
            saveNoteImages((Note) pTen);

        } else if (pTen.getClass().getName().contains("Todo")) {
            pMutableDocument.setString(RepositoryConstants.TYPE_KEY, RepositoryConstants.TODO_TYPE);
            Log.e("todo id", pMutableDocument.getId());
        }
        return pMutableDocument;
    }

    private void saveNoteImages(Note pNote) {

        for(Image image: pNote.getPictures()){
            image.setId(image.getId().replaceAll("null", pNote.getID()));
            try{
                mFileManager.saveImageToDirectory(image);
            } catch (IOException e){

            }
        }
    }
}
