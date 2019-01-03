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
        //MOCKDATA

        MockData md = new MockData();
        return md.tenMockData;
    }
    /*
    public static ArrayList<TEN> getAllTENs() {

        //TODO Ruthild: Sortieren
        ArrayList<TEN> allTEN = new ArrayList<TEN>();
        Todo[] allTodos = Queries.getAllTodods();
        Event[] allEvents = Queries.getAllEvents();
        Note[] allNotes = Queries.getAllNotes();
        for (Todo t : allTodos
                ) {
            allTEN.add(t);
        }
        for (Event e : allEvents
                ) {
            allTEN.add(e);
        }
        for (Note n : allNotes
                ) {
            allTEN.add(n);
        }
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
    /*public static Note getNoteByID(String id) {
        //MOCKDATEN
        for(TEN ten : MockData.tenMockData)
        if (ten instanceof Note) {
            Note n = (Note) ten;
            return n;
        }
        return null;
        /*
        Note note = Queries.getNoteByID(id);

        return note;
        */
    //}
}
