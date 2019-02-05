package com.example.robin.angrynerds_wip.data.repository.sub_repositories.read;

import com.couchbase.lite.Document;
import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;
import com.example.robin.angrynerds_wip.data.repository.RepositoryConstants;
import com.example.robin.angrynerds_wip.data.repository.converter.SeparateAttributesConverter;
import com.example.robin.angrynerds_wip.data.repository.converter.TenJsonParser;
import com.example.robin.angrynerds_wip.data.repository.DataContextManager;

import java.util.ArrayList;

//Class, that manages all reading Requests onto the Database
//Author: Jan Beilfuß
public class ReadRepository {
    TenJsonParser mTenJsonParser;
    SeparateAttributesConverter mSeparateAttributesConverter;
    GetAllTensQuery mGetAllTensQuery;

    public ReadRepository (){
        this.mTenJsonParser = new TenJsonParser();
        this.mSeparateAttributesConverter = new SeparateAttributesConverter();
        this.mGetAllTensQuery = new GetAllTensQuery();
    }

    //returns To-Do with given ID
    public Todo getTodoByID(String pId) {
        Document todoDocument = DataContextManager.getDatabase().getDocument(pId);
        String json = todoDocument.getString(RepositoryConstants.OBJECT_KEY);
        Todo finalTodo = this.mTenJsonParser.stringToTodo(json);
        finalTodo = (Todo) this.mSeparateAttributesConverter.addTENPropertiesFromDocument(finalTodo, todoDocument);
        return finalTodo;
    }

    //returns Event with given ID
    public Event getEventByID(String pId) {
        Document eventDocument = DataContextManager.getDatabase().getDocument(pId);
        String json = eventDocument.getString(RepositoryConstants.OBJECT_KEY);
        Event finalEvent = this.mTenJsonParser.stringToEvent(json);
        finalEvent = (Event) this.mSeparateAttributesConverter.addTENPropertiesFromDocument(finalEvent, eventDocument);
        return finalEvent;
    }

    //returns Note with given ID
    public Note getNoteByID(String pId) {
        Document noteDocument = DataContextManager.getDatabase().getDocument(pId);
        String json = noteDocument.getString(RepositoryConstants.OBJECT_KEY);
        Note finalNote = this.mTenJsonParser.stringToNote(json);
        finalNote = (Note) this.mSeparateAttributesConverter.addTENPropertiesFromDocument(finalNote, noteDocument);
        return finalNote;
    }

    //returns all saved TEN-Objects
    public ArrayList<TEN> getAllTENs() {
        return mGetAllTensQuery.getAllTENs();
    }

    //Needed when TEN is loaded asynchronously for setting the Color of the Activity
    public int[] getTENColors(String pTenId) {
        Document document = DataContextManager.getDatabase().getDocument(pTenId);
        int[] colors = new int[2];
        colors[0] = document.getInt(RepositoryConstants.COLOR_KEY);
        colors[1] = document.getInt(RepositoryConstants.ACCENT_COLOR_KEY);
        return colors;
    }
}
