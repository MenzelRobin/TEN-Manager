package com.example.robin.angrynerds_wip.data.repository;

import com.couchbase.lite.Document;
import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;
import com.example.robin.angrynerds_wip.data.repository.converter.TENConverter;
import com.example.robin.angrynerds_wip.data.repository.database.DatabaseManager;
import com.example.robin.angrynerds_wip.data.repository.database.Queries;

import java.util.ArrayList;

public class ReadRepository {
    TENConverter mTenConverter;
    Queries mQueries;

    public ReadRepository (){
        this.mTenConverter = new TENConverter();
        this.mQueries = new Queries();
    }

    public Todo getTodoByID(String id) {
        Document todoDocument = DatabaseManager.getDatabase().getDocument(id);
        String json = todoDocument.getString(RepositoryConstants.OBJECT_KEY);
        Todo finalTodo = this.mTenConverter.stringToTodo(json);
        finalTodo = (Todo) this.mTenConverter.addTENPropertiesFromDocument(finalTodo, todoDocument);
        return finalTodo;
    }

    public Event getEventByID(String id) {
        Document eventDocument = DatabaseManager.getDatabase().getDocument(id);
        String json = eventDocument.getString(RepositoryConstants.OBJECT_KEY);
        Event finalEvent = this.mTenConverter.stringToEvent(json);
        finalEvent = (Event) this.mTenConverter.addTENPropertiesFromDocument(finalEvent, eventDocument);
        return finalEvent;
    }

    public Note getNoteByID(String id) {
        Document noteDocument = DatabaseManager.getDatabase().getDocument(id);
        String json = noteDocument.getString(RepositoryConstants.OBJECT_KEY);
        Note finalNote = this.mTenConverter.stringToNote(json);
        finalNote = (Note) this.mTenConverter.addTENPropertiesFromDocument(finalNote, noteDocument);
        return finalNote;
    }

    public ArrayList<TEN> getAllTENs() {
        ArrayList<TEN> allTENs = mQueries.getAllTENs();
        return allTENs;
    }

    public int[] getTENColors(String tenID) {
        Document document = DatabaseManager.getDatabase().getDocument(tenID);
        int[] colors = new int[2];
        colors[0] = document.getInt(RepositoryConstants.COLOR_KEY);
        colors[1] = document.getInt(RepositoryConstants.ACCENT_COLOR_KEY);
        return colors;
    }
}
