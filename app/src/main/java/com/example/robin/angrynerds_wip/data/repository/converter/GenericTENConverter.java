package com.example.robin.angrynerds_wip.data.repository.converter;

import com.couchbase.lite.Dictionary;
import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;
import com.example.robin.angrynerds_wip.data.repository.RepositoryConstants;

public class GenericTENConverter {

    private SpecificTENConverter mSpecificTenConverter;

    public GenericTENConverter() {
        this.mSpecificTenConverter = new SpecificTENConverter();
    }

    public TEN createTENFromResult(Dictionary pDictionary) {
        String type = pDictionary.getString(RepositoryConstants.TYPE_KEY);
        String objectJSON = pDictionary.getString(RepositoryConstants.OBJECT_KEY);

        switch (type) {
            case RepositoryConstants.EVENT_TYPE:
                Event event = mSpecificTenConverter.stringToEvent(objectJSON);
                return event;
            case RepositoryConstants.NOTE_TYPE:
                Note note = mSpecificTenConverter.stringToNote(objectJSON);
                return note;
            case RepositoryConstants.TODO_TYPE:
                Todo todo = mSpecificTenConverter.stringToTodo(objectJSON);
                return todo;
            default:
                return null;
        }
    }


}
