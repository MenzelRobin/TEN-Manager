package com.example.robin.angrynerds_wip.activities.todo;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.services.Update;

// Author Sertan Soner Cetin
public class CheckedChangeListener implements CheckBox.OnCheckedChangeListener {

    TodoApplicationLogic mTodoApplicationLogic;

    public CheckedChangeListener(TodoApplicationLogic pTodoApplicationLogic)
    {
        mTodoApplicationLogic = pTodoApplicationLogic;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        for (int i = 0; i < mTodoApplicationLogic.getTasksItemCount(); i++)
        {
            if (i == compoundButton.getId())
            {
                mTodoApplicationLogic.getmTasks().get(i).setStatus(b);
                mTodoApplicationLogic.updateProgress();
            }
        }
    }
}
