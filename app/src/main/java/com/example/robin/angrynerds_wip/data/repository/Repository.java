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
import com.example.robin.angrynerds_wip.data.repository.converter.TENConverter;
import com.example.robin.angrynerds_wip.data.repository.database.DatabaseManager;
import com.example.robin.angrynerds_wip.data.repository.database.DocumentSaver;
import com.example.robin.angrynerds_wip.data.repository.database.Queries;
import com.example.robin.angrynerds_wip.data.repository.filesystem.FileManager;

import java.util.ArrayList;

public class Repository {
    TENConverter tenConverter;
    DocumentSaver documentSaver;
    Queries queries;
    FileManager fileManager;

    public Repository() {
        this.tenConverter = new TENConverter();
        this.documentSaver = new DocumentSaver();
        this.queries = new Queries();
        this.fileManager = new FileManager();
    }

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
        Note finalNote = this.tenConverter.stringToNote(json);
        finalNote = (Note) this.tenConverter.addTENPropertiesFromDocument(finalNote, noteDocument);
        return finalNote;
    }

    public void insertTEN(TEN ten) {
        MutableDocument mutableTENDocument = new MutableDocument();
        ten.setID(mutableTENDocument.getId());
        this.documentSaver.updateCompleteDocument(ten, mutableTENDocument);
    }

    public void updateTEN(TEN ten) {
        MutableDocument mutableTENDocument = DatabaseManager.getDatabase().getDocument(ten.getID()).toMutable();
        this.documentSaver.updateCompleteDocument(ten, mutableTENDocument);
    }

    public ArrayList<TEN> getAllTENs() {
        ArrayList<TEN> allTENs = queries.getAllTENs();
        return allTENs;
    }

    public boolean deleteTEN(String tenID) {

        Document document = DatabaseManager.getDatabase().getDocument(tenID);
        deleteNoteImages(document);
        try {
            DatabaseManager.getDatabase().delete(document);
            return true;
        } catch (CouchbaseLiteException e) {
            return false;
        }
    }

    private void deleteNoteImages(Document document) {
        if (document.getString(RepositoryConstants.TYPE_KEY).equals(RepositoryConstants.NOTE_TYPE)) {
            String json = document.getString(RepositoryConstants.OBJECT_KEY);
            Note note = tenConverter.stringToNote(json);
            for(Image image: note.getPictures()){
                fileManager.deleteImageFromDirectory(image);
            }
        }
    }

    public int[] getTENColors(String tenID) {
        Document document = DatabaseManager.getDatabase().getDocument(tenID);
        int[] colors = new int[2];
        colors[0] = document.getInt(RepositoryConstants.COLOR_KEY);
        colors[1] = document.getInt(RepositoryConstants.ACCENT_COLOR_KEY);
        return colors;
    }
}
