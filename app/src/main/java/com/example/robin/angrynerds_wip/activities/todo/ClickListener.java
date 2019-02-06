package com.example.robin.angrynerds_wip.activities.todo;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.robin.angrynerds_wip.R;

// Authors: Florian Rath, Sertan Soner Cetin
public class ClickListener implements View.OnClickListener  {

    TodoApplicationLogic mTodoApplicationLogic;

    public ClickListener(TodoApplicationLogic todoApplicationLogic) {
        mTodoApplicationLogic = todoApplicationLogic;
    }

    @Override
    public void onClick(View view) {

        // Author: Sertan Soner Cetin
        for (int i = 0; i < mTodoApplicationLogic.getTasksItemCount(); i++)
        {
            if (i == view.getId())
            {
                mTodoApplicationLogic.onDeleteButtonClicked(i);
            }
        }

        // Author: Florian Rath
        switch (view.getId()) {
            case R.id.edit_todo_startDate:
                mTodoApplicationLogic.showDatePickerDialog(view);
                break;
            case R.id.edit_todo_endDate:
                mTodoApplicationLogic.showDatePickerDialog(view);
                break;
            case R.id.id_todo_addButton:
                mTodoApplicationLogic.addButtonClicked();
                break;
            case -1:
                mTodoApplicationLogic.returnToOverview();
                break;
            default:
                break;
        }
    }
}