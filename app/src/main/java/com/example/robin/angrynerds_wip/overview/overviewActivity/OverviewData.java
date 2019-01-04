package com.example.robin.angrynerds_wip.overview.overviewActivity;

import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.services.Read;

import java.util.ArrayList;

public class OverviewData {

    private ArrayList<TEN> mData = new ArrayList();
    private ArrayList<TEN> mCurrentData = new ArrayList();

    OverviewData() {
        refresh();
        filter(TEN.class);
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
}