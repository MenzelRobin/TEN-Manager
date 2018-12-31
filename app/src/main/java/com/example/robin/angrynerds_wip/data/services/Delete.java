package com.example.robin.angrynerds_wip.data.services;

import com.example.robin.angrynerds_wip.data.repository.Repository;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;

public class Delete {

    public static void deleteTEN(TEN ten) {
        Repository repository = new Repository();
        repository.deleteTEN(ten.getID());
    }
}
