package com.example.robin.angrynerds_wip.data.services;

import com.example.robin.angrynerds_wip.data.Queries;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;

import java.util.ArrayList;

public class Update {

    /* Ruthild Gilles (16.11.2018)
     Methode "saveTEN" kann für jede Art von TEN aufgerufen werden.
     Werden weitere Attribute in den jeweiligen TEN Klassen hinzugefügt oder geändert,
     müssen auch hier die Methoden angepasst werden.
    */
    /*--------------------------------------------------
        Methods for saving one TEN object
     --------------------------------------------------*/
    public static void saveTEN(TEN newTEN) {
        TEN oldTEN = Queries.getByID(newTEN.getID());
        if (oldTEN == null) { //new TEN (not in database)
            Queries.insertTEN(newTEN);
        } else { //existing TEN (in database)
            Queries.updateTEN(newTEN.getID(), newTEN);
        }
    }

    public static void saveAllTEN(ArrayList<TEN> newTENs) {
        for (TEN ten : newTENs
                ) {
            saveTEN(ten);
        }
    }
}
