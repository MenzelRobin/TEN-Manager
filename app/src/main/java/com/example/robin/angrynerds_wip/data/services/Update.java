package com.example.robin.angrynerds_wip.data.services;

import com.example.robin.angrynerds_wip.data.repository.Queries;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;

import java.util.ArrayList;

public class Update {

    /* Ruthild Gilles (16.11.2018)
     Methode "saveTEN" kann für jede Art von TEN aufgerufen werden.
     TEN wird gespeichert.
    */
    /*--------------------------------------------------
        Methods for saving TEN object
     --------------------------------------------------*/

    public static void saveTEN(TEN newTen) {
        if (newTen.getID() == "") {
            Queries.insertTEN(newTen);
        } else Queries.updateTEN(newTen);
    }
}
