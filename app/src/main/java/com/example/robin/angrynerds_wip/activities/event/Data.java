package com.example.robin.angrynerds_wip.activities.event;

import android.os.Bundle;

import java.util.Date;
import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.utils.Address;

import static java.util.UUID.fromString;

public class Data {
    private Init mActivity;
    private Event mEvent;

    Data(Init pActivity){
        mActivity = pActivity;
        mEvent = new Event("Aufräumen", new Date(1546514305), new Date[]{}, new Address("Feldstr.",64, "40764", "Langenfeld (Rheinland)"));
    }

    public Event getmEvent(){
        return mEvent;
    }

    public void addData(Bundle pData){

    }
}
