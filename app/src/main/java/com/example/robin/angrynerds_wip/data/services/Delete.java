package com.example.robin.angrynerds_wip.data.services;

import android.util.Log;

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

    public static void deleteMultipleTEN(ArrayList<String> pToDelete){
        //Repository repository = new Repository();
        for(String id : pToDelete){
            //repository.deleteTEN(id);
            Log.d("DELETING", "TEN with id " + id);
        }
    }
}
