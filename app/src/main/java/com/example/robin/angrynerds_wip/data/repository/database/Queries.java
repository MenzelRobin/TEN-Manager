package com.example.robin.angrynerds_wip.data.repository.database;

import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.DataSource;
import com.couchbase.lite.Dictionary;
import com.couchbase.lite.Ordering;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryBuilder;
import com.couchbase.lite.Result;
import com.couchbase.lite.ResultSet;
import com.couchbase.lite.SelectResult;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.repository.converter.QueryResultConverter;
import com.example.robin.angrynerds_wip.data.repository.RepositoryConstants;

import java.util.ArrayList;
import java.util.List;

public class Queries {

    QueryResultConverter queryResultConverter;

    public Queries() {
        this.queryResultConverter = new QueryResultConverter();
    }

    public List<TEN> getAllTENs() {
        Query query = QueryBuilder.select(
                SelectResult.all())
                .from(DataSource.database(DatabaseManager.getDatabase()))
                .orderBy(Ordering.property(RepositoryConstants.CREATION_DATE_KEY).ascending());
        List<TEN> resultList = new ArrayList<TEN>();

        try {
            ResultSet rs = query.execute();

            List<Result> allResults = rs.allResults();
            for (Result result : allResults) {

                Dictionary dictionary = result.getDictionary(RepositoryConstants.DATABASENAME);
                Log.i("Testdata", "Query " + dictionary.getString(RepositoryConstants.TYPE_KEY));
                Log.i("Testdata", "Query " + dictionary.getString(RepositoryConstants.OBJECT_KEY));
                TEN tenObject = this.queryResultConverter.createTENFromResult(dictionary);
                //resultList.add(tenObject);
            }
            return resultList;

        } catch (CouchbaseLiteException e) {
            Log.i("Testdata", "datenbankfehler");
            return null;
        }
    }
}
