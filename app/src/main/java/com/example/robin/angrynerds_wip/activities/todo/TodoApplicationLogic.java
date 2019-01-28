package com.example.robin.angrynerds_wip.activities.todo;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.text.method.Touch;
import android.view.MenuItem;
import android.view.View;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;
import com.example.robin.angrynerds_wip.data.models.utils.Task;
import com.example.robin.angrynerds_wip.data.services.Update;

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

    private ClickListener mClickListener;
    private TouchListener mTouchListener;
    private CheckedChangeListener mCheckedChangeListener;

    private ArrayList<Task> mTasks;
    private DialogFragment datePicker;
    private AppCompatActivity mActivity;
    private TasksAdapter mTaskAdapter;

    private View mActiveDatePickerButton; // der Button, mit dem der DatePicker geöffnet wurde


    //Hier muss noch Data rein
    public TodoApplicationLogic(Gui gui, AppCompatActivity pActivity, Data pData) {
        mActivity = pActivity;
        mGui = gui;
        mData = pData;
        mTasks = mData.getmTodo().getTasks();
        if (mTasks.get(mTasks.size()-1).getDescription() != "")
        {
            mTasks.add(new Task());
        }
        createList();
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
        mClickListener = new ClickListener(this);
        mTouchListener = new TouchListener(this);
        mCheckedChangeListener = new CheckedChangeListener(this);

        MenuItemClickListener menuItemClickListener;
        menuItemClickListener = new MenuItemClickListener(this);

        mGui.getmToolbar().setNavigationOnClickListener(mClickListener);
        mGui.getmToolbar().setOnMenuItemClickListener(menuItemClickListener);
        mGui.getmStartDate().setOnClickListener(mClickListener);
        mGui.getmEndDate().setOnClickListener(mClickListener);

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
        UpdateTodo();
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
        mGui.setmChoiceMode();
        mTaskAdapter =
                new TasksAdapter(
                        mActivity, //Die aktuelle Activity
                        R.layout.rowlayout, // ID des Layouts für ale Listen-Elemente
                        mTasks,
                        this); // Die Liste der Elemente
        mGui.setmAdapter(mTaskAdapter);

        updateProgress();
    }


    private void addTask() {
        mTasks.add(new Task());
        mTaskAdapter.notifyDataSetChanged();
        mGui.getmTasks().post(new Runnable() {
            @Override
            public void run() {
                mGui.getmTasks().setSelection(mTaskAdapter.getCount() - 1);
            }
        });
        updateProgress();
    }

    public void updateProgress(){
        int trueChecked = 0;
        int allChecker = 0;

        for(Task task: mTasks){
            if(task.getStatus() == true){
                trueChecked = trueChecked + 1;
            }
            allChecker = allChecker + 1;
        }
        allChecker = allChecker - 1;
        mGui.setmProgressText(Integer.toString(trueChecked) + " / " + Integer.toString(allChecker));
        //Hier lag der Fehler bei dem Wechsel der Ansichten, wollen wir uns morgen angucken
        //UpdateTodo();
    }

    void onEditTextClicked() {
        addInputTagField();
    }

    public void onActivityReturned(int requestCode, int resultCode, Intent data) {
    }
    //Remove String from TagList and notify adapter
    void onDeleteButtonClicked(int id){
        mTasks.remove(id);
        mTaskAdapter.notifyDataSetChanged();
        updateProgress();
    }

    //Insert user input into TagList
    void onTaskTextChanged(String s, View mView) {
        mTasks.get(mView.getId()).setDescription(s);
        if(mView.getId() == mTasks.size()-1){
            addInputTagField();
        }
        updateProgress();
    }

    public void onOkButtonClicked() { UpdateTodo(); }

    public void onBackPressed() {  UpdateTodo();  }

    private void addInputTagField() {
        mTasks.add(new Task());
        mTaskAdapter.notifyDataSetChanged();
        mGui.getListView().post(new Runnable() {
            @Override
            public void run() {
                mGui.getListView().setSelection(mTaskAdapter.getCount() - 1);
            }
        });
        updateProgress();
    }

    public ArrayList<Task> getmTasks()
    {
        return mTasks;
    }

    //Return number of Strings in TagList
    int getTasksItemCount(){
        return mGui.getTasksItemCount();
    }

    public ClickListener getClickListener() {
        return mClickListener;
    }
    public TouchListener getTouchListener() { return mTouchListener; }
    public CheckedChangeListener getmCheckedChangeListener() { return mCheckedChangeListener;}

    public void UpdateTodo()
    {
        Todo todo = mData.getmTodo();
        todo.setTitle(mGui.getmTitle().getText().toString());
        todo.setNote(mGui.getmText().getText().toString());

        Update.saveTEN(todo);
    }

}