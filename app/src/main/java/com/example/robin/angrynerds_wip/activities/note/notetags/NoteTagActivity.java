package com.example.robin.angrynerds_wip.activities.note.notetags;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.note.note.data.NoteConstants;
import com.example.robin.angrynerds_wip.data.models.utils.Colors;

import java.util.ArrayList;

public class NoteTagActivity extends AppCompatActivity {

    private Gui mGui;
    private ApplicationLogic mApplicationLogic;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        initGUI();

        //Get TagList from Intent
        try{
        initApplicationLogic(extras.getStringArrayList(NoteConstants.INTENT_ID_TAGLIST),
                extras.getInt(NoteConstants.INTENT_ID_COLOR), extras.getInt(NoteConstants.INTENT_ID_ACCENTCOLOR));
        }
        catch(NullPointerException e){
            Log.e("Error",e.getMessage());
            ArrayList<String> taglist = new ArrayList<>();
            taglist.add("");
            initApplicationLogic(taglist, Colors.COLORS[0], Colors.DARKER_ACCENT_COLORS[0]);
            mGui.displayToast(this, "Die Liste konnte nicht geladen werden.");
        }
    }


    private void initGUI () {
        mGui = new Gui(this);
    }

    private void initApplicationLogic (ArrayList<String> tagList, int color, int accentColor) {
        mApplicationLogic = new ApplicationLogic(tagList, mGui, this, color, accentColor);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note_tagoverview_menu, menu);
        return true;
    }
}
