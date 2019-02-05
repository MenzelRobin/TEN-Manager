package com.example.robin.angrynerds_wip.data.repository.sub_repositories.write;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.MutableDocument;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.repository.DataContextManager;
import com.example.robin.angrynerds_wip.data.repository.RepositoryConstants;
import com.example.robin.angrynerds_wip.data.repository.filesystem.FileRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

//class that manages the persistent saving process of Tens
//Author: Jan Beilfuß
public class DocumentSaver {

    ObjectMapper mObjectMapper;
    FileRepository mFileRepository;

    public DocumentSaver() {
        this.mObjectMapper = new ObjectMapper();
        this.mFileRepository = new FileRepository();
    }

    public boolean updateCompleteDocument(TEN pTen, MutableDocument pMutableTENDocument) {
        pMutableTENDocument = preparePersistentSaving(pMutableTENDocument, pTen);
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

    private MutableDocument saveSeparateAttributes(MutableDocument pMutableDocument, TEN pTen){
        pMutableDocument.setDate(RepositoryConstants.CREATION_DATE_KEY, pTen.getDateOfCreation());
        pMutableDocument.setInt(RepositoryConstants.COLOR_KEY, pTen.getColor());
        pMutableDocument.setInt(RepositoryConstants.ACCENT_COLOR_KEY, pTen.getAccentColor());

        return pMutableDocument;
    }

    private MutableDocument preparePersistentSaving(MutableDocument pMutableDocument, TEN pTen) {

        if (pTen.getClass().getName().contains("Event")) {
            pMutableDocument.setString(RepositoryConstants.TYPE_KEY, RepositoryConstants.EVENT_TYPE);

        } else if (pTen.getClass().getName().contains("Note")) {
            pMutableDocument.setString(RepositoryConstants.TYPE_KEY, RepositoryConstants.NOTE_TYPE);
            saveNoteImages((Note) pTen);

        } else if (pTen.getClass().getName().contains("Todo")) {
            pMutableDocument.setString(RepositoryConstants.TYPE_KEY, RepositoryConstants.TODO_TYPE);
        }
        return pMutableDocument;
    }

    private void saveNoteImages(Note pNote) {

        for(Image image: pNote.getPictures()){
            image.setId(image.getId().replaceAll("null", pNote.getID()));
            try{
                mFileRepository.saveImagePersistent(image);
            } catch (IOException e){

            }
        }
    }
}
