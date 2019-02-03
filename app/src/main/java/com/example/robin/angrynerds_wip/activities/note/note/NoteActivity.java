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
import android.view.WindowManager;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.note.note.data.NoteData;
import com.example.robin.angrynerds_wip.activities.note.note.gui.NoteGui;
import com.example.robin.angrynerds_wip.activities.note.note.logic.NoteApplicationLogic;
import com.example.robin.angrynerds_wip.activities.note.note.logic.listener_watcher.EventDispersion;
import com.example.robin.angrynerds_wip.data.repository.DataContextManager;

public class NoteActivity extends AppCompatActivity {

    private NoteData mNoteData;
    private NoteGui mNoteGui;
    private NoteApplicationLogic mNoteApplicationLogic;
    private EventDispersion mEventDispersion;
    private boolean mIsActive;

    @Override
    public void onCreate (Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);

        String id = getIntent().getStringExtra("ID");
        DataContextManager.initDatabase(this.getApplicationContext());
        if(id==null) {
            mNoteData = new NoteData(this);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
            initGUI(true);
        }
        else{
            mNoteData = new NoteData(this, getIntent().getStringExtra("ID"));
            initGUI(false);
        }
        initApplicationLogic();
        initEventDispersion();
    }

    private void initGUI (boolean pNewNote) {
        mNoteGui = new NoteGui(this, pNewNote);
    }

    private void initApplicationLogic () {
        mNoteApplicationLogic = new NoteApplicationLogic(mNoteGui, mNoteData);
    }

    private void initEventDispersion() {
        mEventDispersion = new EventDispersion(mNoteApplicationLogic);
    }

    @Override
    protected void onActivityResult(int pRequestCode, int pResultCode, Intent pData) {
        super.onActivityResult(pRequestCode, pResultCode, pData);
        mNoteApplicationLogic.getNoteNavigationLogic().onActivityReturned(pRequestCode, pResultCode, pData);
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
        initGUI(false);
        mNoteApplicationLogic.onConfigurationChanged(mNoteGui, pNewConfig);
        Log.d("display", "Konfig: " + (int)(pNewConfig.screenHeightDp*getApplicationContext().getResources().getDisplayMetrics().density) + " " + (int)(pNewConfig.screenWidthDp*getApplicationContext().getResources().getDisplayMetrics().density));
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
