package com.example.robin.angrynerds_wip.data.repository;

import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;
import com.example.robin.angrynerds_wip.data.repository.sub_repositories.read.ReadRepository;
import com.example.robin.angrynerds_wip.data.repository.sub_repositories.write.WriteRepository;

import java.util.ArrayList;

//Class that manages All Requests to the Database
//Author: Jan Beilfuß
public class DatabaseRepository {
    ReadRepository mReadRepository;
    WriteRepository mWriteRepository;

    public DatabaseRepository() {
        this.mReadRepository = new ReadRepository();
        this.mWriteRepository = new WriteRepository();
    }

    public Todo getTodoByID(String pId) {
        return mReadRepository.getTodoByID(pId);
    }

    public Event getEventByID(String pId) {
        return mReadRepository.getEventByID(pId);
    }

    public Note getNoteByID(String pId) {
        return mReadRepository.getNoteByID(pId);
    }

    public void insertTEN(TEN pTen) { mWriteRepository.insertTEN(pTen); }

    public void updateTEN(TEN pTen) {
        mWriteRepository.updateTEN(pTen);
    }

    public ArrayList<TEN> getAllTENs() {
        return mReadRepository.getAllTENs();
    }

    public boolean deleteTEN(String pTenId) {
        return mWriteRepository.deleteTEN(pTenId);
    }

    public int[] getTENColors(String pTenId) {
        return mReadRepository.getTENColors(pTenId);
    }
}
