package com.example.robin.angrynerds_wip.activities.overview.noteFragment;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.overview.superClasses.OverviewFragmentGui;

public class OverviewNoteGui extends OverviewFragmentGui {
    /* Yannick-Luca Rüttgers
    Manages the GUI for the Note Fragment
     */

    private RelativeLayout mOverviewNote;
    private TextView mTitle;
    private TextView mDescription;

    // Adds the Fragment view to this Object
    public void addView(View pView){
        super.addView(pView);
        mChecked = mView.findViewById(R.id.id_overview_note_imageView_checked);
        mUnchecked = mView.findViewById(R.id.id_overview_note_imageView_unchecked);
        mOverviewNote = mView.findViewById(R.id.id_overview_note);
        mTitle = mView.findViewById(R.id.id_overview_note_textView_title);
        mDescription = mView.findViewById(R.id.id_overview_note_textView_description);
    }

    // Returns mOverviewNote
    public RelativeLayout getOverviewNote(){
        return mOverviewNote;
    }

    // Sets Title
    public void setTitle(String pTitle){
        mTitle.setText(pTitle);
    }

    // Sets Color
    public void setColor(int pColor){
        mOverviewNote.setBackgroundColor(pColor);
    }

    // Sets Description
    public void setDescription(String pDescription){
        mDescription.setText(pDescription);
    }
}
