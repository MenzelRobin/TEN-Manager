package com.example.robin.angrynerds_wip.data.repository.converter;

import com.couchbase.lite.Document;
import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;
import com.example.robin.angrynerds_wip.data.repository.RepositoryConstants;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class SpecificTENConverter {

    private ObjectMapper mObjectMapper;

    public SpecificTENConverter() {
        this.mObjectMapper = new ObjectMapper();
    }

    public TEN addTENPropertiesFromDocument(TEN pTen, Document pDocument) {
        pTen.setID(pDocument.getId());
        pTen.setColor(pDocument.getInt(RepositoryConstants.COLOR_KEY));
        pTen.setAccentColor(pDocument.getInt(RepositoryConstants.ACCENT_COLOR_KEY));
        pTen.setDateOfCreation(pDocument.getDate(RepositoryConstants.CREATION_DATE_KEY));
        return pTen;
    }

    public Todo stringToTodo(String pJson) {
        try {
            Todo todo = this.mObjectMapper.readValue(pJson, Todo.class);
            return todo;
        } catch (IOException e) {
            return null;
        }
    }

    public Event stringToEvent(String pJson) {
        try {
            Event event = this.mObjectMapper.readValue(pJson, Event.class);
            return event;
        } catch (IOException e) {
            return null;
        }
    }

    public Note stringToNote(String pJson) {
        try {
            Note note = this.mObjectMapper.readValue(pJson, Note.class);
            return note;
        } catch (IOException e) {
            return null;
        }
    }
}
