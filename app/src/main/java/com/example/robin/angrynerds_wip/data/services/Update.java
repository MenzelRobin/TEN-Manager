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
        Methods for saving one TEN object
     --------------------------------------------------*/


    //Teste lieber, ob das newTEN schon eine ID hat
    public static void saveTEN(TEN newTEN) {
        TEN oldTEN = Queries.getByID(newTEN.getID());
        if (oldTEN == null) { //new TEN (not in database)
            Queries.insertTEN(newTEN);
        } else { //existing TEN (in database)
            Queries.updateTEN(newTEN);
        }
    }

    // wo brauchen wir das? Es werden doch maximal einzelne TENs gespeichert
    public static void saveAllTEN(ArrayList<TEN> newTENs) {
        for (TEN ten : newTENs
                ) {
            saveTEN(ten);
        }
    }
}
