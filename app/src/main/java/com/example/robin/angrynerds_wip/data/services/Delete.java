package com.example.robin.angrynerds_wip.data.services;

import com.example.robin.angrynerds_wip.data.Queries;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;

public class Delete {

    public static void deleteTEN(TEN ten) {
        Queries.deleteTEN(ten.getID());
    }
}
