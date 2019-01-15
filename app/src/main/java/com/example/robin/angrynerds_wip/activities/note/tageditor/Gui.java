package com.example.robin.angrynerds_wip.activities.note.tageditor;

import android.app.Activity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.robin.angrynerds_wip.R;

class NoteGui {

    private RelativeLayout mBackground;
    private ListView mListView;

    NoteGui(NoteTagActivity activity) {
        activity.setContentView(R.layout.activity_note_tagoverview);

        mBackground = activity.findViewById(R.id.id_note_tagOverview_background);
        mListView = activity.findViewById(R.id.id_note_tagOverview_listView);
    }

    //Getter
    RelativeLayout getBackground() {
        return mBackground;
    }
    ListView getListView() {
        return mListView;
    }

    //Sets adapter to ListView
    void initiateListView(RowViewAdapter adapter){
        mListView.setAdapter(adapter);
    }

    //Displays a message on screen
    void displayToast(Activity activity, String s) {
        Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
    }

    //Return number of Strings in TagList
    int getListViewItemCount(){
        return mListView.getChildCount();
    }
}
