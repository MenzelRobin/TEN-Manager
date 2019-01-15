package com.example.robin.angrynerds_wip.activities.note.tageditor;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.data.models.utils.MockData;

import java.util.ArrayList;

public class NoteTagActivity extends AppCompatActivity {

    private NoteGui mGui;
    private ApplicationLogic mApplicationLogic;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        initGUI();

        //Get TagList from Intent
        try{
        initApplicationLogic(extras.getStringArrayList("taglist"), extras.getInt("color"));
        }
        catch(NullPointerException e){
            Log.e("Error",e.getMessage());
            ArrayList<String> taglist = new ArrayList<>();
            taglist.add("");
            initApplicationLogic(taglist, this.getResources().getColor(R.color.bgColor1));
            mGui.displayToast(this, "Error getting TagList from NoteActivity.");
        }
    }


    private void initGUI () {
        mGui = new NoteGui(this);
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
        mApplicationLogic.onBackPressed();
        super.onBackPressed();
    }
}
