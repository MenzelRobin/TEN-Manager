package com.example.robin.angrynerds_wip.activities.todo;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.example.robin.angrynerds_wip.R;

import org.w3c.dom.Text;

// Author: Florian Rath
public class Gui extends AppCompatActivity{

    private RelativeLayout mRelativeLayout;
    private EditText mTitle;
    private EditText mText;
    private TextView mStartDate;
    private TextView mEndDate;
    private TextView mProgressText;
    private ListView mTasks;
    private LinearLayout mRowLayout;
    private CheckBox mCheckBox;
    private Toolbar mToolbar;
    private ImageView mTaskDeleteButton;
    private View mSeperateView1;
    private View mSeperateView2;
    private View mSeperateView3;
    private View mSeperateView4;


    public Gui(Init activity) {
        activity.setContentView(R.layout.activity_todo);
        mRelativeLayout = activity.findViewById(R.id.edit_todo_mainRelativeLayout);
        mSeperateView1 = activity.findViewById(R.id.edit_todo_view_seperate1);
        mSeperateView2 = activity.findViewById(R.id.edit_todo_view_seperate2);
        mSeperateView3 = activity.findViewById(R.id.edit_todo_view_seperate3);
        mSeperateView4 = activity.findViewById(R.id.edit_todo_view_seperate4);
        mToolbar = activity.findViewById(R.id.id_todo_toolbar);
        mTitle = activity.findViewById(R.id.edit_todo_title);
        mText = activity.findViewById(R.id.edit_todo_text);
        mStartDate = activity.findViewById(R.id.edit_todo_startDate);
        mEndDate = activity.findViewById(R.id.edit_todo_endDate);
        mProgressText = activity.findViewById(R.id.edit_todo_progressText);
        mTasks = activity.findViewById(R.id.edit_todo_tasks);
        mRowLayout = activity.findViewById(R.id.edit_todo_rowLayout);
        mCheckBox = activity.findViewById(R.id.edit_todo_task_status);
        mTaskDeleteButton = activity.findViewById(R.id.edit_todo_task_deleteButton);

        //Arraylist in TodoApplicationLogic umgezogen, funktioniert
    }
    

    // getter to access views
    public Toolbar getmToolbar() {return mToolbar;}
    public EditText getmTitle() {
        return mTitle;
    }

    public EditText getmText() {
        return mText;
    }

    public TextView getmStartDate() {
        return mStartDate;
    }

    public TextView getmEndDate() { return mEndDate; }

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

    public ImageView getmTaskDeleteButton() { return mTaskDeleteButton; }

    // methods to change view attributes
    public void setmTasks(TasksAdapter adapter){mTasks.setAdapter(adapter);}

    public void setmAdapter(TasksAdapter adapter){
        mTasks.setAdapter(adapter);}

    public void setmChoiceMode(){mTasks.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);}

    public void setmProgressText(String text) {
        mProgressText.setText(text);
    }

    public void setDate(String s, View v) {
        ((TextView)v).setText(s);
    }

    public void setmTitle(String pTitle)
    {
        mTitle.setText(pTitle);
    }

    public void setmText(String pText)
    {
        mText.setText(pText);
    }

    public void setColor(int color, int darkColor) {
        mRelativeLayout.setBackground(new ColorDrawable(color));
        mTasks.setBackground(new ColorDrawable(color));
        mToolbar.setBackground(new ColorDrawable(darkColor));
        mSeperateView1.setBackground(new ColorDrawable(darkColor));
        mSeperateView2.setBackground(new ColorDrawable(darkColor));
        mSeperateView3.setBackground(new ColorDrawable(color));
        mSeperateView4.setBackground(new ColorDrawable(darkColor));
        //mSeperateView1.setBackground(new ColorDrawable(darkColor));
        //mSeperateView1.setAlpha((float) 0.5);
    }

    public void setFocusableInTouchmode(boolean value)
    {
        mRelativeLayout.setFocusableInTouchMode(value);
    }

    public AdapterView getListView() {
        return mTasks;
    }

    //Return number of Strings in TagList
    int getTasksItemCount(){
        return mTasks.getChildCount();
    }
}
