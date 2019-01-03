package com.example.robin.angrynerds_wip.activities.event;

import android.util.Log;
import android.view.View;

import com.example.robin.angrynerds_wip.R;

public class ClickListener implements View.OnClickListener {

    ApplicationLogic mApplicationLogic;

    public ClickListener(ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
    }

    @Override
    public void onClick(View view) {
        Log.d("LOGTAG","onClick");
        switch ( view.getId() ) {
            case R.id.editTextDate:
                mApplicationLogic.showDatePickerDialog(view);
                Log.d("LOGTAG", "on editTextDate clicked");
                break;
            case R.id.editTextTime:
                mApplicationLogic.showTimePickerDialog(view);
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
