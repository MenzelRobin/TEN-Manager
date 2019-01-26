package com.example.robin.angrynerds_wip.data.repository.database;

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
import com.example.robin.angrynerds_wip.data.repository.RepositoryConstants;

public class DataContextManager {

    public static Database database = null;
    public static Context context = null;


    public static void initDatabase(Context pContext) {
        DataContextManager.context = pContext;
        try {
            if(DataContextManager.getDatabase() == null){
            DatabaseConfiguration config = new DatabaseConfiguration(pContext.getApplicationContext());
            DataContextManager.database = new Database(RepositoryConstants.DATABASENAME, config);
            }

        } catch (CouchbaseLiteException e) {
            Toast.makeText(pContext, "Fehler bei der Datenbankerstellung", Toast.LENGTH_LONG);
        }

    }

    public static void resetDatabase() {
        try{
        DataContextManager.getDatabase().compact();
        }catch (CouchbaseLiteException e){}
    }

    //TODO delete after Debug
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
