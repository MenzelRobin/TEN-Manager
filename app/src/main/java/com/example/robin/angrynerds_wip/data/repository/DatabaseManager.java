package com.example.robin.angrynerds_wip.data.repository;

import android.content.Context;
import android.widget.Toast;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.DatabaseConfiguration;

public class DatabaseManager {

    public static Database database = null;
    public static Context context = null;


    public static void initDatabase(Context context){
        try {
            DatabaseConfiguration config = new DatabaseConfiguration(context.getApplicationContext());
            DatabaseManager.database = new Database(DatabaseConstants.DATABASENAME, config);
        } catch (CouchbaseLiteException e) {
            Toast.makeText(context, "Fehler bei der Datenbankerstellung", Toast.LENGTH_LONG);
        }

    }

    public static Database getDatabase() {
        return DatabaseManager.database;
    }
}
