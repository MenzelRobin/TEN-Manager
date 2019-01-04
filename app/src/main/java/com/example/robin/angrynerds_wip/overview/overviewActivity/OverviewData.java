package com.example.robin.angrynerds_wip.overview.overviewActivity;

import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;
import com.example.robin.angrynerds_wip.data.services.Read;

import java.util.ArrayList;

public class OverviewData {

    private OverviewInit mActivity;
    private ArrayList<TEN> mData = new ArrayList();
    private ArrayList<TEN> mCurrentData = new ArrayList();

    OverviewData(OverviewInit pActivity) {
        mActivity = pActivity;
        refresh();
    }

    // Gets all TENs from the Database
    public void refresh(){
        mData = Read.getAllTENs();
    }

    // returns all current TENs
    public ArrayList<TEN> getData(){
        return mCurrentData;
    }

    // Filters for the selected Class
    public void filter(Class pClass){
        mCurrentData = new ArrayList();
        if(pClass == TEN.class){
            for(TEN ten : mData){
                mCurrentData.add(ten);
            }
        } else {
            for(TEN ten : mData){
                if(ten.getClass() == pClass){
                    mCurrentData.add(ten);
                }
            }
        }
    }

    // Filters for all TENs
    public void filterAll(){
        mCurrentData = new ArrayList();
        for(TEN ten : mData){
            mCurrentData.add(ten);
        }
    }

    // Filters all Todos into CurrentData
    public void filterTodos(){
        mCurrentData = new ArrayList();
        for(TEN ten : mData){
            if(ten.getClass() == Todo.class){
                mCurrentData.add(ten);
            }
        }
    }

    // Filters all Events into CurrentData
    public void filterEvents(){
        mCurrentData = new ArrayList();
        for(TEN ten : mData){
            if(ten.getClass() == Event.class){
                mCurrentData.add((Event) ten);
            }
        }
    }

    // Filters all Notes into CurrentData
    public void filterNotes(){
        mCurrentData = new ArrayList();
        for(TEN ten : mData) {
            if (ten.getClass() == Note.class) {
                mCurrentData.add(ten);
            }
        }
    }
}