package com.example.robin.angrynerds_wip.activities.note.tageditor;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.robin.angrynerds_wip.data.models.utils.MockData;

public class NoteTagActivity extends AppCompatActivity {

    private Gui mGui;
    private ApplicationLogic mApplicationLogic;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initGUI();
        initApplicationLogic();
    }


    private void initGUI () {
        mGui = new Gui(this);
    }

    private void initApplicationLogic () {
        mApplicationLogic = new ApplicationLogic(MockData.getNoteSample(), mGui, this);
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        //NoteData.saveDataInBundle(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mApplicationLogic.onActivityReturned(requestCode, resultCode, data);
    }

    // Called when the Orientation of the App is changed
    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        initGUI();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //mApplicationLogic.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();   //default action
        mApplicationLogic.onBackPressed();   // customized action
    }
}
