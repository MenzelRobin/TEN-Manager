package com.example.robin.angrynerds_wip.overview.noteFragment;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentGui;

public class OverviewNoteGui extends OverviewFragmentGui {

    RelativeLayout mOverviewNote;
    TextView mTitle;
    TextView mDescription;

    // Constructor
    public OverviewNoteGui(){
    }

    // Adds the Fragment view to this Object
    public void addView(View pView){
        super.addView(pView);
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
