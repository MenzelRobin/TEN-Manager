package com.example.robin.angrynerds_wip.data.services;

import com.example.robin.angrynerds_wip.data.repository.Repository;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;

public class Delete {

    private Repository repository;

    public Delete(){
        this.repository = new Repository();
    }

    public void deleteTEN(TEN ten) {
        this.repository.deleteTEN(ten.getID());
    }
}
