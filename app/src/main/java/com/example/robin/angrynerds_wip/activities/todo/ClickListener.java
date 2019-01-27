package com.example.robin.angrynerds_wip.activities.todo;

import android.view.View;

import com.example.robin.angrynerds_wip.R;

public class ClickListener implements View.OnClickListener  {

    TodoApplicationLogic mTodoApplicationLogic;

    public ClickListener(TodoApplicationLogic todoApplicationLogic) {
        mTodoApplicationLogic = todoApplicationLogic;
    }

    @Override
    public void onClick(View view) {

        switch ( view.getId() ) {
            case R.id.edit_todo_startDate:
                mTodoApplicationLogic.showDatePickerDialog(view);
                break;
            case R.id.edit_todo_endDate:
                mTodoApplicationLogic.showDatePickerDialog(view);
                break;
            case R.id.edit_todo_title:
                mTodoApplicationLogic.createList();
                break;
            case -1:
                mTodoApplicationLogic.returnToOverview();
                break;
            default:
                break;
        }
    }
}