package com.example.robin.angrynerds_wip.overview.noteFragment;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.robin.angrynerds_wip.R;

public class OverviewNoteGui {

    View mView;
    RelativeLayout mOverviewNote;
    TextView mTitle;
    TextView mDescription;

    // Constructor
    public OverviewNoteGui(){
    }

    // Adds the Fragment view to this Object
    public void addView(View pView){
        mView = pView;
        mOverviewNote = mView.findViewById(R.id.id_overview_note);
        mTitle = mView.findViewById(R.id.id_overview_note_textView_title);
        mDescription = mView.findViewById(R.id.id_overview_note_textView_description);
    }

    // Returns mOverviewNote
    public RelativeLayout getOverviewNote(){
        return mOverviewNote;
    }

    public void setTitle(String pTitle){
        mTitle.setText(pTitle);
    }

    public void setColor(int pColor){
        mOverviewNote.setBackgroundColor(pColor);
    }

    public void setDescription(String pDescription){
        mDescription.setText(pDescription);
    }
}
