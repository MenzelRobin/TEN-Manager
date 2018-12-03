package com.example.robin.angrynerds_wip.data.repository.converter.sub_converter;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Document;
import com.couchbase.lite.MutableDocument;
import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.repository.DatabaseManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class EventConverter {

    private ObjectMapper objectMapper;

    EventConverter(){
        this.objectMapper = new ObjectMapper();
    }

    public Event getEvent(String id) {
        Document document = DatabaseManager.getDatabase().getDocument(id);
        String EventasJSON = document.getString(DatabaseManager.OBJECT_KEY);
        try{
        Event event = this.objectMapper.readValue(EventasJSON, Event.class);
        return event;
        } catch(IOException e){ };
        return null;
    }

    public void updateEventDocument(Event pEvent) {
        MutableDocument mDocument;

        if (pEvent.getID() == "") {
            mDocument = new MutableDocument();
            mDocument.setString("type", pEvent.getClass().getName());
            mDocument.setLong("dateOfCreation", pEvent.getDateOfCreation());
        } else {
            mDocument = DatabaseManager.getDatabase().getDocument(pEvent.getID()).toMutable();
        }

        try {
            mDocument.setString(DatabaseManager.OBJECT_KEY, objectMapper.writeValueAsString(pEvent));
        } catch (JsonProcessingException e) {
        }

        try{
            DatabaseManager.getDatabase().save(mDocument);
        } catch (CouchbaseLiteException e) {
        }
    }
}
