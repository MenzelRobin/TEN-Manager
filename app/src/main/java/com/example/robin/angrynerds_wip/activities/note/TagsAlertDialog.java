package com.example.robin.angrynerds_wip.activities.note;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ListView;

import com.example.robin.angrynerds_wip.R;

import java.util.ArrayList;

class TagsAlertDialog {

    private ArrayList<String> mTags;

    TagsAlertDialog(ArrayList<String> tags){
        mTags = tags;
    }

    void display(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Tags");
        builder.setIcon(R.drawable.ic_sort_darkgrey_24dp);
        AlertDialog dialog = builder.create();
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.activity_note_tags_overlay, null);
        dialog.setView(dialogLayout);
        ListView listView = dialogLayout.findViewById(R.id.id_note_tagsContainer);

        for(String tag : mTags){
            listView.addView(new TagRowContainer(tag, activity));
        }



        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.show();
    }
}
