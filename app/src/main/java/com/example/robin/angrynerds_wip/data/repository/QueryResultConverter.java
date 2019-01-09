package com.example.robin.angrynerds_wip.data.repository;

import com.couchbase.lite.Result;
import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;

public class QueryResultConverter {

    private TENConverter tenConverter;

    public QueryResultConverter() {
        this.tenConverter = new TENConverter();
    }

    public TEN createTENFromResult(Result result) {
        String type = result.getString(DatabaseConstants.TYPE_KEY);
        String objectJSON = result.getString(DatabaseConstants.OBJECT_KEY);

        switch (type) {
            case DatabaseConstants.EVENT_TYPE:
                Event event = tenConverter.stringToEvent(objectJSON);
                return event;
            case DatabaseConstants.NOTE_TYPE:
                Note note = tenConverter.stringToNote(objectJSON);
                note = tenConverter.addImagesFromResultToNote(note, result);
                return note;
            case DatabaseConstants.TODO_TYPE:
                Todo todo = tenConverter.stringToTodo(objectJSON);

            default:
                return null;
        }
    }
}
