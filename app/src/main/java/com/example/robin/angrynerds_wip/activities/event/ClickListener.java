package com.example.robin.angrynerds_wip.activities.event;

import android.view.View;

import com.example.robin.angrynerds_wip.R;

public class ClickListener implements View.OnClickListener  {

    ApplicationLogic mApplicationLogic;

    public ClickListener(ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
    }

    @Override
    public void onClick(View view) {
        switch ( view.getId() ) {
            case R.id.editTextDate:
                mApplicationLogic.onDateClicked();
                break;
            case R.id.editTextTime:
                mApplicationLogic.onTimeClicked();
            case R.id.editTextNewReminder:
                mApplicationLogic.onNewReminderClicked();
            case R.id.iconCloseReminder1:
                mApplicationLogic.removeReminder(R.id.iconCloseReminder1);
            case R.id.iconCloseReminder2:
                mApplicationLogic.removeReminder(R.id.iconCloseReminder2);
            default:
                break;
        }
    }

}
