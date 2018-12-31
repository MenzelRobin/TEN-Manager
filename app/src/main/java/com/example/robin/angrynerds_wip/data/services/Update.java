package com.example.robin.angrynerds_wip.data.services;

import com.example.robin.angrynerds_wip.data.repository.Repository;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;

public class Update {

    /* Ruthild Gilles (16.11.2018)
     Methode "saveTEN" kann für jede Art von TEN aufgerufen werden.
     TEN wird gespeichert.
    */
    /*--------------------------------------------------
        Methods for saving TEN object
     --------------------------------------------------*/

    public void saveTEN(TEN newTen) {
        Repository repository = new Repository();
        if (newTen.getID() == "") {
            repository.insertTEN(newTen);
        } else repository.updateTEN(newTen);
    }
}
