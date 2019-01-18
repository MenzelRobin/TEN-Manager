package com.example.robin.angrynerds_wip.activities.note.tageditor;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.robin.angrynerds_wip.R;

class Gui {

    private RelativeLayout mBackground;
    private ListView mListView;

    Gui(NoteTagActivity activity) {
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

    //Hides the keyboard and clears focus from view
    void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Get current focused window
        View view = activity.getCurrentFocus();
        //Create new view if no view is active
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        view.clearFocus();
    }
}
