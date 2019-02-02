package com.example.robin.angrynerds_wip.data.repository.converter;

import com.couchbase.lite.Dictionary;
import com.couchbase.lite.Result;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.repository.RepositoryConstants;

//class that manages the Conversion from Query Result to TEN Java Object
//Author: Jan Beilfuß
public class GenericTENConverter {

    private TenJsonParser mTenJsonParser;
    private SeparateAttributesConverter mSeparateAttributesConverter;

    public GenericTENConverter() {
        this.mTenJsonParser = new TenJsonParser();
        this.mSeparateAttributesConverter = new SeparateAttributesConverter();
    }

    //Method that maps the Dictionary of a Query Result To a TEN-Object
    //Object only contains Information from the JSON, but non of the ones stored in the document
    public TEN createTENFromResult(Result pResult) {

        Dictionary dictionary = pResult.getDictionary(RepositoryConstants.DATABASENAME);
        String type = dictionary.getString(RepositoryConstants.TYPE_KEY);
        String objectJSON = dictionary.getString(RepositoryConstants.OBJECT_KEY);

        TEN resultTEN = null;
        switch (type) {
            case RepositoryConstants.EVENT_TYPE:
                resultTEN = mTenJsonParser.stringToEvent(objectJSON);
                break;
            case RepositoryConstants.NOTE_TYPE:
            resultTEN = mTenJsonParser.stringToNote(objectJSON);
                break;
            case RepositoryConstants.TODO_TYPE:
            resultTEN = mTenJsonParser.stringToTodo(objectJSON);
                break;
            default:
        }
        if (resultTEN != null) {
            resultTEN = this.mSeparateAttributesConverter.addTENPropertiesFromResult(resultTEN, pResult);
        }
        return resultTEN;
    }
}
