package com.example.robin.angrynerds_wip.activities.note.note;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;

public class NoteActivity extends AppCompatActivity {

    private NoteData mNoteData;
    private Gui mGui;
    private ApplicationLogic mApplicationLogic;
    private EventDispersion mEventDispersion;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initGUI();
        initApplicationLogic();
        initEventDispersion();
    }

    private void initData(){
        mNoteData = new NoteData(this);
    }

    private void initGUI () {
        mGui = new Gui(this);
    }

    private void initApplicationLogic () {
        //TODO Leere Notiz bzw. übergebene Notiz einfügen
        mApplicationLogic = new ApplicationLogic(mGui, mNoteData);
    }

    private void initEventDispersion() {
        mEventDispersion = new EventDispersion(mApplicationLogic);
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        mNoteData.saveDataInBundle(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        mNoteData.restoreDataFromBundle(savedInstanceState);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mApplicationLogic.onActivityReturned(requestCode, resultCode, data);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        mEventDispersion.onCreateContextMenu(menu, view, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // return super.onContextItemSelected(item);
        return mEventDispersion.onContextItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mGui.getmNoteImageContainer().removeAllViews();

        initGUI();
        //mApplicationLogic.setmGui(mGui);
        mApplicationLogic.onConfigurationChanged(mGui);
        /*initGUI();
        mNoteData.addImageButton();
        mApplicationLogic.setmGui(mGui);
        mApplicationLogic.dataToGui();*/
    }

    @Override
    protected void onPause() {
        super.onPause();
        //mApplicationLogic.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();   //default action
        //mApplicationLogic.onBackPressed();   // customized action
    }
}
