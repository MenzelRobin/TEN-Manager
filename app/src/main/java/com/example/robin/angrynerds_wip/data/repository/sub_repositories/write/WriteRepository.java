package com.example.robin.angrynerds_wip.data.repository.sub_repositories.write;

import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Document;
import com.couchbase.lite.MutableDocument;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.repository.RepositoryConstants;
import com.example.robin.angrynerds_wip.data.repository.converter.TenJsonParser;
import com.example.robin.angrynerds_wip.data.repository.DataContextManager;
import com.example.robin.angrynerds_wip.data.repository.filesystem.FileRepository;

//Class that handles all writing accesses to the database
//Author: Jan Beilfuß
public class WriteRepository {

    private DocumentSaver mDocumentSaver;
    private TenJsonParser mTenJsonParser;
    private FileRepository mFileRepository;

    public WriteRepository(){
        this.mDocumentSaver = new DocumentSaver();
        this.mTenJsonParser = new TenJsonParser();
        this.mFileRepository = new FileRepository();
    }

    //new TEN to the Database
    public void insertTEN(TEN pTen) {
        MutableDocument mutableTENDocument = new MutableDocument();
        pTen.setID(mutableTENDocument.getId());
        this.mDocumentSaver.updateCompleteDocument(pTen, mutableTENDocument);
    }

    //already Existing TEN to the Database
    public void updateTEN(TEN pTen) {
        MutableDocument mutableTENDocument = DataContextManager.getDatabase().getDocument(pTen.getID()).toMutable();
        this.mDocumentSaver.updateCompleteDocument(pTen, mutableTENDocument);
    }

    public boolean deleteTEN(String pTenId) {
        Document document = DataContextManager.getDatabase().getDocument(pTenId);
        Log.d("NoteS", "Doc: " + document);
        deleteNoteImages(document);
        try {
            if(document != null){
                DataContextManager.getDatabase().delete(document);
                return true;
            }
            return false;
        } catch (CouchbaseLiteException e) {
            return false;
        }
    }

    //Side Effects of Deleting a Note
    public void deleteNoteImages(Document pDocument) {
        if (pDocument.getString(RepositoryConstants.TYPE_KEY).equals(RepositoryConstants.NOTE_TYPE)) {
            String json = pDocument.getString(RepositoryConstants.OBJECT_KEY);
            Note note = mTenJsonParser.stringToNote(json);
            for(Image image: note.getPictures()){
                mFileRepository.deleteImageFromDirectory(image.getId());
            }
        }
    }
}
