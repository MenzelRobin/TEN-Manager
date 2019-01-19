package com.example.robin.angrynerds_wip.activities.event;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import com.example.robin.angrynerds_wip.R;

import java.util.Date;


public class Init extends AppCompatActivity {

    private Gui mGui;
    private EventApplicationLogic mEventApplicationLogic;
    private Data mData;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData(getIntent().getStringExtra("ID"));
        initGUI();
        initApplicationLogic();
    }

    //Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.event_menu, menu);
        return true;
    }

    private void initData (String eventId) {
        mData = new Data(this, eventId);
    }

    private void initGUI () {
        mGui = new Gui(this);
    }

    private void initApplicationLogic () {
        mEventApplicationLogic = new EventApplicationLogic(mGui, this, mData);
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        //Data.saveDataInBundle(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        //super.returnToOverview();   //default action
        mEventApplicationLogic.returnToOverview();   // customized action
    }

    public void receiveDate(Date date){
        mEventApplicationLogic.receiveDate(date);
    }

    public void receiveTime(Date date){ mEventApplicationLogic.receiveTime(date); }

}
