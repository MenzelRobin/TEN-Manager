package com.example.robin.angrynerds_wip.activities.note.note.gui;

import android.app.Activity;
import android.content.res.Configuration;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.note.note.data.gui_oriented.IContainer;
import com.example.robin.angrynerds_wip.activities.note.note.data.gui_oriented.ImageContainer;
import com.example.robin.angrynerds_wip.activities.note.note.NoteActivity;

import java.util.ArrayList;

public class NoteGui {

    private RelativeLayout mBackground;
    private EditText mNoteTitle;
    //private HorizontalScrollView mNoteImageViewPortrait;
    //private ScrollView mNoteImageViewLandscape;
    private LinearLayout mNoteImageContainer;
    private EditText mNoteDescription;
    private TextView mNoteTags;
    private Toolbar mToolbar;
    private FrameLayout mProgressBarHolder;

    //TODO implement separators?
    //private View mViewSeparator1;
    //private View mViewSeparator2;
    //private View mViewSeparator3;

    public NoteGui(NoteActivity pActivity) {

        pActivity.setContentView(R.layout.activity_note);

        mBackground = pActivity.findViewById(R.id.id_note_background);
        mNoteTitle = pActivity.findViewById(R.id.id_note_title);

        //Initiates according to screen orientation
        if(pActivity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            //Horizontally scrollable Image Gallery in Portrait Mode
            //mNoteImageViewPortrait = pActivity.findViewById(R.id.id_note_ImageScrollView_Horizontal);
            mNoteImageContainer = pActivity.findViewById(R.id.id_note_linearImageContainer_Horizontal);
        }
        else{
            //Vertically scrollable Image Gallery in Landscape Mode
            //mNoteImageViewLandscape = pActivity.findViewById(R.id.id_note_ImageScrollView_Vertical);
            mNoteImageContainer = pActivity.findViewById(R.id.id_note_linearImageContainer_Vertical);
        }

        mNoteDescription = pActivity.findViewById(R.id.id_note_description);
        mNoteTags = pActivity.findViewById(R.id.id_note_tags);
        mToolbar = pActivity.findViewById(R.id.id_note_toolbar);
        mProgressBarHolder = pActivity.findViewById(R.id.noteProgressBarHolder);

        //TODO implement separators?
        //mViewSeparator1 = pActivity.findViewById(R.id.id_note_separate1);
        //mViewSeparator2 = pActivity.findViewById(R.id.id_note_separate2);
        //mViewSeparator3 = pActivity.findViewById(R.id.id_note_separate3);

        mNoteTags.setMovementMethod(new ScrollingMovementMethod());

        //Set toolbar
        pActivity.setSupportActionBar(mToolbar);
        pActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        pActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    //Getters
    public EditText getNoteTitle() {
        return mNoteTitle;
    }
    public LinearLayout getNoteImageContainer() {
        return mNoteImageContainer;
    }
    public EditText getNoteDescription() {
        return mNoteDescription;
    }
    public TextView getNoteTags() {
        return mNoteTags;
    }
    public Toolbar getToolbar() {return mToolbar;}
    //Setters
    public void setNoteTitle(String pNoteTitle) {
        this.mNoteTitle.setText(pNoteTitle);
    }
    public void setNoteImageContainer(ArrayList<IContainer> pImageContainers) {
        //Adds ImageViews to NoteData
        for(IContainer mImage : pImageContainers){
            mNoteImageContainer.addView(mImage.getImageContainer());
        }
    }
    public void setNoteDescription(String pNoteDescription) { this.mNoteDescription.setText(pNoteDescription); }
    public void setNoteTags(ArrayList<String> pNoteTags) { this.mNoteTags.setText(formatTags(pNoteTags)); }
    public void setColors(int pColor, int pAccentColor){
        mBackground.setBackgroundColor(pColor);
        mToolbar.setBackgroundColor(pAccentColor);

        //TODO implement separators?
        //mViewSeparator1.setBackgroundColor(pAccentColor);
        //mViewSeparator2.setBackgroundColor(pAccentColor);
        //mViewSeparator3.setBackgroundColor(pAccentColor);

        //TODO does not work
        //mNoteTitle.setHighlightColor(pAccentColor);
        //mNoteDescription.setHighlightColor(pAccentColor);
    }

    //Formats tags to display in TextView
    private String formatTags(ArrayList<String> pNoteTags) {
        String concatString = "";
        int size = pNoteTags.size();
        for(int i = 0; i < size; i++){
            concatString += "#" + pNoteTags.get(i);
            if(i<size-1)
                concatString += "              ";
        }
        return concatString;
    }

    //Displays a message on screen
    public void displayToast(Activity pActivity, String pText) {
        Toast.makeText(pActivity, pText, Toast.LENGTH_SHORT).show();
    }

    public void addSingleAnimatedImage(ImageContainer pImageContainer) {

        AlphaAnimation anim = new AlphaAnimation(0,  1);
        anim.setDuration(500);
        anim.setRepeatMode(Animation.REVERSE);
        int position = mNoteImageContainer.getChildCount()-1;
        mNoteImageContainer.addView(pImageContainer.getImageContainer(), position);
        pImageContainer.getImageView().startAnimation(anim);

    }

    public void startLoadingSpinner(){
        AlphaAnimation inAnimation = new AlphaAnimation(0f, 9f);
        inAnimation.setDuration(200);
        mProgressBarHolder.setAnimation(inAnimation);
        mProgressBarHolder.setVisibility(View.VISIBLE);
    }

    public void stopLoadingSpinner(){
        AlphaAnimation outAnimation = new AlphaAnimation(9f, 0f);
        outAnimation.setDuration(200);
        mProgressBarHolder.setAnimation(outAnimation);
        mProgressBarHolder.setVisibility(View.GONE);

    }

    public void disableAll(){
        this.mNoteDescription.setEnabled(false);
        this.mNoteTitle.setEnabled(false);
        this.mNoteTags.setEnabled(false);
    }

    public void enableAll(){
        this.mNoteDescription.setEnabled(true);
        this.mNoteTitle.setEnabled(true);
        this.mNoteTags.setEnabled(true);
    }
}
