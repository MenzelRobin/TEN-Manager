package com.example.robin.angrynerds_wip.data.repository.converter;

import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
//Class, that parses the JSON into a TEN Object
//Author: Jan Beilfu0
public class TenJsonParser {

    private ObjectMapper mObjectMapper;

    public TenJsonParser() {
        this.mObjectMapper = new ObjectMapper();
    }

    //parses To-do
    public Todo stringToTodo(String pJson) {
        try {
            Todo todo = this.mObjectMapper.readValue(pJson, Todo.class);
            return todo;
        } catch (IOException e) {
            return null;
        }
    }

    //parses Event
    public Event stringToEvent(String pJson) {
        try {
            Event event = this.mObjectMapper.readValue(pJson, Event.class);
            return event;
        } catch (IOException e) {
            return null;
        }
    }

    //parses Note
    public Note stringToNote(String pJson) {
        try {
            Note note = this.mObjectMapper.readValue(pJson, Note.class);
            return note;
        } catch (IOException e) {
            return null;
        }
    }
}
