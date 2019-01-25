package com.example.robin.angrynerds_wip.data.repository.converter;

import com.couchbase.lite.Dictionary;
import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;
import com.example.robin.angrynerds_wip.data.repository.RepositoryConstants;

public class QueryResultConverter {

    private TENConverter tenConverter;

    public QueryResultConverter() {
        this.tenConverter = new TENConverter();
    }

    public TEN createTENFromResult(Dictionary dictionary) {
        String type = dictionary.getString(RepositoryConstants.TYPE_KEY);
        String objectJSON = dictionary.getString(RepositoryConstants.OBJECT_KEY);

        switch (type) {
            case RepositoryConstants.EVENT_TYPE:
                Event event = tenConverter.stringToEvent(objectJSON);
                return event;
            case RepositoryConstants.NOTE_TYPE:
                Note note = tenConverter.stringToNote(objectJSON);

                //note = tenConverter.addImagesFromResultToNote(note, dictionary);
                return note;
            case RepositoryConstants.TODO_TYPE:
                Todo todo = tenConverter.stringToTodo(objectJSON);
                return todo;
            default:
                return null;
        }
    }
}
