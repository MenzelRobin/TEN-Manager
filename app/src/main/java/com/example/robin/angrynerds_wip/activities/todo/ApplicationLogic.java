package com.example.robin.angrynerds_wip.activities.todo;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.view.View;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.data.models.utils.Task;
import com.example.robin.angrynerds_wip.data.models.utils.TasksAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

//import de.fhdw.bfwi316b.set.colorchooser.activities.ActivityUtilities;
//import de.fhdw.bfwi316b.set.colorchooser.activities.Data;

public class ApplicationLogic {

    //private Data mData;

    private Gui mGui;
    private DialogFragment datePicker;
    private Activity mActivity;
    private TasksAdapter mTaskAdapter;

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
        createList();
    }

    private void initListener() {
        ClickListener clickListener;

        clickListener = new ClickListener(this);
        mGui.getmStartDate().setOnClickListener(clickListener);
        mGui.getmEndDate().setOnClickListener(clickListener);

        //mGui.getmCheckBox().setOnClickListener(clickListener);
        //mGui.getmRowLayout().setOnClickListener(clickListener);
    }


    public void dataToGui() {
        Date date = Calendar.getInstance().getTime();

        mGui.setDate(formatDate(date), mGui.getmStartDate());
        mGui.setDate(formatDate(date), mGui.getmEndDate());
    }

    //to receive Date from DatePicker Fragment
    public void receiveDate(Date date) {
        //mGui.setDate(datePicker.getDayOfMonth() + "." + (datePicker.getMonth() + 1) + "." + datePicker.getYear(), mActiveDatePickerButton);
        mGui.setDate(formatDate(date), mActiveDatePickerButton);
    }

    public String formatDate(Date date){
        DateFormat displayFormat = new SimpleDateFormat("EEEE', ' dd. MMMM yyyy", Locale.GERMAN);
        return displayFormat.format(date);
    }

    public void showDatePickerDialog(View v){
        datePicker = new DatePickerFragment();
        datePicker.show(mActivity.getFragmentManager(), "DatePicker");
        mActiveDatePickerButton = v;
    }

    //Hier wird die Liste erzeugt
    public void createList(){
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task("Erste Aufgabe", false));
        tasks.add(new Task("Zweite Aufgabe", true));
        tasks.add(new Task("Dritte Aufgabe", false));
        tasks.add(new Task("Dritte Aufgabe", false));
        tasks.add(new Task("Dritte Aufgabe", false));
        tasks.add(new Task("Dritte Aufgabe",true));
        mGui.setmChoiceMode();
        TasksAdapter adapter =
                new TasksAdapter(
                        mActivity, //Die aktuelle Activity
                        R.layout.rowlayout, // ID des Layouts für ale Listen-Elemente
                        tasks); // Die Liste der Elemente
        mGui.setmAdapter(adapter);

        checkProgress(tasks);
    }

    public void checkProgress(ArrayList<Task> arrayList){
        int trueChecked = 0;
        int allChecker = 0;

        for(Task task: arrayList){

            if(task.getStatus() == true){
                trueChecked = trueChecked + 1;
            }

            allChecker = allChecker + 1;
        }
        mGui.setmProgressText(Integer.toString(trueChecked) + " / " + Integer.toString(allChecker));
    }

    public void onActivityReturned(int requestCode, int resultCode, Intent data) {
    }

    public void onOkButtonClicked() {}

    public void onBackPressed() {    }


}