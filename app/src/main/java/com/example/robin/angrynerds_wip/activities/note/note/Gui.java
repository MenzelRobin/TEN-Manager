package com.example.robin.angrynerds_wip.activities.note.note;

import android.app.Activity;
import android.content.res.Configuration;
import android.text.method.ScrollingMovementMethod;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.robin.angrynerds_wip.R;

import java.util.ArrayList;

class Gui {

    private RelativeLayout mBackground;
    private EditText mNoteTitle;
    private HorizontalScrollView mNoteImageViewPortrait;
    private ScrollView mNoteImageViewLandscape;
    private LinearLayout mNoteImageContainer;
    private EditText mNoteDescription;
    private TextView mNoteTags;

    Gui(NoteActivity activity) {

        activity.setContentView(R.layout.activity_note);

        mBackground = activity.findViewById(R.id.id_note_background);
        mNoteTitle = activity.findViewById(R.id.id_note_title);
        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            //Horizontally scrollable Image Gallery in Portrait Mode
            mNoteImageViewPortrait = activity.findViewById(R.id.id_note_ImageScrollView_Horizontal);
            mNoteImageContainer = activity.findViewById(R.id.id_note_linearImageContainer_Horizontal);
        }
        else{
            //Vertically scrollable Image Gallery in Landscape Mode
            mNoteImageViewLandscape = activity.findViewById(R.id.id_note_ImageScrollView_Vertical);
            mNoteImageContainer = activity.findViewById(R.id.id_note_linearImageContainer_Vertical);
        }
        mNoteDescription = activity.findViewById(R.id.id_note_description);
        mNoteTags = activity.findViewById(R.id.id_note_tags);
        mNoteTags.setMovementMethod(new ScrollingMovementMethod());
    }

    //Getters
    EditText getmNoteTitle() {
        return mNoteTitle;
    }
    LinearLayout getmNoteImageContainer() {
        return mNoteImageContainer;
    }
    EditText getmNoteDescription() {
        return mNoteDescription;
    }
    TextView getmNoteTags() {
        return mNoteTags;
    }
    //Setters
    void setBackgroundColor(int bgColor) { mBackground.setBackgroundColor(bgColor);}
    void setmNoteTitle(String mNoteTitle) {
        this.mNoteTitle.setText(mNoteTitle);
    }
    void setmNoteImageContainer(ArrayList<IContainer> imageContainers) {
        for(IContainer mImage : imageContainers){
            mNoteImageContainer.addView(mImage.getImageContainer());
        }
    }
    void setmNoteDescription(String mNoteDescription) {
        this.mNoteDescription.setText(mNoteDescription);
    }
    void setmNoteTags(ArrayList<String> mNoteTags) {
        this.mNoteTags.setText(formatTags(mNoteTags));
    }

    private String formatTags(ArrayList<String> mNoteTags) {
        String concatString = "";
        int size = mNoteTags.size();
        for(int i = 0; i < size; i++){
            concatString += "#" + mNoteTags.get(i);
            if(i<size-1)
                concatString += "              ";
        }
        return concatString;
    }

    void displayToast(Activity activity, String s) {
        Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
    }
}
