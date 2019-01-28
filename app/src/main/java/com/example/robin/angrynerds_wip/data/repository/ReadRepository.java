package com.example.robin.angrynerds_wip.data.repository;

import android.util.Log;

import com.couchbase.lite.Document;
import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;
import com.example.robin.angrynerds_wip.data.repository.converter.SpecificTENConverter;
import com.example.robin.angrynerds_wip.data.repository.database.DataContextManager;
import com.example.robin.angrynerds_wip.data.repository.database.Queries;

import java.util.ArrayList;

public class ReadRepository {
    SpecificTENConverter mSpecificTenConverter;
    Queries mQueries;

    public ReadRepository (){
        this.mSpecificTenConverter = new SpecificTENConverter();
        this.mQueries = new Queries();
    }

    public Todo getTodoByID(String pId) {
        Document todoDocument = DataContextManager.getDatabase().getDocument(pId);
        String json = todoDocument.getString(RepositoryConstants.OBJECT_KEY);
        Todo finalTodo = this.mSpecificTenConverter.stringToTodo(json);
        finalTodo = (Todo) this.mSpecificTenConverter.addTENPropertiesFromDocument(finalTodo, todoDocument);
        return finalTodo;
    }

    public Event getEventByID(String pId) {
        Document eventDocument = DataContextManager.getDatabase().getDocument(pId);
        String json = eventDocument.getString(RepositoryConstants.OBJECT_KEY);
        Event finalEvent = this.mSpecificTenConverter.stringToEvent(json);
        finalEvent = (Event) this.mSpecificTenConverter.addTENPropertiesFromDocument(finalEvent, eventDocument);
        return finalEvent;
    }

    public Note getNoteByID(String pId) {
        Document noteDocument = DataContextManager.getDatabase().getDocument(pId);
        String json = noteDocument.getString(RepositoryConstants.OBJECT_KEY);
        Note finalNote = this.mSpecificTenConverter.stringToNote(json);
        finalNote = (Note) this.mSpecificTenConverter.addTENPropertiesFromDocument(finalNote, noteDocument);
        return finalNote;
    }

    public ArrayList<TEN> getAllTENs() {
        return mQueries.getAllTENs();
    }

    public int[] getTENColors(String pTenId) {
        Document document = DataContextManager.getDatabase().getDocument(pTenId);
        int[] colors = new int[2];
        colors[0] = document.getInt(RepositoryConstants.COLOR_KEY);
        colors[1] = document.getInt(RepositoryConstants.ACCENT_COLOR_KEY);
        return colors;
    }
}
