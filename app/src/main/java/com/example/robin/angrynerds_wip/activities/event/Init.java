package com.example.robin.angrynerds_wip.activities.event;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ShareActionProvider;

import com.example.robin.angrynerds_wip.R;

import java.util.Date;


public class Init extends AppCompatActivity {

    private Gui mGui;
    private EventApplicationLogic mEventApplicationLogic;
    private Data mData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData(getIntent().getStringExtra("ID"));
        initGUI();
        initApplicationLogic();
    }

    private void initData(String eventId) {
        mData = new Data(this, eventId);
    }

    private void initGUI() {
        mGui = new Gui(this);
    }

    private void initApplicationLogic() {
        mEventApplicationLogic = new EventApplicationLogic(mGui, this, mData);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        mEventApplicationLogic.returnToOverview();
    }

    public void receiveDate(Date date) {
        mEventApplicationLogic.receiveDate(date);
    }

    public void receiveTime(Date date) {
        mEventApplicationLogic.receiveTime(date);
    }

    //Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.event_menu, menu);
        return true;
    }
}
