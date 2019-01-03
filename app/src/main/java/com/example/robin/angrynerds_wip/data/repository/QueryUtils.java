package com.example.robin.angrynerds_wip.data.repository;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.MutableDocument;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.repository.DatabaseManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.font.TextAttribute;

public class QueryUtils {

    ObjectMapper objectMapper;

    public QueryUtils(){
        this.objectMapper = new ObjectMapper();
    }

    public boolean updateCompleteDocument(TEN ten, MutableDocument mutableTENDocument){
        mutableTENDocument = getTypeOfTEN(mutableTENDocument, ten);
        mutableTENDocument.setDate(DatabaseConstants.CREATION_DATE_KEY, ten.getDateOfCreation());
        try {

            mutableTENDocument.setString(DatabaseConstants.OBJECT_KEY, this.objectMapper.writeValueAsString(ten));
            try {
                DatabaseManager.getDatabase().save(mutableTENDocument);
                return true;
            } catch (CouchbaseLiteException e) {
                return false;
            }
        } catch (JsonProcessingException e) {
            return false;
        }
    }

    private MutableDocument getTypeOfTEN(MutableDocument mutableDocument, TEN ten){
        if(ten.getClass().getName().contains("Event")){
            mutableDocument.setString(DatabaseConstants.TYPE_KEY, DatabaseConstants.EVENT_TYPE);
        }
        else if (ten.getClass().getName().contains("Note")){
            mutableDocument.setString(DatabaseConstants.TYPE_KEY, DatabaseConstants.NOTE_TYPE);
        }
        else if (ten.getClass().getName().contains("Todo")){
            mutableDocument.setString(DatabaseConstants.TYPE_KEY, DatabaseConstants.TODO_TYPE);
        }
        return mutableDocument;
    }
}
