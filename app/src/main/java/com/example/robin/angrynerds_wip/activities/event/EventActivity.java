package com.example.robin.angrynerds_wip.activities.event;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.event.data.EventData;
import com.example.robin.angrynerds_wip.activities.event.gui.EventGui;
import com.example.robin.angrynerds_wip.activities.event.logic.EventApplicationLogic;
import com.example.robin.angrynerds_wip.data.repository.DataContextManager;


public class EventActivity extends AppCompatActivity {

    private EventGui mGui;
    private EventApplicationLogic mEventApplicationLogic;
    private EventData mData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataContextManager.initDatabase(getApplicationContext());

        initData(getIntent().getStringExtra("ID"));
        initGUI();
        initApplicationLogic();
    }

    private void initData(String eventId) {
        mData = new EventData(this, eventId);
    }

    private void initGUI() {
        mGui = new EventGui(this);
    }

    private void initApplicationLogic() {
        mEventApplicationLogic = new EventApplicationLogic(mGui, this, mData);
    }

    @Override
    public void onBackPressed() {
        mEventApplicationLogic.returnToOverview();
    }

    public void receiveDate(int year, int month, int day) {
        mEventApplicationLogic.receiveDate(year, month, day);
    }

    public void receiveTime(int hour, int minute) {
        mEventApplicationLogic.receiveTime(hour, minute);
    }

    //Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.event_menu, menu);
        return true;
    }
}
