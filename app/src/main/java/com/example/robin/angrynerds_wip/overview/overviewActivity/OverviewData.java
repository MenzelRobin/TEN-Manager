package com.example.robin.angrynerds_wip.overview.overviewActivity;

import android.util.Log;

import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;
import com.example.robin.angrynerds_wip.data.services.Read;

import java.util.ArrayList;

public class OverviewData {

    private OverviewInit mActivity;
    private ArrayList<TEN> mTEN = new ArrayList();

    OverviewData(OverviewInit pActivity) {
        mActivity = pActivity;
        pullTENs();
    }

    // Gets all TENs from the Database
    public void pullTENs(){
        mTEN = Read.getAllTENs();
    }

    // returns all loaded TENs
    public ArrayList<TEN> getTENs(){
        return mTEN;
    }

    // returns all Todos from the loaded TENs
    public ArrayList getTodos(){
        ArrayList<Todo> todo = new ArrayList();
        for(TEN ten : mTEN){
            if(ten.getClass() == Todo.class){
                todo.add((Todo) ten);
            }
        }
        return todo;
    }

    // returns all Events from the loaded TENs
    public ArrayList getEvents(){
        ArrayList<Event> event = new ArrayList();
        for(TEN ten : mTEN){
            if(ten.getClass() == Event.class){
                event.add((Event) ten);
            }
        }
        return event;
    }

    // returns all Notes from the loaded TENs
    public ArrayList<Note> getNotes(){
        ArrayList<Note> note = new ArrayList();
        for(TEN ten : mTEN){
            if(ten.getClass() == Note.class){
                note.add((Note) ten);
            }
        }
        return note;
    }
}