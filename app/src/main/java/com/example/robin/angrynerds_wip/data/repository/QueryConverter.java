package com.example.robin.angrynerds_wip.data.repository;

import com.couchbase.lite.Result;
import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;

public class QueryConverter {

    private TENConverter tenConverter;

    public QueryConverter() {
        this.tenConverter = new TENConverter();
    }

    public TEN createTENFromResult(Result result) {
        String type = result.getString(DatabaseConstants.TYPE_KEY);
        String object = result.getString(DatabaseConstants.OBJECT_KEY);

        switch (type) {
            case DatabaseConstants.EVENT_TYPE:
                Event event = tenConverter.stringToEvent(object);
                return event;
            case DatabaseConstants.NOTE_TYPE:
                Note note = tenConverter.stringToNote(object);
                return note;
            case DatabaseConstants.TODO_TYPE:
                Todo todo = tenConverter.stringToTodo(object);

            default:
                return null;
        }
    }
}
