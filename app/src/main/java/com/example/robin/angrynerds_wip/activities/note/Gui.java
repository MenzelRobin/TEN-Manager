package com.example.robin.angrynerds_wip.activities.note;

import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.robin.angrynerds_wip.R;

import java.util.ArrayList;

public class Gui {

    private Init mActivity;

    private RelativeLayout mBackground;
    private EditText mNoteTitle;
    private HorizontalScrollView mNoteImageView;
    private LinearLayout mNoteImageContainer;
    private EditText mNoteDescription;
    private TextView mNoteTags;
    private ArrayList<LinearLayout> mNoteImages;

    //Getters
    public EditText getmNoteTitle() {
        return mNoteTitle;
    }
    public HorizontalScrollView getmNoteImageView() {
        return mNoteImageView;
    }
    public LinearLayout getmNoteImageContainer() {
        return mNoteImageContainer;
    }
    public EditText getmNoteDescription() {
        return mNoteDescription;
    }
    public TextView getmNoteTags() {
        return mNoteTags;
    }
    public ArrayList<LinearLayout> getmNoteImages() { return mNoteImages; }
    //Setters
    public void setBackgroundColor(int bgColor) { mBackground.setBackgroundColor(bgColor);}
    public void setmNoteTitle(String mNoteTitle) {
        this.mNoteTitle.setText(mNoteTitle);
    }
    public void setmNoteImageView(HorizontalScrollView mNoteImageView) {
        this.mNoteImageView = mNoteImageView;
    }
    public void setmNoteImageContainer(LinearLayout mNoteImageContainer) {
        this.mNoteImageContainer = mNoteImageContainer;
    }
    public void setmNoteDescription(String mNoteDescription) {
        this.mNoteDescription.setText(mNoteDescription);
    }
    public void setmNoteTags(ArrayList<String> mNoteTags) {
        // TODO method for string insertion
        String displayText = "";
        int size = mNoteTags.size();
        for(int i = 0; i < size; i++){
            displayText += "#" + mNoteTags.get(i);
            if(i<size-1)
                displayText += "              ";
        }

        //displayText = displayText.substring(0, displayText.length() - 1);
        this.mNoteTags.setText(displayText);
    }

    public Gui(Init activity) {

        activity.setContentView(R.layout.activity_note);

        mActivity = activity;
        mBackground = activity.findViewById(R.id.id_note_background);
        mNoteTitle = activity.findViewById(R.id.id_note_title);
        mNoteImageView = activity.findViewById(R.id.id_note_horizontalScrollView);
        mNoteImageContainer = activity.findViewById(R.id.id_note_linearImageContainer);
        mNoteDescription = activity.findViewById(R.id.id_note_description);
        mNoteTags = activity.findViewById(R.id.id_note_tags);
        mNoteImages = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            mNoteImages.add(new ImageContainer(mActivity,i).getPicture());
        }

        for(LinearLayout mImage : mNoteImages){
            mNoteImageContainer.addView(mImage);
        }
    }

    public void displayToast(String s) {
        Toast.makeText(mActivity, s, Toast.LENGTH_SHORT).show();
    }
}
