package com.example.robin.angrynerds_wip.data.repository;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Document;
import com.couchbase.lite.MutableDocument;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.repository.converter.SpecificTENConverter;
import com.example.robin.angrynerds_wip.data.repository.database.DataContextManager;
import com.example.robin.angrynerds_wip.data.repository.database.DocumentSaver;
import com.example.robin.angrynerds_wip.data.repository.filesystem.FileManager;

public class WriteRepository {

    private DocumentSaver mDocumentSaver;
    private SpecificTENConverter mSpecificTenConverter;
    private FileManager mFileManager;

    public WriteRepository(){
        this.mDocumentSaver = new DocumentSaver();
        this.mSpecificTenConverter = new SpecificTENConverter();
        this.mFileManager = new FileManager();
    }

    public void insertTEN(TEN pTen) {
        MutableDocument mutableTENDocument = new MutableDocument();
        pTen.setID(mutableTENDocument.getId());

        this.mDocumentSaver.updateCompleteDocument(pTen, mutableTENDocument);
    }

    public void updateTEN(TEN pTen) {
        MutableDocument mutableTENDocument = DataContextManager.getDatabase().getDocument(pTen.getID()).toMutable();
        this.mDocumentSaver.updateCompleteDocument(pTen, mutableTENDocument);
    }

    public boolean deleteTEN(String pTenId) {
        Document document = DataContextManager.getDatabase().getDocument(pTenId);
        deleteNoteImages(document);
        try {
            DataContextManager.getDatabase().delete(document);
            return true;
        } catch (CouchbaseLiteException e) {
            return false;
        }
    }

    public void deleteNoteImages(Document pDocument) {
        if (pDocument.getString(RepositoryConstants.TYPE_KEY).equals(RepositoryConstants.NOTE_TYPE)) {
            String json = pDocument.getString(RepositoryConstants.OBJECT_KEY);
            Note note = mSpecificTenConverter.stringToNote(json);
            for(Image image: note.getPictures()){
                mFileManager.deleteImageFromDirectory(image.getId());
            }
        }
    }
}
