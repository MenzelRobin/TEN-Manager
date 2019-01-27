package com.example.robin.angrynerds_wip.activities.todo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.example.robin.angrynerds_wip.data.models.tens.Todo;
import com.example.robin.angrynerds_wip.data.services.Delete;
import com.example.robin.angrynerds_wip.data.services.Read;
import com.example.robin.angrynerds_wip.data.services.Update;

public class Data {
    private Init mActivity;
    private Todo mTodo;

    Data(Init pActivity, String todoId) {
        mActivity = pActivity;
        mTodo = Read.getTodoByID(todoId);
    }
    public void deleteTodo() {
        Delete.deleteTEN(mTodo.getID());
    }

    public void updateTodo() {
        Update.saveTEN(mTodo);
    }

    public void setDate(Date date) {
        //mTodo.setTime(date);
        updateTodo();
    }

    public void setTitle(String title) {
        mTodo.setTitle(title);
        updateTodo();
    }


    //Getter
    public Todo getmTodo() {
        return mTodo;
    }
}
