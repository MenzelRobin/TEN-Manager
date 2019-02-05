package com.example.robin.angrynerds_wip.data.repository.sub_repositories.write;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.MutableDocument;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.repository.DataContextManager;
import com.example.robin.angrynerds_wip.data.repository.RepositoryConstants;
import com.example.robin.angrynerds_wip.data.repository.filesystem.FileRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//class that manages the persistent saving process of Tens
//Author: Jan Beilfuß
public class DocumentSaver {

    ObjectMapper mObjectMapper;
    FileRepository mFileRepository;

    public DocumentSaver() {
        this.mObjectMapper = new ObjectMapper();
        this.mFileRepository = new FileRepository();
    }

    //manages process of writing a ten to a document and saving this
    public boolean updateCompleteDocument(TEN pTen, MutableDocument pMutableTENDocument) {
        pMutableTENDocument = setTypeOfDocument(pMutableTENDocument, pTen);
        pMutableTENDocument = saveSeparateAttributes(pMutableTENDocument, pTen);
        try {
            pMutableTENDocument.setString(RepositoryConstants.OBJECT_KEY, this.mObjectMapper.writeValueAsString(pTen));
            try {
                DataContextManager.getDatabase().save(pMutableTENDocument);
                return true;
            } catch (CouchbaseLiteException e) {
                return false;
            }
        } catch (JsonProcessingException e) {
            return false;
        }
    }

    //saves attributes not saved in the JSON-String
    private MutableDocument saveSeparateAttributes(MutableDocument pMutableDocument, TEN pTen){
        pMutableDocument.setDate(RepositoryConstants.CREATION_DATE_KEY, pTen.getDateOfCreation());
        pMutableDocument.setInt(RepositoryConstants.COLOR_KEY, pTen.getColor());
        pMutableDocument.setInt(RepositoryConstants.ACCENT_COLOR_KEY, pTen.getAccentColor());

        return pMutableDocument;
    }

    //Sets Type depending of the class of the given Object
    private MutableDocument setTypeOfDocument(MutableDocument pMutableDocument, TEN pTen) {

        if (pTen.getClass().getName().contains("Event")) {
            pMutableDocument.setString(RepositoryConstants.TYPE_KEY, RepositoryConstants.EVENT_TYPE);

        } else if (pTen.getClass().getName().contains("Note")) {
            pMutableDocument.setString(RepositoryConstants.TYPE_KEY, RepositoryConstants.NOTE_TYPE);

        } else if (pTen.getClass().getName().contains("Todo")) {
            pMutableDocument.setString(RepositoryConstants.TYPE_KEY, RepositoryConstants.TODO_TYPE);
        }
        return pMutableDocument;
    }
}
