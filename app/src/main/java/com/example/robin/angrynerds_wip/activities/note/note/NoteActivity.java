package com.example.robin.angrynerds_wip.activities.note.note;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.note.note.data.NoteData;
import com.example.robin.angrynerds_wip.activities.note.note.gui.NoteGui;
import com.example.robin.angrynerds_wip.activities.note.note.logic.NoteApplicationLogic;
import com.example.robin.angrynerds_wip.activities.note.note.logic.event_handler.EventDispersion;

public class NoteActivity extends AppCompatActivity {

    private NoteData mNoteData;
    private NoteGui mNoteGui;
    private NoteApplicationLogic mNoteApplicationLogic;
    private EventDispersion mEventDispersion;
    private boolean mIsActive;

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
        mNoteGui = new NoteGui(this);
    }

    private void initApplicationLogic () {
        //TODO Leere Notiz bzw. übergebene Notiz einfügen
        mNoteApplicationLogic = new NoteApplicationLogic(mNoteGui, mNoteData);
    }

    private void initEventDispersion() {
        mEventDispersion = new EventDispersion(mNoteApplicationLogic);
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
        mNoteApplicationLogic.onActivityReturned(requestCode, resultCode, data);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        mEventDispersion.onCreateContextMenu(menu, view, menuInfo);
    }

    //Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.note_menu, menu);
        return true;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return mEventDispersion.onContextItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mNoteGui.getNoteImageContainer().removeAllViews();
        initGUI();
        mNoteApplicationLogic.onConfigurationChanged(mNoteGui);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //mNoteApplicationLogic.onPause();
    }

    @Override
    public void onBackPressed() {
        mNoteApplicationLogic.onBackPressed();
        super.onBackPressed();
    }

    @Override
    public void onStart() {
        super.onStart();
        mIsActive = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        mIsActive = false;
    }

    public boolean isActive(){
        return  mIsActive;
    }
}
