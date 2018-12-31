package com.example.robin.angrynerds_wip.data.repository;

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
        //TODO muss durch die Database Constants ausgetauscht werden, ansonsten crasht die app wenn man die Paketstruktur ändert
        mutableTENDocument.setString(DatabaseConstants.TYPE_KEY, ten.getClass().getName());
        mutableTENDocument.setDate("dateOfCreation", ten.getDateOfCreation());
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
}
