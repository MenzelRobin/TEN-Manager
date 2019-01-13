package com.example.robin.angrynerds_wip.data.repository;

import android.graphics.Bitmap;
import android.util.Log;

import com.couchbase.lite.Blob;
import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Document;
import com.couchbase.lite.MutableDocument;
import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;
import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.repository.converter.ImageConverter;
import com.example.robin.angrynerds_wip.data.repository.converter.TENConverter;
import com.example.robin.angrynerds_wip.data.repository.database.DatabaseManager;
import com.example.robin.angrynerds_wip.data.repository.database.DocumentSaver;
import com.example.robin.angrynerds_wip.data.repository.database.Queries;

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
        String json = todoDocument.getString(RepositoryConstants.OBJECT_KEY);
        Todo finalTodo = this.tenConverter.stringToTodo(json);
        finalTodo = (Todo) this.tenConverter.addTENPropertiesFromDocument(finalTodo, todoDocument);
        return finalTodo;
    }

    public Event getEventByID(String id) {
        Document eventDocument = DatabaseManager.getDatabase().getDocument(id);
        String json = eventDocument.getString(RepositoryConstants.OBJECT_KEY);
        Event finalEvent = this.tenConverter.stringToEvent(json);
        finalEvent = (Event) this.tenConverter.addTENPropertiesFromDocument(finalEvent, eventDocument);
        return finalEvent;
    }

    public Note getNoteByID(String id) {
        Document noteDocument = DatabaseManager.getDatabase().getDocument(id);
        String json = noteDocument.getString(RepositoryConstants.OBJECT_KEY);
        Log.i("NoteRemake", json);
        Note finalNote = this.tenConverter.stringToNote(json);
        finalNote = (Note) this.tenConverter.addTENPropertiesFromDocument(finalNote, noteDocument);
        return finalNote;
    }

    //TODO Jan: Insert Befehl (neuen Eintrag anlegen; TEN hat noch keine ID)
    public void insertTEN(TEN ten) {
        MutableDocument mutableTENDocument = new MutableDocument();
        ten.setID(mutableTENDocument.getId());
        this.documentSaver.updateCompleteDocument(ten, mutableTENDocument);

    }


    public void updateTEN(TEN ten) {
        MutableDocument mutableTENDocument = DatabaseManager.getDatabase().getDocument(ten.getID()).toMutable();
        this.documentSaver.updateCompleteDocument(ten, mutableTENDocument);

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
        int numberOfImages = noteDocument.getInt(RepositoryConstants.IMAGE_COUNTER);
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

    public int[] getTENColors(String tenID) {
        Document document = DatabaseManager.getDatabase().getDocument(tenID);
        int[] colors = new int [2];
        colors[0] = document.getInt(RepositoryConstants.COLOR_KEY);
        colors[1] = document.getInt(RepositoryConstants.ACCENT_COLOR_KEY);
        return colors;
    }
}
