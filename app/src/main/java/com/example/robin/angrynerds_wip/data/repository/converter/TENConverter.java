package com.example.robin.angrynerds_wip.data.repository.converter;

import android.graphics.Bitmap;
import android.util.Log;

import com.couchbase.lite.Dictionary;
import com.couchbase.lite.Document;
import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;
import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.repository.RepositoryConstants;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class TENConverter {

    private ObjectMapper objectMapper;

    public TENConverter() {
        this.objectMapper = new ObjectMapper();
    }

    public TEN addTENPropertiesFromDocument(TEN ten, Document document) {
        String documentID = document.getId();
        int color = document.getInt(RepositoryConstants.COLOR_KEY);
        int accentColor = document.getInt(RepositoryConstants.ACCENT_COLOR_KEY);
        Date dateOfCreation = document.getDate(RepositoryConstants.CREATION_DATE_KEY);

        ten.setID(documentID);
        ten.setColor(color);
        ten.setAccentColor(accentColor);
        ten.setDateOfCreation(dateOfCreation);

        return ten;
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
