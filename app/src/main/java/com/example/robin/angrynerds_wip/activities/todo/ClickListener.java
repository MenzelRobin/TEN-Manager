package com.example.robin.angrynerds_wip.activities.todo;

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
            case R.id.edit_todo_startDate:
                mApplicationLogic.showDatePickerDialog(view);
                break;
            case R.id.edit_todo_endDate:
                mApplicationLogic.showDatePickerDialog(view);
                break;
            case R.id.edit_todo_title:
                mApplicationLogic.createList();
                break;
            case -1:
                mApplicationLogic.returnToOverview();
                break;
            default:
                break;
        }
    }
}