package com.example.robin.angrynerds_wip.data.repository;

import android.widget.Toast;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.DataSource;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryBuilder;
import com.couchbase.lite.ResultSet;
import com.couchbase.lite.SelectResult;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;

import java.util.ArrayList;

public class Queries {

    public ArrayList<TEN> getAll() {
        Query query = QueryBuilder.select(SelectResult.all())
                .from(DataSource.database(DatabaseManager.database));


        ResultSet results = null;
        try {
            results = query.execute();

        } catch (CouchbaseLiteException e) {
            Toast.makeText(DatabaseManager.context, "Fehler beim Zugriff auf die Datenbank", Toast.LENGTH_LONG);
        }

        return null;
    }

    public ArrayList<TEN> getEvents() {
        return null;

    }

    public ArrayList<TEN> getNotes() {
        return null;

    }

    public ArrayList<TEN> getToDos() {
        return null;

    }
}
