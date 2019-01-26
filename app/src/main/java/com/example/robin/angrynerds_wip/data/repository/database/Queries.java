package com.example.robin.angrynerds_wip.data.repository.database;

import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.DataSource;
import com.couchbase.lite.Dictionary;
import com.couchbase.lite.Meta;
import com.couchbase.lite.Ordering;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryBuilder;
import com.couchbase.lite.Result;
import com.couchbase.lite.ResultSet;
import com.couchbase.lite.SelectResult;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.repository.converter.GenericTENConverter;
import com.example.robin.angrynerds_wip.data.repository.RepositoryConstants;

import java.util.ArrayList;
import java.util.List;

public class Queries {

    private GenericTENConverter mGenericTENConverter;

    public Queries() {
        this.mGenericTENConverter = new GenericTENConverter();
    }

    public ArrayList<TEN> getAllTENs() {
        Query query = QueryBuilder.select(
                SelectResult.all(),
                SelectResult.expression(Meta.id))
                .from(DataSource.database(DataContextManager.getDatabase()))
                .orderBy(Ordering.property(RepositoryConstants.CREATION_DATE_KEY).ascending());
        ArrayList<TEN> resultList = new ArrayList<TEN>();

        try {
            ResultSet rs = query.execute();

            List<Result> allResults = rs.allResults();
            for (Result result : allResults) {

                Dictionary dictionary = result.getDictionary(RepositoryConstants.DATABASENAME);
                TEN tenObject = this.mGenericTENConverter.createTENFromResult(dictionary);

                tenObject.setID(result.getString("id"));
                tenObject.setColor(dictionary.getInt(RepositoryConstants.COLOR_KEY));
                tenObject.setAccentColor(dictionary.getInt(RepositoryConstants.ACCENT_COLOR_KEY));
                tenObject.setDateOfCreation(dictionary.getDate(RepositoryConstants.CREATION_DATE_KEY));

                resultList.add(tenObject);
            }
            return resultList;

        } catch (CouchbaseLiteException e) {
            return null;
        }
    }
}
