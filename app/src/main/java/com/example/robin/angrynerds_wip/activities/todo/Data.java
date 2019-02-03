package com.example.robin.angrynerds_wip.activities.todo;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.example.robin.angrynerds_wip.data.models.tens.Todo;
import com.example.robin.angrynerds_wip.data.services.Create;
import com.example.robin.angrynerds_wip.data.services.Delete;
import com.example.robin.angrynerds_wip.data.services.Read;
import com.example.robin.angrynerds_wip.data.services.Update;

public class Data {
    private Init mActivity;
    private Todo mTodo;
    private boolean mIsNew;

    Data(Init pActivity, String todoId) {
        mActivity = pActivity;

        if(todoId == null){
            mTodo = Create.newTodo();
            Update.saveTEN(mTodo);
            mIsNew = true;

        }else{
            mTodo = Read.getTodoByID(todoId);
            mIsNew = false;
        }
    }
    public void deleteTodo() {
        Delete.deleteTEN(mTodo.getID());
    }

    public void updateTodo() {
        Update.saveTEN(mTodo);
    }

    public void setDate(Date date) {
        //mTodo.setDate(date);
        updateTodo();
    }

    public void setTitle(String title) {
        mTodo.setTitle(title);
        updateTodo();
    }

    public void setText(String text) {
        mTodo.setNote(text);
        updateTodo();
    }

    public boolean getmIsNew() { return mIsNew; }


    //Getter
    public Todo getmTodo() {
        return mTodo;
    }
}
