package com.example.robin.angrynerds_wip.data.services;

import com.example.robin.angrynerds_wip.data.repository.Repository;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;

public class Delete {
    /* Ruthild Gilles (30.11.2018)
     Class Delete contains methods to delete the given TEN object.
    */

    public static void deleteTEN(TEN ten) {
        Repository repository = new Repository();
        repository.deleteTEN(ten.getID());
    }
}
