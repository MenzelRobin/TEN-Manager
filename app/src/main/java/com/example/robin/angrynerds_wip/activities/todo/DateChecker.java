package com.example.robin.angrynerds_wip.activities.todo;

import java.util.Date;

public class DateChecker {

    //date verification
    public boolean startDateBeforeEndDate(Date startDate, Date endDate){
        boolean dateCorrect = true;

        int startVar = (int) startDate.getTime();
        int endVar = (int) endDate.getTime();

        if(startVar > endVar){
            dateCorrect = false;
        }

        return dateCorrect;
    }
}
