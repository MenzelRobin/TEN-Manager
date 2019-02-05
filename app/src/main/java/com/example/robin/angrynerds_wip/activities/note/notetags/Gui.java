package com.example.robin.angrynerds_wip.activities.note.notetags;

import android.app.Activity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.robin.angrynerds_wip.R;

// Authored by Joscha Nassenstein
class Gui {

    private RelativeLayout mBackground;
    private ListView mListView;
    private Button mAddButton;
    private View mSeparator;
    private Toolbar mToolbar;

    Gui(NoteTagActivity pActivity) {
        pActivity.setContentView(R.layout.activity_note_tagoverview);

        mBackground = pActivity.findViewById(R.id.id_note_tagOverview_background);
        mListView = pActivity.findViewById(R.id.id_note_tagOverview_listView);
        mAddButton = pActivity.findViewById(R.id.id_note_tagOverview_addButton);
        mSeparator = pActivity.findViewById(R.id.id_note_tagOverview_separator);
        mToolbar = pActivity.findViewById(R.id.id_note_tagOverview_toolbar);
    }

    //Getter
    RelativeLayout getBackground() {
        return mBackground;
    }
    ListView getListView() {
        return mListView;
    }
    Button getAddButton() { return mAddButton; }
    View getSeparator(){ return mSeparator; }
    Toolbar getToolbar() { return mToolbar; }

    //Sets adapter to ListView
    void initiateListView(ListViewAdapter adapter){
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
