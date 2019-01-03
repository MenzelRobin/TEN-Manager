package com.example.robin.angrynerds_wip.activities.event;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;

import com.example.robin.angrynerds_wip.R;

//import com.example.robin.angrynerds_wip.activities.Data;

public class Init extends AppCompatActivity {

    private Gui mGui;
    private ApplicationLogic mApplicationLogic;
    private Data mData;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
        initGUI();
        initApplicationLogic();
    }

    private void initData () {
        mData = new Data(this);
    }

    private void initGUI () {
        mGui = new Gui(this);
    }

    private void initApplicationLogic () {
        mApplicationLogic = new ApplicationLogic(mGui, this, mData);
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        //Data.saveDataInBundle(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // super.onActivityResult(requestCode, resultCode, Data);
        mApplicationLogic.onActivityReturned(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();   //default action
        //mApplicationLogic.onBackPressed();   // customized action
    }

    public void receiveDate(DatePicker dp){
        mApplicationLogic.receiveDate(dp);
    }

    public void receiveTime(int h, int m){
        mApplicationLogic.receiveTime(h, m);
    }

}
