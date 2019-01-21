package com.example.robin.angrynerds_wip.data.repository.database;

import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.MutableDocument;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.repository.RepositoryConstants;
import com.example.robin.angrynerds_wip.data.repository.converter.ImageConverter;
import com.example.robin.angrynerds_wip.data.repository.filesystem.FileManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class DocumentSaver {

    ObjectMapper objectMapper;
    ImageConverter imageConverter;
    FileManager fileManager;

    public DocumentSaver() {
        this.objectMapper = new ObjectMapper();
        this.imageConverter = new ImageConverter();
        this.fileManager = new FileManager();
    }

    public boolean updateCompleteDocument(TEN ten, MutableDocument mutableTENDocument) {
        mutableTENDocument = prepareDocument(mutableTENDocument, ten);

        mutableTENDocument.setDate(RepositoryConstants.CREATION_DATE_KEY, ten.getDateOfCreation());
        mutableTENDocument.setInt(RepositoryConstants.COLOR_KEY, ten.getColor());
        mutableTENDocument.setInt(RepositoryConstants.ACCENT_COLOR_KEY, ten.getAccentColor());

        try {

            mutableTENDocument.setString(RepositoryConstants.OBJECT_KEY, this.objectMapper.writeValueAsString(ten));
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

    private MutableDocument prepareDocument(MutableDocument mutableDocument, TEN ten) {

        if (ten.getClass().getName().contains("Event")) {
            mutableDocument.setString(RepositoryConstants.TYPE_KEY, RepositoryConstants.EVENT_TYPE);

        } else if (ten.getClass().getName().contains("Note")) {
            mutableDocument.setString(RepositoryConstants.TYPE_KEY, RepositoryConstants.NOTE_TYPE);
            mutableDocument = saveNoteImages(mutableDocument, (Note) ten);

        } else if (ten.getClass().getName().contains("Todo")) {
            mutableDocument.setString(RepositoryConstants.TYPE_KEY, RepositoryConstants.TODO_TYPE);
        }
        return mutableDocument;
    }

    private MutableDocument saveNoteImages(MutableDocument mutableDocument, Note note) {


        for(Image image: note.getPictures()){
            image.setId(image.getId().replaceAll("null", note.getID()));
            try{
                fileManager.saveImageToDirectory(image);
            } catch (IOException e){

            }
        }

        //removeImages(mutableDocument);
        //if (note.getPictures() != null) {
        //    int numberOfImages = note.getPictures().size();
        //    mutableDocument.setInt(RepositoryConstants.IMAGE_COUNTER, numberOfImages);
        //    ArrayList<Bitmap> imageBitmaps = note.getPictures();
        //    for (int i = 0; i < numberOfImages; i++) {
        //        Blob imageBlob = this.imageConverter.BitmapToBlob(imageBitmaps.get(i));
        //        mutableDocument.setBlob(RepositoryConstants.IMAGE_CORE_ID + (i + 1), imageBlob);
        //    }
        //}
        return mutableDocument;
    }

    private void removeImages(MutableDocument mutableDocument){
        //int numberOfImages = mutableDocument.getInt(RepositoryConstants.IMAGE_COUNTER);
        //if(numberOfImages > 0){
        //        Log.i("DatabaseTest", "Key: " + mutableDocument.getBlob(RepositoryConstants.IMAGE_CORE_ID + 1).getProperties().get("digest"));
        //    for(String key: mutableDocument.getBlob(RepositoryConstants.IMAGE_CORE_ID + "1").getProperties().keySet()){
        //        Log.i("DatabaseTest", "Key: " + key);
        //    }
        //    try{
        //    DatabaseManager.getDatabase().compact();
        //    } catch (CouchbaseLiteException e){
        //        Log.e("Couchbase", "Could not be compacted");
        //    }
//
        //    Log.i("DatabaseTest", "Keymapping done!");
        //}
        //for(int i = 1; i<=numberOfImages; i++){
        //    mutableDocument.setBlob(RepositoryConstants.IMAGE_CORE_ID + i, null);
        //}
    }//
}
