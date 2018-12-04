package com.example.robin.angrynerds_wip.data.repository.converter.sub_converter;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Document;
import com.couchbase.lite.MutableDocument;
import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.repository.DatabaseManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class SingleEventDB {

    private ObjectMapper objectMapper;

    SingleEventDB() {
        this.objectMapper = new ObjectMapper();
    }

    public Event getEvent(String id) {
        Document document = DatabaseManager.getDatabase().getDocument(id);
        String EventasJSON = document.getString(DatabaseManager.OBJECT_KEY);
        try {
            Event event = this.objectMapper.readValue(EventasJSON, Event.class);
            return event;
        } catch (IOException e) {
        }
        return null;
    }

    public boolean updateEventDocument(Event pEvent) {
        MutableDocument mDocument;

        mDocument = DatabaseManager.getDatabase().getDocument(pEvent.getID()).toMutable();
        try {
            mDocument.setString(DatabaseManager.OBJECT_KEY, objectMapper.writeValueAsString(pEvent));

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

    public boolean createEventDocument(Event pEvent) {

        MutableDocument mDocument;

        mDocument = new MutableDocument();
        mDocument.setString("type", pEvent.getClass().getName());
        mDocument.setLong("dateOfCreation", pEvent.getDateOfCreation());
        try {

            mDocument.setString(DatabaseManager.OBJECT_KEY, objectMapper.writeValueAsString(pEvent));

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

    public boolean deleteTEN(String pID){
        try{
            DatabaseManager.getDatabase().delete(DatabaseManager.getDatabase().getDocument(pID));
            return true;
        } catch (CouchbaseLiteException e){
            return false;
        }
    }
}
