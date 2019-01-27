package com.example.robin.angrynerds_wip.activities.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.robin.angrynerds_wip.R;

import java.util.Date;

//import com.example.robin.angrynerds_wip.activities.Data;

public class Init extends AppCompatActivity {

    private Gui mGui;
    private ApplicationLogic mApplicationLogic;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData(savedInstanceState);
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

    private void initData (Bundle savedInstanceState) {
        //mData = new Data(savedInstanceState, this);
    }

    private void initGUI () {
        mGui = new Gui(this);
    }

    private void initApplicationLogic () {
        mApplicationLogic = new ApplicationLogic(mGui,this);
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        //Data.saveDataInBundle(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // super.onActivityResult(requestCode, resultCode, data);
        mApplicationLogic.onActivityReturned(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();   //default action
        //mApplicationLogic.onBackPressed();   // customized action
    }

    public void receiveDate(Date dp){
        mApplicationLogic.receiveDate(dp);
    }

}
