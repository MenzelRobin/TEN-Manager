package com.example.robin.angrynerds_wip.data.repository.converter;

import com.couchbase.lite.Dictionary;
import com.couchbase.lite.Result;
import com.couchbase.lite.ResultSet;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.repository.DatabaseManager;
import com.example.robin.angrynerds_wip.data.repository.converter.sub_converter.EventConverter;

import java.util.ArrayList;

public class Converter {

    public ArrayList<TEN> resultSetToArray(ResultSet resultSet) {

        ArrayList<TEN> returnValue = new ArrayList<TEN>();
        for (Result result : resultSet) {
            Dictionary resultDic = result.getDictionary(DatabaseManager.DATABASENAME);

            TEN ten = null;
            switch (resultDic.getString("type")) {
                case "Event":
                    ten = new EventConverter().documentToEvent(resultDic);
                    break;
                case "Note":
                    ten = documentToNote(resultDic);
                    break;
                case "Todo":
                    ten = documentToTodo(resultDic);
                    break;
            }

            returnValue.add(ten);
        }
        return returnValue;
    }
}
