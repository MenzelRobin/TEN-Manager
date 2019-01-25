package com.example.robin.angrynerds_wip.overview.overviewActivity;

import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.services.Read;

import java.util.ArrayList;

public class OverviewData {

    private ArrayList<TEN> mData = new ArrayList();
    private ArrayList<TEN> mCurrentData = new ArrayList();
    private Class mCurrentClass;

    OverviewData() {
        setCurrentClass(TEN.class);
        refresh();
    }

    // Gets all TENs from the Database
    public void refresh(){
        mData = Read.getAllTENs();
        filter();
    }

    // returns all current TENs
    public ArrayList<TEN> getData(){
        return mCurrentData;
    }

    // Sets current Class and filters
    public void setCurrentClass(Class pClass){
        mCurrentClass = pClass;
    }

    // Filters for the selected Class
    public void filter(){
        mCurrentData = new ArrayList();
        if(mCurrentClass == TEN.class){
            for(TEN ten : mData){
                mCurrentData.add(ten);
            }
        } else {
            for(TEN ten : mData){
                if(ten.getClass() == mCurrentClass){
                    mCurrentData.add(ten);
                }
            }
        }
    }
}