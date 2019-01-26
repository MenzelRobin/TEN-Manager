package com.example.robin.angrynerds_wip.data.repository;

import android.graphics.Bitmap;
import android.util.Log;

import com.couchbase.lite.Blob;
import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Document;
import com.couchbase.lite.MutableDocument;
import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;
import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.repository.converter.TENConverter;
import com.example.robin.angrynerds_wip.data.repository.database.DatabaseManager;
import com.example.robin.angrynerds_wip.data.repository.database.DocumentSaver;
import com.example.robin.angrynerds_wip.data.repository.database.Queries;
import com.example.robin.angrynerds_wip.data.repository.filesystem.FileManager;

import java.util.ArrayList;

public class Repository {
    ReadRepository mReadRepository;
    WriteRepository mWriteRepository;

    public Repository() {
        this.mReadRepository = new ReadRepository();
        this.mWriteRepository = new WriteRepository();
    }

    public Todo getTodoByID(String id) {
        return mReadRepository.getTodoByID(id);
    }

    public Event getEventByID(String id) {
        return mReadRepository.getEventByID(id);
    }

    public Note getNoteByID(String id) {
        return mReadRepository.getNoteByID(id);
    }

    public void insertTEN(TEN ten) {
        mWriteRepository.insertTEN(ten);
    }

    public void updateTEN(TEN ten) {
        mWriteRepository.updateTEN(ten);
    }

    public ArrayList<TEN> getAllTENs() {
       return mReadRepository.getAllTENs();
    }

    public boolean deleteTEN(String tenID) {
        return mWriteRepository.deleteTEN(tenID);
    }

    public int[] getTENColors(String tenID) {
        return mReadRepository.getTENColors(tenID);
    }
}
