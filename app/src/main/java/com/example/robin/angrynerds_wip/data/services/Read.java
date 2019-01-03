package com.example.robin.angrynerds_wip.data.services;

import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;
import com.example.robin.angrynerds_wip.data.repository.Repository;

import java.util.ArrayList;


public class Read {
     /* Ruthild Gilles (30.11.2018)
     Class Read contains methods to get all or one specific TEN object.
    */

    /*--------------------------------------------------
        Method to get all TEN objects in an arraylist
     --------------------------------------------------*/
    public static ArrayList<TEN> getAllTENs() {
        Repository repository = new Repository();
        ArrayList<TEN> allTEN = new ArrayList<>();
        for (TEN ten : repository.getAllTENs())
            allTEN.add(ten);
        return allTEN;
    }

    /*--------------------------------------------------
        Methods to get one TEN object by ID
     --------------------------------------------------*/
    public static Todo getTodoByID(String id) {
        Repository repository = new Repository();
        Todo todo = repository.getTodoByID(id);
        return todo;
    }

    public static Event getEventByID(String id) {
        Repository repository = new Repository();
        Event event = repository.getEventByID(id);
        return event;
    }

    public static Note getNoteByID(String id) {
        Repository repository = new Repository();
        Note note = repository.getNoteByID(id);
        return note;
    }
}
