package com.example.robin.angrynerds_wip.data.repository;

import android.content.Context;
import android.widget.Toast;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.DataSource;
import com.couchbase.lite.Database;
import com.couchbase.lite.DatabaseConfiguration;
import com.couchbase.lite.Meta;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryBuilder;
import com.couchbase.lite.ResultSet;
import com.couchbase.lite.SelectResult;

//Class that provides the database to the DatabaseRepository-Classes and Functions
//Author: Jan Beilfuß
public class DataContextManager {

    public static Database database = null;
    public static Context context = null;

    //Gets Database from Context if it is not set
    public static void initDatabase(Context pContext) {
        if (DataContextManager.getDatabase() == null) {
            DataContextManager.context = pContext;
            try {
                DatabaseConfiguration config = new DatabaseConfiguration(pContext.getApplicationContext());
                DataContextManager.database = new Database(RepositoryConstants.DATABASENAME, config);
                compactDatabase();
            } catch (CouchbaseLiteException e) {
                Toast.makeText(pContext, "Fehler bei der Datenbankerstellung", Toast.LENGTH_LONG);
            }
        }
    }

    //Compacts the Database = Cleans all Empty Entries (they are just flagged on Delete)
    public static void compactDatabase() {
        try {
            DataContextManager.getDatabase().compact();
        } catch (CouchbaseLiteException e) {
        }
    }

    //returns the number of Documents in the Database. ONLY needed when using Mockdata
    public static int getNumberOfDocuments() {
        Query query = QueryBuilder.select(SelectResult.expression(Meta.id)).from(DataSource.database(DataContextManager.getDatabase()));
        try {
            ResultSet rs = query.execute();
            return rs.allResults().size();
        } catch (CouchbaseLiteException e) {
            return -1;
        }
    }

    public static Database getDatabase() {
        return DataContextManager.database;
    }
}
