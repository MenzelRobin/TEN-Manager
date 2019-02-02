package com.example.robin.angrynerds_wip.data.repository.converter;

import com.couchbase.lite.Dictionary;
import com.couchbase.lite.Document;
import com.couchbase.lite.Result;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.repository.RepositoryConstants;


//Methods that adds all Attributes not stored in the JSON
//Author: Jan Beilfuß
public class SeparateAttributesConverter {

    //Document is the Databasereturn when getting only a single TEN
    public TEN addTENPropertiesFromDocument(TEN pTen, Document pDocument) {
        pTen.setID(pDocument.getId());
        pTen.setColor(pDocument.getInt(RepositoryConstants.COLOR_KEY));
        pTen.setAccentColor(pDocument.getInt(RepositoryConstants.ACCENT_COLOR_KEY));
        pTen.setDateOfCreation(pDocument.getDate(RepositoryConstants.CREATION_DATE_KEY));
        return pTen;
    }

    //Result is the Databasereturn when querying a set of TENs
    public TEN addTENPropertiesFromResult(TEN pTen, Result pResult) {
        Dictionary dictionary = pResult.getDictionary(RepositoryConstants.DATABASENAME);
        pTen.setID(pResult.getString(RepositoryConstants.COUCHBASE_ID_KEY));
        pTen.setColor(dictionary.getInt(RepositoryConstants.COLOR_KEY));
        pTen.setAccentColor(dictionary.getInt(RepositoryConstants.ACCENT_COLOR_KEY));
        pTen.setDateOfCreation(dictionary.getDate(RepositoryConstants.CREATION_DATE_KEY));

        return pTen;
    }
}
