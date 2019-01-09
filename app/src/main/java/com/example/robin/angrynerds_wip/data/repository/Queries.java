package com.example.robin.angrynerds_wip.data.repository;

import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.DataSource;
import com.couchbase.lite.Ordering;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryBuilder;
import com.couchbase.lite.Result;
import com.couchbase.lite.ResultSet;
import com.couchbase.lite.SelectResult;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;

import java.util.ArrayList;
import java.util.List;

public class Queries {

    QueryResultConverter queryResultConverter;

    public Queries() {
        this.queryResultConverter = new QueryResultConverter();
    }

    public List<TEN> getAllTENs() {
        Query query = QueryBuilder.select(
                SelectResult.property(DatabaseConstants.OBJECT_KEY),
                SelectResult.property(DatabaseConstants.TYPE_KEY))
                .from(DataSource.database(DatabaseManager.getDatabase()))
                .orderBy(Ordering.property(DatabaseConstants.CREATION_DATE_KEY).descending());
        List<TEN> resultList = new ArrayList<TEN>();
        try {
            ResultSet rs = query.execute();
            for (Result result : rs) {

                Log.i("Testdata", result.getString(DatabaseConstants.OBJECT_KEY));
                TEN tenObject = this.queryResultConverter.createTENFromResult(result);
                resultList.add(tenObject);
            }
            return resultList;
        } catch (CouchbaseLiteException e) {
            return null;
        }
    }
}
