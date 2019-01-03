package com.example.robin.angrynerds_wip.data.services;

import com.example.robin.angrynerds_wip.data.repository.Repository;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;

import java.util.Objects;

public class Update {
    /* Ruthild Gilles (30.11.2018)
     Class Update contains methods to save information on a changed or newly created TEN.
    */

    /*--------------------------------------------------
        Methods for saving a TEN object
     --------------------------------------------------*/

    public static void saveTEN(TEN newTen) {
        Repository repository = new Repository();
        if (Objects.equals(newTen.getID(), "")) {
            repository.insertTEN(newTen);
        } else repository.updateTEN(newTen);
    }
}
