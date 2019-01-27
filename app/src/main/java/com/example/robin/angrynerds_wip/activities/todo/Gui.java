package com.example.robin.angrynerds_wip.activities.todo;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.example.robin.angrynerds_wip.R;

public class Gui extends AppCompatActivity{

    private EditText mTitle;
    private EditText mText;
    private Button mStartDate;
    private Button mEndDate;
    private TextView mProgressText;
    private ListView mTasks;
    private LinearLayout mRowLayout;
    private CheckBox mCheckBox;
    private Toolbar mToolbar;


    public Gui(Init activity) {
        activity.setContentView(R.layout.activity_todo);

        mToolbar = activity.findViewById(R.id.id_todo_toolbar);
        mTitle = activity.findViewById(R.id.edit_todo_title);
        mText = activity.findViewById(R.id.edit_todo_text);
        mStartDate = activity.findViewById(R.id.edit_todo_startDate);
        mEndDate = activity.findViewById(R.id.edit_todo_endDate);
        mProgressText = activity.findViewById(R.id.edit_todo_progressText);
        mTasks = activity.findViewById(R.id.edit_todo_tasks);
        mRowLayout = activity.findViewById(R.id.edit_todo_rowLayout);
        mCheckBox = activity.findViewById(R.id.edit_todo_task_status);

        //Arraylist in ApplicationLogic umgezogen, funktioniert
        /*
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
        */
    }
    

    // getter to access views
    public Toolbar getmToolbar() {return mToolbar;}
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

    public LinearLayout getmRowLayout() {
        return mRowLayout;
    }

    public CheckBox getmCheckBox() {
        return mCheckBox;
    }

    // methods to change view attributes
    public void setmTasks(TasksAdapter adapter){mTasks.setAdapter(adapter);}

    public void setmAdapter(TasksAdapter adapter){
        mTasks.setAdapter(adapter);}

    public void setmChoiceMode(){mTasks.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);}

    public void setmProgressText(String text) {
        mProgressText.setText(text);
    }

    public void setDate(String s, View v) {
        ((Button)v).setText(s);
    }

}
