package com.example.robin.angrynerds_wip.activities.todo;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MenuItemClickListener implements Toolbar.OnMenuItemClickListener{
    TodoApplicationLogic mTodoApplicationLogic;

    public MenuItemClickListener(TodoApplicationLogic pTodoApplicationLogic){
        mTodoApplicationLogic = pTodoApplicationLogic;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        mTodoApplicationLogic.onMenuItemClick(item);
        return false;
    }
}
