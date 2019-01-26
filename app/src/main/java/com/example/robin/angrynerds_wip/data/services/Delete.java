package com.example.robin.angrynerds_wip.data.services;

import com.example.robin.angrynerds_wip.data.repository.Repository;

import java.util.ArrayList;

public class Delete {
    /* Ruthild Gilles (30.11.2018)
     Class Delete contains methods to delete the given TEN object.
    */

    public static void deleteTEN(String tenID) {
        Repository repository = new Repository();
        repository.deleteTEN(tenID);
    }

    public static void deleteMultipleTENs(ArrayList<String> tenIDs) {
        Repository repository = new Repository();
        for (String tenID : tenIDs) {
            repository.deleteTEN(tenID);
        }
    }
}
