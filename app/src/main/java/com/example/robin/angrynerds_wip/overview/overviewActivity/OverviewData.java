package com.example.robin.angrynerds_wip.overview.overviewActivity;

import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;
import com.example.robin.angrynerds_wip.data.services.Read;

import java.util.ArrayList;

public class OverviewData {

    OverviewInit mActivity;
    ArrayList<TEN> mTEN = new ArrayList();


    OverviewData(OverviewInit pActivity) {
        mActivity = pActivity;
    }

    public void pullTEN(){
        mTEN = Read.getAllTENs();
    }

    public ArrayList<TEN> getTEN(){
        return mTEN;
    }

    public ArrayList getTodo(){
        ArrayList<Todo> todo = new ArrayList();
        for(TEN ten : mTEN){
            if(ten.getClass() == Todo.class){
                todo.add((Todo) ten);
            }
        }
        return todo;
    }

    public ArrayList getEvent(){
        ArrayList<Event> event = new ArrayList();
        for(TEN ten : mTEN){
            if(ten.getClass() == Event.class){
                event.add((Event) ten);
            }
        }
        return event;
    }

    public ArrayList getNote(){
        ArrayList<Note> note = new ArrayList();
        for(TEN ten : mTEN){
            if(ten.getClass() == Note.class){
                note.add((Note) ten);
            }
        }
        return note;
    }
}
