package com.example.robin.angrynerds_wip.data.repository;

import android.graphics.Bitmap;
import android.media.Image;
import android.util.Log;

import com.couchbase.lite.Blob;
import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Document;
import com.couchbase.lite.MutableDocument;
import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    TENConverter tenConverter;
    DocumentSaver documentSaver;
    Queries queries;
    ImageConverter imageConverter;

    public Repository() {
        this.tenConverter = new TENConverter();
        this.documentSaver = new DocumentSaver();
        this.queries = new Queries();
    }

    //TODO Jan: jeweiliges Objekt mit übergebener ID zurückgeben (wenn kein Objekt mit ID dann return null)
    public Todo getTodoByID(String id) {

        Document todoDocument = DatabaseManager.getDatabase().getDocument(id);
        String json = todoDocument.getString(DatabaseConstants.OBJECT_KEY);
        Todo finalTodo = this.tenConverter.stringToTodo(json);
        return finalTodo;
    }

    public Event getEventByID(String id) {
        Document eventDocument = DatabaseManager.getDatabase().getDocument(id);
        String json = eventDocument.getString(DatabaseConstants.OBJECT_KEY);
        Event finalEvent = this.tenConverter.stringToEvent(json);
        return finalEvent;
    }

    public Note getNoteByID(String id) {
        Document noteDocument = DatabaseManager.getDatabase().getDocument(id);
        String json = noteDocument.getString(DatabaseConstants.OBJECT_KEY);
        Note finalNote = this.tenConverter.stringToNote(json);
        //finalNote = this.tenConverter.addImagesFromDocumentToNote(finalNote, noteDocument);
        finalNote.setPictures(new ArrayList<Bitmap>());
        return finalNote;
    }

    //TODO Jan: Insert Befehl (neuen Eintrag anlegen; TEN hat noch keine ID)
    public void insertTEN(TEN ten) {
        MutableDocument mutableTENDocument = new MutableDocument();
        ten.setID(mutableTENDocument.getId());
        this.documentSaver.updateCompleteDocument(ten, mutableTENDocument);

    }


    public void updateTEN(TEN ten) {
        MutableDocument mutableTENDOcument = DatabaseManager.getDatabase().getDocument(ten.getID()).toMutable();
        this.documentSaver.updateCompleteDocument(ten, mutableTENDOcument);

    }

    public List<TEN> getAllTENs() {
        queries.getAllTENs();
        return null;
    }

    public boolean deleteTEN(String id) {
        try {
            DatabaseManager.getDatabase().delete(DatabaseManager.getDatabase().getDocument(id));
            return true;
        } catch (CouchbaseLiteException e) {
            return false;
        }
    }

    public int getNumberOfImages(String noteId) {
        Document noteDocument = DatabaseManager.getDatabase().getDocument(noteId);
        int numberOfImages = noteDocument.getInt(DatabaseConstants.IMAGE_COUNTER);
        return numberOfImages;
    }

    public Bitmap getImage(String noteId, String imageId) {
        ImageConverter asyncImageConverter = new ImageConverter();
        Document noteDocument = DatabaseManager.getDatabase().getDocument(noteId);
        Log.i("Testdata", "NoteID: " + noteId + " imageID " + imageId);
        Blob imageBlob = noteDocument.getBlob(imageId);
        Log.i("Testdata", "Blob: " + imageBlob);
        Log.i("Testdata", "ImageConverter: " + asyncImageConverter);
        Bitmap imageBitmap = asyncImageConverter.BlobToBitmap(imageBlob);
        return imageBitmap;
    }
}
