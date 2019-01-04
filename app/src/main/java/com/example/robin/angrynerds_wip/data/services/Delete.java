package com.example.robin.angrynerds_wip.data.services;

import com.example.robin.angrynerds_wip.data.repository.Repository;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;

import java.util.ArrayList;

public class Delete {
    /* Ruthild Gilles (30.11.2018)
     Class Delete contains methods to delete the given TEN object.
    */

    public static void deleteTEN(TEN ten) {
        Repository repository = new Repository();
        repository.deleteTEN(ten.getID());
    }

    public static void deleteManyTENs(ArrayList<TEN> ListTENs) {
        Repository repository = new Repository();
        for (TEN ten : ListTENs
                ) {
            repository.deleteTEN(ten.getID());
        }
    }
}
