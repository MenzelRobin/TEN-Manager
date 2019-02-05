package com.example.robin.angrynerds_wip.activities.event.gui;

import android.app.Activity;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.data.models.utils.RecurringType;

public class RecurringTypeHelper {
    /* Robin Menzel
    Helps to give Labels to appropriate recurring types.
    */

    private String[] mRecurringTypeStrings;

    public RecurringTypeHelper(Activity pActivity) {
        mRecurringTypeStrings = new String[4];
        mRecurringTypeStrings[0] = pActivity.getString(R.string.event_reminder_recurringType_none);
        mRecurringTypeStrings[1] = pActivity.getString(R.string.event_reminder_recurringType_daily);
        mRecurringTypeStrings[2] = pActivity.getString(R.string.event_reminder_recurringType_weekly);
        mRecurringTypeStrings[3] = pActivity.getString(R.string.event_reminder_recurringType_yearly);
    }

    public String[] getRecurringTypeStrings() {
        return mRecurringTypeStrings;
    }

    public String getRecurringTypeString(RecurringType rt) {
        int i = 0;
        if (rt == RecurringType.NONE) {
            i = 0;
        } else if (rt == RecurringType.DAILY) {
            i = 1;
        } else if (rt == RecurringType.WEEKLY) {
            i = 2;
        } else if (rt == RecurringType.MONTHLY) {
            i = 3;
        }

        return mRecurringTypeStrings[i];
    }
}
