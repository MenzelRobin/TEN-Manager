package com.example.robin.angrynerds_wip.activities.event.logic.listener;

import android.util.Log;
import android.view.View;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.event.logic.EventApplicationLogic;

public class EventClickListener implements View.OnClickListener {

    EventApplicationLogic mEventApplicationLogic;

    public EventClickListener(EventApplicationLogic eventApplicationLogic) {
        mEventApplicationLogic = eventApplicationLogic;
    }

    @Override
    public void onClick(View view) {
        Log.d("LOGTAG","onClick");
        switch ( view.getId() ) {
            case R.id.id_event_editText_date:
                mEventApplicationLogic.showDatePickerDialog(view);
                break;
            case R.id.id_event_editText_time:
                mEventApplicationLogic.showTimePickerDialog(view);
                break;
            case R.id.id_event_editText_recurringType:
                mEventApplicationLogic.onRecurringTypeClicked();
                break;
            case R.id.id_event_editText_newReminder:
                mEventApplicationLogic.onNewReminderClicked();
                break;
            case R.id.id_event_imageView_iconCloseReminder1:
                mEventApplicationLogic.onCloseReminderClicked(1);
                break;
            case R.id.id_event_imageView_iconCloseReminder2:
                mEventApplicationLogic.onCloseReminderClicked(2);
                break;
            case R.id.id_event_imageView_iconCloseReminder3:
                mEventApplicationLogic.onCloseReminderClicked(3);
                break;
            case R.id.id_event_imageView_iconCloseReminder4:
                mEventApplicationLogic.onCloseReminderClicked(4);
                break;
            case R.id.id_event_imageView_gmaps:
                mEventApplicationLogic.onNavigationClicked();
            case -1:
                mEventApplicationLogic.returnToOverview();
                break;
            default:
                break;
        }
    }
}
