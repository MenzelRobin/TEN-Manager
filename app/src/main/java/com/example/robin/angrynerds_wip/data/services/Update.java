package com.example.robin.angrynerds_wip.data.services;

import com.example.robin.angrynerds_wip.data.repository.DatabaseRepository;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;

public class Update {
    /* Ruthild Gilles (30.11.2018)
     Class Update contains methods to save information on a changed or newly created TEN.
    */

    /*--------------------------------------------------
        Methods for saving a TEN object
     --------------------------------------------------*/

    public static void saveTEN(TEN newTen) {
        DatabaseRepository databaseRepository = new DatabaseRepository();
        if (newTen.getID() == null) {
            databaseRepository.insertTEN(newTen);
        } else databaseRepository.updateTEN(newTen);
    }
}
