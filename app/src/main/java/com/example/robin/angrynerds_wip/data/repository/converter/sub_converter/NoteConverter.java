package com.example.robin.angrynerds_wip.data.repository.converter.sub_converter;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Dictionary;
import com.couchbase.lite.MutableDocument;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.utils.Adress;
import com.example.robin.angrynerds_wip.data.models.utils.RecurringType;
import com.example.robin.angrynerds_wip.data.repository.DatabaseManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

public class NoteConverter {
    public TEN documentToNote(Dictionary dictionary) {
        Date time = dictionary.getDate("time");
        time.toString();
        Date[] reminder = new Date[dictionary.getArray("reminder").count()];
        Adress adress;
        RecurringType recurringType;
        return null;
    }

    public void updateNoteDocument(Note pNote) {
        ObjectMapper objectMapper = new ObjectMapper();
        MutableDocument document;

        if (pNote.getID() == "") {
            document = new MutableDocument();
            document.setString("type", pNote.getClass().getName());
            document.setLong("dateOfCreation", pNote.getDateOfCreation());
        } else {
            document = DatabaseManager.getDatabase().getDocument(pNote.getID()).toMutable();
        }

        try {
            document.setString(DatabaseManager.OBJECT_KEY, objectMapper.writeValueAsString(pNote));
        } catch (JsonProcessingException e) {
        }

        try{
            DatabaseManager.getDatabase().save(document);
        } catch (CouchbaseLiteException e) {
        }
    }
}
