package com.example.robin.angrynerds_wip.todo;

import android.app.Activity;
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
import android.view.View;
import android.widget.DatePicker;

import com.example.robin.angrynerds_wip.MainActivity;
import com.example.robin.angrynerds_wip.OverviewActivity;
import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.todo.DatePickerFragment;

import java.text.DateFormat;
import java.util.Calendar;

//import de.fhdw.bfwi316b.set.colorchooser.activities.ActivityUtilities;
//import de.fhdw.bfwi316b.set.colorchooser.activities.Data;

public class ApplicationLogic {

    //private Data mData;

    private Gui mGui;
    private DialogFragment datePicker;
    private Activity mActivity;

    private View mActiveDatePickerButton; // der Button, mit dem der DatePicker geöffnet wurde


    //Hier muss noch Data rein
    public ApplicationLogic(Gui gui, Activity pActivity) {
        mActivity = pActivity;
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


    public void dataToGui() {
    }

    //to receive Date from DatePicker Fragment
    public void receiveDate(DatePicker datePicker) {
        mGui.setDate(datePicker.getDayOfMonth() + "." + (datePicker.getMonth() + 1) + "." + datePicker.getYear(), mActiveDatePickerButton);
    }

    public void showDatePickerDialog(View v){
        datePicker = new DatePickerFragment();
        datePicker.show(mActivity.getFragmentManager(), "DatePicker");
        mActiveDatePickerButton = v;
    }

    public void onActivityReturned(int requestCode, int resultCode, Intent data) {
    }

    public void onOkButtonClicked() {}

    public void onBackPressed() {    }


}