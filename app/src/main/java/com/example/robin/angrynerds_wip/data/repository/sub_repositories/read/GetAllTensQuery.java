package com.example.robin.angrynerds_wip.data.repository.sub_repositories.read;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.DataSource;
import com.couchbase.lite.Meta;
import com.couchbase.lite.Ordering;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryBuilder;
import com.couchbase.lite.Result;
import com.couchbase.lite.ResultSet;
import com.couchbase.lite.SelectResult;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.repository.RepositoryConstants;
import com.example.robin.angrynerds_wip.data.repository.converter.GenericTENConverter;
import com.example.robin.angrynerds_wip.data.repository.DataContextManager;

import java.util.ArrayList;
import java.util.List;

//Class that contains our currently only Database-Query
//Author: Jan Beilfuß
public class GetAllTensQuery {

    private GenericTENConverter mGenericTENConverter;

    public GetAllTensQuery() { this.mGenericTENConverter = new GenericTENConverter(); }

    public ArrayList<TEN> getAllTENs() {
        Query query = QueryBuilder.select(
                SelectResult.all(),
                SelectResult.expression(Meta.id))
                .from(DataSource.database(DataContextManager.getDatabase()))
                .orderBy(Ordering.property(RepositoryConstants.CREATION_DATE_KEY).descending());
        ArrayList<TEN> resultList = new ArrayList<TEN>();

        try {
            ResultSet rs = query.execute();

            List<Result> allResults = rs.allResults();
            for (Result result : allResults) {
                TEN tenObject = this.mGenericTENConverter.createTENFromResult(result);
                resultList.add(tenObject);
            }
            return resultList;

        } catch (CouchbaseLiteException e) {
            return null;
        }
    }
}
