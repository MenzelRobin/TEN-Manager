package com.example.robin.angrynerds_wip.activities.note.notetags;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.robin.angrynerds_wip.R;

import java.util.ArrayList;

// Authored by Joscha Nassenstein
class ListViewAdapter extends ArrayAdapter<String>{

    private final Context context;
    private ApplicationLogic applicationLogic;
    private final ArrayList<String> values;

    //Custom Adapter for tagoverview_rowlayout
    ListViewAdapter(Context context, ArrayList<String> values, ApplicationLogic applicationLogic) {
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
        EditText textView = rowView.findViewById(R.id.id_note_tagOverview_listItemText);
        ImageButton deleteButton = rowView.findViewById(R.id.id_note_tagOverview_deleteButton);
        textView.setText(values.get(position));
        textView.setId(position);
        textView.addTextChangedListener(new TextWatcher(applicationLogic, textView));
        deleteButton.setId(position);
        deleteButton.setOnClickListener(applicationLogic.getClickListener());

        return rowView;
    }
}
