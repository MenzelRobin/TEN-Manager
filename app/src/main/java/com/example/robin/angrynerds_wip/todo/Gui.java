package com.example.robin.angrynerds_wip.todo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;
import com.example.robin.angrynerds_wip.data.models.utils.MockData;
import com.example.robin.angrynerds_wip.data.models.utils.Task;
import com.example.robin.angrynerds_wip.data.models.utils.TasksAdapter;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Gui extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText mTitle;
    private EditText mText;
    private Button mStartDate;
    private Button mEndDate;
    private SeekBar mProgressBar;
    private ListView mTasks;

    public Gui(Init activity) {
        activity.setContentView(R.layout.activity_todo);

        mTitle = activity.findViewById(R.id.edit_todo_title);
        mText = activity.findViewById(R.id.edit_todo_text);
        mStartDate = activity.findViewById(R.id.edit_todo_startDate);
        mEndDate = activity.findViewById(R.id.edit_todo_endDate);
        mProgressBar = activity.findViewById(R.id.edit_todo_progressBar);
        mTasks = activity.findViewById(R.id.edit_todo_tasks);

        ArrayList<String> strings = new ArrayList<String>();
        strings.add("test");

        // TODO: Muss an diese Klasse übergeben werden
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task("Erste Aufgabe", false));
        tasks.add(new Task("Zweite Aufgabe", true));
        tasks.add(new Task("Dritte Aufgabe", false));
        tasks.add(new Task("Dritte Aufgabe", false));
        tasks.add(new Task("Dritte Aufgabe", false));
        tasks.add(new Task("Dritte Aufgabe", true));
        tasks.add(new Task("Dritte Aufgabe", false));
        tasks.add(new Task("Dritte Aufgabe", true));
        tasks.add(new Task("Dritte Aufgabe", false));
        tasks.add(new Task("Dritte Aufgabe", false));
        tasks.add(new Task("Dritte Aufgabe", true));
        mTasks.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        TasksAdapter adapter =
                new TasksAdapter(
                        activity, // Die aktuelle Activity
                        R.layout.rowlayout, // ID des Layouts für alle Listen-Elemente
                        tasks); // Die Liste der Elemente
        mTasks.setAdapter(adapter);

        /*
        java.util.Calendar cal = java.util.Calendar.getInstance();
        int year = cal.get(java.util.Calendar.YEAR);
        int month = cal.get(java.util.Calendar.MONTH);
        int day = cal.get(java.util.Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(getActivity(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,
                year,month,day);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

            }
        };
        */
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.set(java.util.Calendar.YEAR, year);
        c.set(java.util.Calendar.MONTH, month);
        c.set(java.util.Calendar.DAY_OF_MONTH, dayOfMonth);

        String currentDateString = DateFormat.getDateInstance().format(c.getTime());

        //mGui.getmStartDate().set
    }

    public void openDate(){
        android.support.v4.app.DialogFragment datePicker = new DatePickerFragment();

        datePicker.show(getSupportFragmentManager(), "date picker");
        }

    // getter to access views
    public EditText getmTitle() {
        return mTitle;
    }

    public EditText getmText() {
        return mText;
    }

    public Button getmStartDate() {
        return mStartDate;
    }

    public Button getmEndDate() { return mEndDate; }

    public SeekBar getmProgressBar() {
        return mProgressBar;
    }

    public ListView getmTasks() { return mTasks; }


    // methods to change view attributes
    public void setmProgressBar(int progress) {
        mProgressBar.setProgress(progress);
    }
}
