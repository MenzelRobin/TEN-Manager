package com.example.robin.angrynerds_wip.data.repository;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Document;
import com.couchbase.lite.MutableDocument;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.repository.converter.TENConverter;
import com.example.robin.angrynerds_wip.data.repository.database.DatabaseManager;
import com.example.robin.angrynerds_wip.data.repository.database.DocumentSaver;
import com.example.robin.angrynerds_wip.data.repository.filesystem.FileManager;

public class WriteRepository {

    private DocumentSaver mDocumentSaver;
    private TENConverter mTenConverter;
    private FileManager mFileManager;

    public WriteRepository(){
        this.mDocumentSaver = new DocumentSaver();
        this.mTenConverter = new TENConverter();
        this.mFileManager = new FileManager();
    }

    public void insertTEN(TEN ten) {
        MutableDocument mutableTENDocument = new MutableDocument();
        ten.setID(mutableTENDocument.getId());
        this.mDocumentSaver.updateCompleteDocument(ten, mutableTENDocument);
    }

    public void updateTEN(TEN ten) {
        MutableDocument mutableTENDocument = DatabaseManager.getDatabase().getDocument(ten.getID()).toMutable();
        this.mDocumentSaver.updateCompleteDocument(ten, mutableTENDocument);
    }

    public boolean deleteTEN(String tenID) {
        Document document = DatabaseManager.getDatabase().getDocument(tenID);
        deleteNoteImages(document);
        try {
            DatabaseManager.getDatabase().delete(document);
            return true;
        } catch (CouchbaseLiteException e) {
            return false;
        }
    }

    public void deleteNoteImages(Document document) {
        if (document.getString(RepositoryConstants.TYPE_KEY).equals(RepositoryConstants.NOTE_TYPE)) {
            String json = document.getString(RepositoryConstants.OBJECT_KEY);
            Note note = mTenConverter.stringToNote(json);
            for(Image image: note.getPictures()){
                mFileManager.deleteImageFromDirectory(image.getId());
            }
        }
    }
}
