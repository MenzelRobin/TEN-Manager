package com.example.robin.angrynerds_wip.data;

import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;

public class Queries {

    //TODO Jan: jeweiliges Objekt mit übergebener ID zurückgeben (wenn kein Objekt mit ID dann return null)
    public static TEN getByID(String id) {
        return null;
    }

    public static Todo getTodoByID(String id) {
        return null;
    }

    public static Event getEventByID(String id) {
        return null;
    }

    public static Note getNoteByID(String id) {
        return null;
    }

    //TODO Jan: Insert Befehl (neuen Eintrag anlegen; TEN hat noch keine ID)
    public static void insertTEN(TEN ten) {

    }

    //TODO Jan: vorhanden Eintrag (mit übergebener ID) mit den Werten aus dem übergebenen TEN überschreiben
    public static void updateTEN(String id, TEN ten) {
    }

    //TODO Jan: alle vorhandenen TENs in einem Array übergeben
    public static Todo[] getAllTodods() {
        //alle Todos sortiert nach Titel
        return null;
    }

    public static Event[] getAllEvents() {
        //alle Events sortiert nach Datum
        return null;
    }

    public static Note[] getAllNotes() {
        //alle Notes sortiert nach Titel
        return null;
    }

    //TODO Jan: Eintrag mit übergebener ID aus Datenbank löschen
    public static void deleteTEN(String id) {
    }
}
