package com.example.robin.angrynerds_wip.data.services;

import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;

public class Create {
    /* Ruthild Gilles (30.11.2018)
     Class Create contains methods to create new empty TEN objects.
     This class only exists to give a consistent form to the CRUD methods.
    */

    public static Todo newTodo() {
        return new Todo();
    }

    public static Event newEvent() {
        return new Event();
    }

    public static Note newNote() {
        return new Note();
    }
}
