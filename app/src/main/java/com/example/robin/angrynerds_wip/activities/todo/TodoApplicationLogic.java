package com.example.robin.angrynerds_wip.activities.todo;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;
import com.example.robin.angrynerds_wip.data.models.utils.Task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

//import de.fhdw.bfwi316b.set.colorchooser.activities.ActivityUtilities;
//import de.fhdw.bfwi316b.set.colorchooser.activities.Data;

public class TodoApplicationLogic {

    //private Data mData;

    private Gui mGui;
    private Data mData;
    private DialogFragment datePicker;
    private AppCompatActivity mActivity;
    private TasksAdapter mTaskAdapter;

    private View mActiveDatePickerButton; // der Button, mit dem der DatePicker geöffnet wurde


    //Hier muss noch Data rein
    public TodoApplicationLogic(Gui gui, AppCompatActivity pActivity, Data pData) {
        mActivity = pActivity;
        mGui = gui;
        mData = pData;
        initGui();
        initListener();
    }



    private void initGui() {
        //initialize Toolbar including menu and back button
        mActivity.setSupportActionBar(mGui.getmToolbar());
        mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        mActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);

        dataToGui();
        createList();
    }

    private void initListener() {
        ClickListener clickListener;
        clickListener = new ClickListener(this);
        MenuItemClickListener menuItemClickListener;
        menuItemClickListener = new MenuItemClickListener(this);

        mGui.getmToolbar().setNavigationOnClickListener(clickListener);
        mGui.getmToolbar().setOnMenuItemClickListener(menuItemClickListener);
        mGui.getmStartDate().setOnClickListener(clickListener);
        mGui.getmEndDate().setOnClickListener(clickListener);

        //mGui.getmCheckBox().setOnClickListener(clickListener);
        //mGui.getmRowLayout().setOnClickListener(clickListener);
    }


    public void dataToGui() {
        Date date = Calendar.getInstance().getTime();

        Todo todo = mData.getmTodo();
        mGui.setmTitle(todo.getTitle());
        mGui.setmText(todo.getNote());
        mGui.setColor(todo.getColor(), todo.getAccentColor());
        mGui.setDate(formatDate(todo.getStartDate()), mGui.getmStartDate());
        mGui.setDate(formatDate(todo.getEndDate()), mGui.getmEndDate());
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

    //Return to overview if back pressed / Event deleted / toolbar navigation
    public void returnToOverview() {
        mActivity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.slide_out_right);
        mActivity.finish();
    }

    //Toolbar menu is clicked
    public void onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.todo_action_settings) {
            mData.deleteTodo();
            returnToOverview();
        }
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