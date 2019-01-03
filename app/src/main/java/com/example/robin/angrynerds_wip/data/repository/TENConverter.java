package com.example.robin.angrynerds_wip.data.repository;

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

    public Todo stringToTodo(String json) {
        try {
            Todo todo = this.objectMapper.readValue(json, Todo.class);
            return todo;
        } catch (IOException e) {
            return null;
        }
    }

    public Event stringToEvent(String json) {
        try {
            Event event = this.objectMapper.readValue(json, Event.class);
            return event;
        } catch (IOException e) {
            return null;
        }
    }


    public Note stringToNote(String json) {
        try {
            Note note = this.objectMapper.readValue(json, Note.class);
            return note;
        } catch (IOException e) {
            return null;
        }
    }
}
