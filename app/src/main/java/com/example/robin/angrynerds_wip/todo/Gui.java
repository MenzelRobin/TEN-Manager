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

public class Gui extends AppCompatActivity{

    private EditText mTitle;
    private EditText mText;
    private Button mStartDate;
    private Button mEndDate;
    private TextView mProgressText;
    private ListView mTasks;

    public Gui(Init activity) {
        activity.setContentView(R.layout.activity_todo);

        mTitle = activity.findViewById(R.id.edit_todo_title);
        mText = activity.findViewById(R.id.edit_todo_text);
        mStartDate = activity.findViewById(R.id.edit_todo_startDate);
        mEndDate = activity.findViewById(R.id.edit_todo_endDate);
        mProgressText = activity.findViewById(R.id.edit_todo_progressText);
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
        mTasks.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        TasksAdapter adapter =
                new TasksAdapter(
                        activity, // Die aktuelle Activity
                        R.layout.rowlayout, // ID des Layouts für alle Listen-Elemente
                        tasks); // Die Liste der Elemente
        mTasks.setAdapter(adapter);
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

    public TextView getmProgressText() {
        return mProgressText;
    }

    public ListView getmTasks() { return mTasks; }


    // methods to change view attributes
    public void setmProgressText(String text) {
        mProgressText.setText(text);
    }

    public void setDate(String s, View v) {
        ((Button)v).setText(s);
    }

}
