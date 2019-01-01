package com.example.robin.angrynerds_wip.activities.note.tageditor;

import android.app.Activity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.robin.angrynerds_wip.R;

class Gui {

    private RelativeLayout mBackground;
    private ListView mListView;
    private Button mAddButton;

    Gui(TagActivity activity) {
        activity.setContentView(R.layout.activity_note_tagoverview);

        mBackground = activity.findViewById(R.id.id_note_tagOverview_background);
        mListView = activity.findViewById(R.id.id_note_tagOverview_listView);
        mAddButton = activity.findViewById(R.id.id_note_tagOverview_addButton);
    }

    //Getter
    public RelativeLayout getmBackground() {
        return mBackground;
    }
    public ListView getmListView() {
        return mListView;
    }
    public Button getmAddButton() {
        return mAddButton;
    }

    //Setter
    public void setmBackground(RelativeLayout mBackground) {
        this.mBackground = mBackground;
    }
    public void setmListView(ListView mListView) {
        this.mListView = mListView;
    }
    public void setmAddButton(Button mAddButton) {
        this.mAddButton = mAddButton;
    }

    void initiateListView(RowViewAdapter adapter){
        mListView.setAdapter(adapter);
    }

    void displayToast(Activity activity, String s) {
        Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
    }

    int getListViewItemCount(){
        return mListView.getChildCount();
    }
}
