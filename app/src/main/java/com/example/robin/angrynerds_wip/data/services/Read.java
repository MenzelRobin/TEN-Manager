package com.example.robin.angrynerds_wip.data.services;

import android.graphics.Color;

import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;
import com.example.robin.angrynerds_wip.data.models.utils.MockData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Read {

    /*--------------------------------------------------
        Method to get all TEN objects in an arraylist
     --------------------------------------------------*/
    public static ArrayList<TEN> getAllTENs() {
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
    } */


    /*--------------------------------------------------
        Methods to get one TEN object by ID
     --------------------------------------------------*/

    public static Todo getTodoByID(String id) {
        //MOCKDATEN

        Todo t = new Todo("TodoTest");
        return t;

        /*
        Todo todo = Queries.getTodoByID(id);
        return todo;
        */
    }

    public static Event getEventByID(String id) {
        //MOCKDATEN

        Event e = new Event("EventTest", Calendar.getInstance().getTime(), new Date[1540942029]);
        return e;

        /*
        Event event = Queries.getEventByID(id);
        return event;
        */
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
