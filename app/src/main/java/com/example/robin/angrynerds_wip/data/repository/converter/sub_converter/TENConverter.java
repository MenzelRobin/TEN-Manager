package com.example.robin.angrynerds_wip.data.repository.converter.sub_converter;

import com.couchbase.lite.Document;
import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;
import com.example.robin.angrynerds_wip.data.repository.DatabaseManager;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class TENConverter {

    private ObjectMapper objectMapper;

    public TENConverter() {
        this.objectMapper = new ObjectMapper();
    }

    public Todo documentToTodo(Document document) {
        String TodoasJSON = document.getString(DatabaseManager.OBJECT_KEY);
        try {
            Todo todo = this.objectMapper.readValue(TodoasJSON, Todo.class);
            return todo;
        } catch (IOException e) {
            return null;
        }
    }

    public Event documentToEvent(Document eventDocument) {
        String EventasJSON = eventDocument.getString(DatabaseManager.OBJECT_KEY);
        try {
            Event event = this.objectMapper.readValue(EventasJSON, Event.class);
            return event;
        } catch (IOException e) {
            return null;
        }
    }


    public Note documentToNote(Document noteDocument) {
        String EventasJSON = noteDocument.getString(DatabaseManager.OBJECT_KEY);
        try {
            Note note = this.objectMapper.readValue(EventasJSON, Note.class);
            return note;
        } catch (IOException e) {
            return null;
        }
    }
}
