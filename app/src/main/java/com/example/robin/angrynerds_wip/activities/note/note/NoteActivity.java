package com.example.robin.angrynerds_wip.activities.note.note;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    public void onCreate (Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);

        String noteID = "";
        Bundle extras = getIntent().getExtras();

        //Get ID from Intent
        try{
            noteID = extras.getString("ID");
        }
        catch(NullPointerException e){
            Log.e("Error",e.getMessage());
        }

        initData();
        initGUI();
        initApplicationLogic(noteID);
        initEventDispersion();
    }

    private void initData(){
        mNoteData = new NoteData(this);
    }

    private void initGUI () {
        mNoteGui = new NoteGui(this);
    }

    private void initApplicationLogic (String pId) {
        //TODO Leere Notiz bzw. übergebene Notiz einfügen
        mNoteApplicationLogic = new NoteApplicationLogic(mNoteGui, mNoteData, pId);
    }

    private void initEventDispersion() {
        mEventDispersion = new EventDispersion(mNoteApplicationLogic);
    }

    @Override
    protected void onActivityResult(int pRequestCode, int pResultCode, Intent pData) {
        super.onActivityResult(pRequestCode, pResultCode, pData);
        mNoteApplicationLogic.onActivityReturned(pRequestCode, pResultCode, pData);
    }

    @Override
    public void onCreateContextMenu(ContextMenu pMenu, View pView, ContextMenu.ContextMenuInfo pMenuInfo) {
        super.onCreateContextMenu(pMenu, pView, pMenuInfo);
        mEventDispersion.onCreateContextMenu(pMenu, pView, pMenuInfo);
    }

    //Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu pMenu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.note_menu, pMenu);
        return true;
    }

    @Override
    public boolean onContextItemSelected(MenuItem pItem) {
        return mEventDispersion.onContextItemSelected(pItem);
    }

    @Override
    public void onConfigurationChanged(Configuration pNewConfig) {
        super.onConfigurationChanged(pNewConfig);
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
