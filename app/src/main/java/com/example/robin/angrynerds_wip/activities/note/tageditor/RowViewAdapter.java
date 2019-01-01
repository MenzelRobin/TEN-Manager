package com.example.robin.angrynerds_wip.activities.note.tageditor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.robin.angrynerds_wip.R;

import java.util.ArrayList;

public class RowViewAdapter extends ArrayAdapter<String>{

    private final Context context;
    private ApplicationLogic applicationLogic;
    private final ArrayList<String> values;

    RowViewAdapter(Context context, ArrayList<String> values, ApplicationLogic applicationLogic) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
        this.applicationLogic = applicationLogic;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_note_tagoverview_rowlayout, parent, false);
        EditText textView = (EditText) rowView.findViewById(R.id.id_note_tagOverview_listItemText);
        ImageButton deleteButton = (ImageButton) rowView.findViewById(R.id.id_note_tagOverview_deleteButton);
        textView.setText(values.get(position));
        deleteButton.setId(position);
        deleteButton.setOnClickListener(applicationLogic.getClickListener());

        return rowView;
    }
}
