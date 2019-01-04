package com.example.robin.angrynerds_wip.todo;

import android.content.Context;
import android.view.View;
import android.widget.DatePicker;

import com.example.robin.angrynerds_wip.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ClickListener implements View.OnClickListener  {

    ApplicationLogic mApplicationLogic;

    public ClickListener(ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
    }

    @Override
    public void onClick(View view) {

        switch ( view.getId() ) {
            case R.id.edit_todo_startDate:
                //mApplicationLogic.showDatePickerDialogue();
                //Button funktioniert jetzt
                mApplicationLogic.onStartDateClicked();
                break;
            default:
                break;
        }
    }


}
