package com.example.robin.angrynerds_wip.data.repository;

import android.graphics.Bitmap;

import com.couchbase.lite.Blob;
import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.MutableDocument;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class DocumentSaver {

    ObjectMapper objectMapper;
    ImageConverter imageConverter;

    public DocumentSaver(){
        this.objectMapper = new ObjectMapper();
        this.imageConverter = new ImageConverter();
    }

    public boolean updateCompleteDocument(TEN ten, MutableDocument mutableTENDocument){
        mutableTENDocument = prepareDocument(mutableTENDocument, ten);
        mutableTENDocument.setLong(DatabaseConstants.CREATION_DATE_KEY, ten.getDateOfCreation().getTime());
        try {

            mutableTENDocument.setString(DatabaseConstants.OBJECT_KEY, this.objectMapper.writeValueAsString(ten));
            try {
                DatabaseManager.getDatabase().save(mutableTENDocument);
                return true;
            } catch (CouchbaseLiteException e) {
                return false;
            }
        } catch (JsonProcessingException e) {
            return false;
        }
    }

    private MutableDocument prepareDocument(MutableDocument mutableDocument, TEN ten){
        if(ten.getClass().getName().contains("Event")){
            mutableDocument.setString(DatabaseConstants.TYPE_KEY, DatabaseConstants.EVENT_TYPE);
        }
        else if (ten.getClass().getName().contains("Note")){
            mutableDocument.setString(DatabaseConstants.TYPE_KEY, DatabaseConstants.NOTE_TYPE);
            mutableDocument = saveNoteImages(mutableDocument, (Note) ten);
            ((Note) ten).setPictures(null);
        }
        else if (ten.getClass().getName().contains("Todo")){
            mutableDocument.setString(DatabaseConstants.TYPE_KEY, DatabaseConstants.TODO_TYPE);
        }
        return mutableDocument;
    }

    private MutableDocument saveNoteImages(MutableDocument mutableDocument, Note note){

        int numberOfImages = note.getPictures().size();
        mutableDocument.setInt(DatabaseConstants.IMAGE_COUNTER, numberOfImages);
        ArrayList<Bitmap> imageBitmaps = note.getPictures();
        for(int i=0; i<numberOfImages; i++){
            Blob imageBlob = this.imageConverter.BitmapToBlob(imageBitmaps.get(i));
            mutableDocument.setBlob(DatabaseConstants.IMAGE_CORE_ID + (i+1), imageBlob);
        }


        //TODO for-schleife, die die Bilder abspeichert Beginnend bei 1
        return mutableDocument;
    }
}
