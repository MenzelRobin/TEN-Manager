package com.example.robin.angrynerds_wip.data.repository.converter.sub_converter;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Dictionary;
import com.couchbase.lite.MutableDocument;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;
import com.example.robin.angrynerds_wip.data.models.utils.Adress;
import com.example.robin.angrynerds_wip.data.models.utils.RecurringType;
import com.example.robin.angrynerds_wip.data.repository.DatabaseManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

public class TodoConverter {
    public TEN documentToEvent(Dictionary dictionary) {
        Date time = dictionary.getDate("time");
        time.toString();
        Date[] reminder = new Date[dictionary.getArray("reminder").count()];
        Adress adress;
        RecurringType recurringType;
        return null;
    }

    public void updateTodoDocument(Todo pTodo) {
        ObjectMapper objectMapper = new ObjectMapper();
        MutableDocument document;

        if (pTodo.getID() == "") {
            document = new MutableDocument();
            document.setString("type", pTodo.getClass().getName());
            document.setLong("dateOfCreation", pTodo.getDateOfCreation());
        } else {
            document = DatabaseManager.getDatabase().getDocument(pTodo.getID()).toMutable();
        }

        try {
            document.setString(DatabaseManager.OBJECT_KEY, objectMapper.writeValueAsString(pTodo));
        } catch (JsonProcessingException e) {
        }

        try{
            DatabaseManager.getDatabase().save(document);
        } catch (CouchbaseLiteException e) {
        }
    }
}
