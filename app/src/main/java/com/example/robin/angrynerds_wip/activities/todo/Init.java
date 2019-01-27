package com.example.robin.angrynerds_wip.activities.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.data.services.Create;

import java.util.Date;

//import com.example.robin.angrynerds_wip.activities.Data;

public class Init extends AppCompatActivity {

    private Gui mGui;
    private TodoApplicationLogic mTodoApplicationLogic;
    private Data mData;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        initData("3dc9a05d-da97-4c10-88b3-2f30e84f1fbf");
        initGUI();
        initApplicationLogic();
    }

    //Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.todo_menu, menu);
        return true;
    }

    private void initData (String todoId) {

        mData = new Data(this, todoId);
    }

    private void initGUI () {
        mGui = new Gui(this);
    }

    private void initApplicationLogic () {
        mTodoApplicationLogic = new TodoApplicationLogic(mGui,this, mData);
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        //Data.saveDataInBundle(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // super.onActivityResult(requestCode, resultCode, data);
        mTodoApplicationLogic.onActivityReturned(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();   //default action
        //mTodoApplicationLogic.onBackPressed();   // customized action
    }

    public void receiveDate(Date dp){
        mTodoApplicationLogic.receiveDate(dp);
    }

}
