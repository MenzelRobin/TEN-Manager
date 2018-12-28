package com.example.robin.angrynerds_wip.activities.note;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.robin.angrynerds_wip.R;

public class Gui {

    private EditText mNoteTitle;
    private HorizontalScrollView mNoteImageView;
    private LinearLayout mNoteImageContainer;
    private EditText mNoteDescription;
    private TextView mNoteTags;

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
    //Setters
    public void setmNoteTitle(EditText mNoteTitle) {
        this.mNoteTitle = mNoteTitle;
    }
    public void setmNoteImageView(HorizontalScrollView mNoteImageView) {
        this.mNoteImageView = mNoteImageView;
    }
    public void setmNoteImageContainer(LinearLayout mNoteImageContainer) {
        this.mNoteImageContainer = mNoteImageContainer;
    }
    public void setmNoteDescription(EditText mNoteDescription) {
        this.mNoteDescription = mNoteDescription;
    }
    public void setmNoteTags(TextView mNoteTags) {
        this.mNoteTags = mNoteTags;
    }


    
    public Gui(Init activity) {

        activity.setContentView(R.layout.activity_note);

        mNoteTitle = activity.findViewById(R.id.id_note_title);
        mNoteImageView = activity.findViewById(R.id.id_note_horizontalScrollView);
        mNoteImageContainer = activity.findViewById(R.id.id_note_linearImageContainer);
        mNoteDescription = activity.findViewById(R.id.id_note_description);
        mNoteTags = activity.findViewById(R.id.id_note_tags);
    }

    // methods to change view attributes
/*
    public void setColorInView(int color) {
        mView.setBackground(new ColorDrawable(color));
    }

    public void setRedSeekBarProgress(int progress) {
        mRedSeekBar.setProgress(progress);
    }

    public void setGreenSeekBarProgress(int progress) {
        mGreenSeekBar.setProgress(progress);
    }

    public void setBlueSeekBarProgress(int progress) {
        mBlueSeekBar.setProgress(progress);
    }
*/
}
