package com.example.robin.angrynerds_wip.activities.web_image_import;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.NavigationParameterConstants;
import com.example.robin.angrynerds_wip.activities.note.note.data.NoteConstants;
import com.example.robin.angrynerds_wip.activities.note.note.data.NoteData;
import com.example.robin.angrynerds_wip.activities.note.note.gui.NoteGui;
import com.example.robin.angrynerds_wip.activities.note.note.logic.NoteApplicationLogic;
import com.example.robin.angrynerds_wip.activities.note.note.logic.listener_watcher.EventDispersion;
import com.example.robin.angrynerds_wip.data.repository.DataContextManager;

// Authored by Jan Beilfuß
public class WebImportActivity extends AppCompatActivity {

    private WebImportGui mWebImportGui;
    private WebImportData mWebImportData;
    private WebImportLogic mWebImportLogic;
    private boolean mIsActive;

    @Override
    public void onCreate (Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        DataContextManager.initDatabase(this.getApplicationContext());
        this.initData();
        this.initGUI();
        this.initApplicationLogic();

    }

    private void initGUI () {
        this.mWebImportGui = new WebImportGui(this);
    }

    public WebImportLogic getmWebImportLogic() {
        return mWebImportLogic;
    }

    private void initApplicationLogic () {
        this.mWebImportLogic = new WebImportLogic(mWebImportData, mWebImportGui);
    }

    private void initData(){
        int color = getIntent().getExtras().getInt(NavigationParameterConstants.COLOR);
        String searchtTerm = getIntent().getStringExtra(NavigationParameterConstants.SEARCHTERM);
        this.mWebImportData = new WebImportData(this, searchtTerm, color);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == NoteConstants.WRITE_EXTERNAL_STORAGE_PERMISSION_REQUESTCODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          //  this.mNoteApplicationLogic.getImageImport().importImageFromGallery();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration pNewConfig) {
        super.onConfigurationChanged(pNewConfig);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
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

