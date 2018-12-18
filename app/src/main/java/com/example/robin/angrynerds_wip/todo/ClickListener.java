package com.example.robin.angrynerds_wip.todo;

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
                //mApplicationLogic.showDatePickerDialogue();
                break;
            default:
                break;
        }
    }

}
