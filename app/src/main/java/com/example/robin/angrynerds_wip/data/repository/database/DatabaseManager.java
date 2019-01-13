package com.example.robin.angrynerds_wip.data.repository.database;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.DataSource;
import com.couchbase.lite.Database;
import com.couchbase.lite.DatabaseConfiguration;
import com.couchbase.lite.Meta;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryBuilder;
import com.couchbase.lite.Result;
import com.couchbase.lite.ResultSet;
import com.couchbase.lite.SelectResult;
import com.example.robin.angrynerds_wip.data.repository.RepositoryConstants;

public class DatabaseManager {

    public static Database database = null;
    public static Context context = null;


    public static void initDatabase(Context context) {
        DatabaseManager.context = context;
        try {
            if(DatabaseManager.getDatabase() == null){
            DatabaseConfiguration config = new DatabaseConfiguration(context.getApplicationContext());
            DatabaseManager.database = new Database(RepositoryConstants.DATABASENAME, config);
            }

        } catch (CouchbaseLiteException e) {
            Toast.makeText(context, "Fehler bei der Datenbankerstellung", Toast.LENGTH_LONG);
        }

    }

    public static void resetDatabase() {

        if (DatabaseManager.getDatabase() == null) {
            Log.i("Testdata", "Pls init Database before reseting it");
        } else {

            try {
                Query query = QueryBuilder.select(SelectResult.expression(Meta.id))
                        .from(DataSource.database(DatabaseManager.getDatabase()));
                ResultSet resultSet = query.execute();


                for (Result result : resultSet.allResults()) {
                    String id = result.getString("id");
                    Log.i("Testdata", "deleted " + id);
                    DatabaseManager.getDatabase().delete(DatabaseManager.getDatabase().getDocument(id));

                }
                Log.i("Testdata", "Number of rows ::  " + resultSet.allResults().size());

            } catch (CouchbaseLiteException e) {
                Log.i("Testdata", e.toString());
            }

        }

    }

    public static int getNumberOfDocuments() {
        Query query = QueryBuilder.select(SelectResult.expression(Meta.id)).from(DataSource.database(DatabaseManager.getDatabase()));
        try {
            ResultSet rs = query.execute();
            return rs.allResults().size();
        } catch (CouchbaseLiteException e) {
            return -1;
        }
    }

    public static Database getDatabase() {
        return DatabaseManager.database;
    }
}
