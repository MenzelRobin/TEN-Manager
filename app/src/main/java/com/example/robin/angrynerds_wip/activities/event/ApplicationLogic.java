package com.example.robin.angrynerds_wip.activities.event;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


//import de.fhdw.bfwi316b.set.colorchooser.activities.ActivityUtilities;
//import de.fhdw.bfwi316b.set.colorchooser.activities.Data;
import com.example.robin.angrynerds_wip.data.Queries;

public class ApplicationLogic extends Activity {

    //private Data mData;
    private Gui mGui;

    public ApplicationLogic(Gui gui) {
        mGui = gui;
        initGui();
        initListener();
    }

    private void initGui() {
        dataToGui();
    }

    private void initListener() {
        //Alle CliclListener, die in der GUI genutzt werden
        ClickListener clickListener;

        clickListener = new ClickListener(this);
        mGui.getmEditTextDate().setOnClickListener(clickListener);
        mGui.getmEditTextTime().setOnClickListener(clickListener);
        mGui.getmIconCloseReminder1().setOnClickListener(clickListener);
        mGui.getmIconCloseReminder2().setOnClickListener(clickListener);
        mGui.getmEditTextNewReminder().setOnClickListener(clickListener);
    }

    public void dataToGui() {
    }

    public void onActivityReturned(int requestCode, int resultCode, Intent data) {
    }

    public void onBackPressed() {    }

    public void onDateClicked() {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "date picker");
    }

    public void onTimeClicked() {
    }

    public void removeReminder(int iconCloseReminder1) {
    }

    public void onNewReminderClicked() {
    }
}
