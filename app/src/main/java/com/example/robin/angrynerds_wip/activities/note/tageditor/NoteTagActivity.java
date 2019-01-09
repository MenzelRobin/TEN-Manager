package com.example.robin.angrynerds_wip.activities.note.tageditor;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.robin.angrynerds_wip.data.models.utils.MockData;

import java.util.ArrayList;

public class NoteTagActivity extends AppCompatActivity {

    private Gui mGui;
    private ApplicationLogic mApplicationLogic;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        initGUI();
        initApplicationLogic(extras.getStringArrayList("taglist"), extras.getInt("color"));
    }


    private void initGUI () {
        mGui = new Gui(this);
    }

    private void initApplicationLogic (ArrayList<String> tagList, int color) {
        mApplicationLogic = new ApplicationLogic(tagList, mGui, this, color);
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        //NoteData.saveDataInBundle(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mApplicationLogic.onActivityReturned(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //mApplicationLogic.onPause();
    }

    @Override
    public void onBackPressed() {
        mApplicationLogic.onBackPressed();   // customized action
        super.onBackPressed();   //default action
    }
}
