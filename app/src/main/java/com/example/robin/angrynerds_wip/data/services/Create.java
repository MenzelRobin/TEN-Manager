package com.example.robin.angrynerds_wip.data.services;

import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;

public class Create {

    /*--------------------
    Ruthild Gilles (04.12.2018)
    Klasse für Activities zum Erstellen von neuen leeren TEN Objekten
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
