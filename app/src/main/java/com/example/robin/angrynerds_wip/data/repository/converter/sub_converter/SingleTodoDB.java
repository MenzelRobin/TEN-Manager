package com.example.robin.angrynerds_wip.data.repository.converter.sub_converter;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Document;
import com.couchbase.lite.MutableDocument;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;
import com.example.robin.angrynerds_wip.data.repository.DatabaseManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class SingleTodoDB {

    private ObjectMapper objectMapper;

    SingleTodoDB() {
        this.objectMapper = new ObjectMapper();
    }

    public Todo getTodo(String id) {
        Document document = DatabaseManager.getDatabase().getDocument(id);
        String TodoasJSON = document.getString(DatabaseManager.OBJECT_KEY);
        try {
            Todo todo = this.objectMapper.readValue(TodoasJSON, Todo.class);
            return todo;
        } catch (IOException e) {
        }
        return null;
    }

    public boolean updateTodoDocument(Todo pTodo) {
        MutableDocument mDocument;

        mDocument = DatabaseManager.getDatabase().getDocument(pTodo.getID()).toMutable();
        try {
            mDocument.setString(DatabaseManager.OBJECT_KEY, objectMapper.writeValueAsString(pTodo));

            try {
                DatabaseManager.getDatabase().save(mDocument);
                return true;
            } catch (CouchbaseLiteException e) {
                return false;
            }

        } catch (JsonProcessingException e) {
            return false;
        }
    }

    public boolean createTodoDocument(Todo pTodo) {

        MutableDocument mDocument;

        mDocument = new MutableDocument();
        mDocument.setString("type", pTodo.getClass().getName());
        mDocument.setLong("dateOfCreation", pTodo.getDateOfCreation());
        try {
            
            mDocument.setString(DatabaseManager.OBJECT_KEY, objectMapper.writeValueAsString(pTodo));

            try {
                DatabaseManager.getDatabase().save(mDocument);
                return true;
            } catch (CouchbaseLiteException e) {
                return false;
            }
        } catch (JsonProcessingException e) {
            return false;
        }
    }
}
