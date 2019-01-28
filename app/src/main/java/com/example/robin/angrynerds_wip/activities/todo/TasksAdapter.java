package com.example.robin.angrynerds_wip.activities.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.data.models.utils.Task;

import java.util.ArrayList;

public class TasksAdapter extends ArrayAdapter {

    private final Context mContext;
    private final ArrayList<Task> mTasks;
    private TodoApplicationLogic mTodoApplicationLogic;

    public TasksAdapter(Context context, int resource, ArrayList<Task> values, TodoApplicationLogic pTodoApplicationLogic) {
        super(context, -1, values);
        mContext = context;
        mTasks = values;
        mTodoApplicationLogic = pTodoApplicationLogic;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =
                (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);

        // Lookup view for data population
        EditText description = (EditText) rowView.findViewById(R.id.edit_todo_task_description);
        CheckBox status = (CheckBox) rowView.findViewById(R.id.edit_todo_task_status);
        ImageView deleteButton = (ImageView) rowView.findViewById(R.id.edit_todo_task_deleteButton);

        if (position == mTasks.size() - 1)
        {
            deleteButton.setVisibility(View.INVISIBLE);
            description.setOnTouchListener(mTodoApplicationLogic.getTouchListener());
        }

        // Get the data item for this position
        Task task = (Task)mTasks.get(position);
        // Populate the data into the template view using the data object
        description.setId(position);
        description.setText(task.getDescription());
        description.addTextChangedListener(new TextWatcher(mTodoApplicationLogic, description));
        status.setId(position);
        status.setChecked(task.getStatus());
        deleteButton.setId(position);
        deleteButton.setOnClickListener(mTodoApplicationLogic.getClickListener());

        return rowView;
    }
}
