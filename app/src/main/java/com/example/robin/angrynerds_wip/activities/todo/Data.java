package com.example.robin.angrynerds_wip.activities.todo;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.example.robin.angrynerds_wip.data.models.tens.Todo;
import com.example.robin.angrynerds_wip.data.models.utils.Task;
import com.example.robin.angrynerds_wip.data.services.Create;
import com.example.robin.angrynerds_wip.data.services.Delete;
import com.example.robin.angrynerds_wip.data.services.Read;
import com.example.robin.angrynerds_wip.data.services.Update;
import com.example.robin.angrynerds_wip.modules.share.ShareModule;

// Author: Sertan Soner Cetin
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
            if (mTodo.getTasks().size() < 1)
            {
                mTodo.getTasks().add(new Task());
            }
            mIsNew = false;
        }
    }
    public void deleteTodo() {
        Delete.deleteTEN(mTodo.getID());
    }

    public void updateTodo() {
        Update.saveTEN(mTodo);
    }

    public void shareTodo(String pStartDate, String pEndDate)
    {
        ShareModule.shareTodo(mActivity, mTodo, pStartDate, pEndDate);
    }

    public void setDate(Date date) {
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
