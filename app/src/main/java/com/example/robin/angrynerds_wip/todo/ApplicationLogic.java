package com.example.robin.angrynerds_wip.todo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.DatePicker;

import com.example.robin.angrynerds_wip.MainActivity;
import com.example.robin.angrynerds_wip.OverviewActivity;
import com.example.robin.angrynerds_wip.data.models.tens.Event;

import java.text.DateFormat;
import java.util.Calendar;

//import de.fhdw.bfwi316b.set.colorchooser.activities.ActivityUtilities;
//import de.fhdw.bfwi316b.set.colorchooser.activities.Data;

public class ApplicationLogic {

    //private Data mData;

    private Gui mGui;


    //Hier muss noch Data rein
    public ApplicationLogic(Gui gui) {
        mGui = gui;
        initGui();
        initListener();
    }



    private void initGui() {
        dataToGui();
    }

    private void initListener() {
        ClickListener clickListener;

        clickListener = new ClickListener(this);
        mGui.getmStartDate().setOnClickListener(clickListener);
        mGui.getmEndDate().setOnClickListener(clickListener);
    }

    public void onStartDateClicked(){
        mGui.openDate();
    }

    public void dataToGui() {
    }

    public void onActivityReturned(int requestCode, int resultCode, Intent data) {
    }

    public void onOkButtonClicked() {}

    public void onBackPressed() {    }


}