package com.example.robin.angrynerds_wip.data.repository;

import android.graphics.Bitmap;

import com.couchbase.lite.Result;
import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

public class TENConverter {

    private ObjectMapper objectMapper;
    private ImageConverter imageConverter;

    public TENConverter() {
        this.objectMapper = new ObjectMapper();
        this.imageConverter = new ImageConverter();
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


            //TODO Bilder aus den Blobs hinzufügen
            return note;
        } catch (IOException e) {
            return null;
        }
    }

    public Note addImagesFromResultToNote(Note note, Result result) {
        int numberOfPictures = result.getInt(DatabaseConstants.IMAGE_COUNTER);
        ArrayList<Bitmap> images = new ArrayList<Bitmap>();

        for (int i = 1; i <= numberOfPictures; i++) {

            Bitmap image = this.imageConverter
                    .BlobToBitmap(result.getBlob(DatabaseConstants.IMAGE_CORE_ID + i));
            images.add(image);
        }

        note.setPictures(images);
        return note;
    }
}
