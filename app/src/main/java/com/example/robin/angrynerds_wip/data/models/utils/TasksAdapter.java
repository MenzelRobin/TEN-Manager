package com.example.robin.angrynerds_wip.data.models.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.robin.angrynerds_wip.R;

import java.util.ArrayList;

public class TasksAdapter extends ArrayAdapter {

    private final Context mContext;
    private final ArrayList<Task> mTasks;
    public TasksAdapter(Context context, int resource, ArrayList<Task> values) {
        super(context, -1, values);
        mContext = context;
        mTasks = values;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =
                (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);

        // Lookup view for data population
        EditText description = (EditText) rowView.findViewById(R.id.task_description);
        CheckBox status = (CheckBox) rowView.findViewById(R.id.task_status);

        // Get the data item for this position
        Task task = (Task)mTasks.get(position);
        // Populate the data into the template view using the data object
        description.setText(task.getDescription());
        status.setChecked(task.getStatus());

        return rowView;
    }
}
