package com.example.robin.angrynerds_wip.data.repository;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Document;
import com.couchbase.lite.MutableDocument;
import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;

import java.util.List;

public class Repository {
    TENConverter tenConverter;
    QueryUtils queryUtils;
    Queries queries;

    public Repository() {
        this.tenConverter = new TENConverter();
        this.queryUtils = new QueryUtils();
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
        return finalNote;
    }

    //TODO Jan: Insert Befehl (neuen Eintrag anlegen; TEN hat noch keine ID)
    public void insertTEN(TEN ten) {
        MutableDocument mutableTENDocument = new MutableDocument();
        ten.setID(mutableTENDocument.getId());
        this.queryUtils.updateCompleteDocument(ten, mutableTENDocument);

    }

    //TODO Jan: vorhanden Eintrag (mit übergebener ID) mit den Werten aus dem übergebenen TEN überschreiben
    public void updateTEN(TEN ten) {
        MutableDocument mutableTENDOcument = DatabaseManager.getDatabase().getDocument(ten.getID()).toMutable();
        this.queryUtils.updateCompleteDocument(ten, mutableTENDOcument);

    }

    //TODO Jan: alle vorhandenen TENs in einem Array übergeben
    public List<TEN> getAllTENs() {
        queries.getAllTENs();
        return null;
    }

    public List<Todo> getAllTodos() {
        //alle Todos sortiert nach Titel
        return null;
    }

    public List<Event> getAllEvents() {
        //alle Events sortiert nach Datum
        return null;
    }

    public List<Note> getAllNotes() {
        //alle Notes sortiert nach Titel
        return null;
    }

    //TODO Jan: Eintrag mit übergebener ID aus Datenbank löschen
    public boolean deleteTEN(String id) {
        try {
            DatabaseManager.getDatabase().delete(DatabaseManager.getDatabase().getDocument(id));
            return true;
        } catch (CouchbaseLiteException e) {
            return false;
        }
    }
}
