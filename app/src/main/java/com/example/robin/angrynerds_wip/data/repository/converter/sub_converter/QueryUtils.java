package com.example.robin.angrynerds_wip.data.repository.converter.sub_converter;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.MutableDocument;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.repository.DatabaseManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class QueryUtils {

    ObjectMapper objectMapper;

    public QueryUtils(){
        this.objectMapper = new ObjectMapper();
    }

    public boolean updateCompleteDocument(TEN ten, MutableDocument mutableTENDocument){
        mutableTENDocument.setString("type", ten.getClass().getName());
        mutableTENDocument.setDate("dateOfCreation", ten.getDateOfCreation());
        try {

            mutableTENDocument.setString(DatabaseManager.OBJECT_KEY, this.objectMapper.writeValueAsString(ten));
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
}
