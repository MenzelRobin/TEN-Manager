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

    public NoteGui(NoteActivity activity) {

        activity.setContentView(R.layout.activity_note);

        mBackground = activity.findViewById(R.id.id_note_background);
        mNoteTitle = activity.findViewById(R.id.id_note_title);

        //Initiates according to screen orientation
        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            //Horizontally scrollable Image Gallery in Portrait Mode
            //mNoteImageViewPortrait = activity.findViewById(R.id.id_note_ImageScrollView_Horizontal);
            mNoteImageContainer = activity.findViewById(R.id.id_note_linearImageContainer_Horizontal);
        }
        else{
            //Vertically scrollable Image Gallery in Landscape Mode
            //mNoteImageViewLandscape = activity.findViewById(R.id.id_note_ImageScrollView_Vertical);
            mNoteImageContainer = activity.findViewById(R.id.id_note_linearImageContainer_Vertical);
        }

        mNoteDescription = activity.findViewById(R.id.id_note_description);
        mNoteTags = activity.findViewById(R.id.id_note_tags);
        mToolbar = activity.findViewById(R.id.id_note_toolbar);
        mProgressBarHolder = activity.findViewById(R.id.noteProgressBarHolder);

        //TODO implement separators?
        //mViewSeparator1 = activity.findViewById(R.id.id_note_separate1);
        //mViewSeparator2 = activity.findViewById(R.id.id_note_separate2);
        //mViewSeparator3 = activity.findViewById(R.id.id_note_separate3);

        mNoteTags.setMovementMethod(new ScrollingMovementMethod());

        //Set toolbar
        activity.setSupportActionBar(mToolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
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
    public void setNoteTitle(String mNoteTitle) {
        this.mNoteTitle.setText(mNoteTitle);
    }
    public void setNoteImageContainer(ArrayList<IContainer> imageContainers) {
        //Adds ImageViews to NoteData
        for(IContainer mImage : imageContainers){
            mNoteImageContainer.addView(mImage.getImageContainer());
        }
    }
    public void setNoteDescription(String mNoteDescription) { this.mNoteDescription.setText(mNoteDescription); }
    public void setNoteTags(ArrayList<String> mNoteTags) { this.mNoteTags.setText(formatTags(mNoteTags)); }
    public void setColors(int color, int accentColor){
        mBackground.setBackgroundColor(color);
        mToolbar.setBackgroundColor(accentColor);

        //TODO implement separators?
        //mViewSeparator1.setBackgroundColor(accentColor);
        //mViewSeparator2.setBackgroundColor(accentColor);
        //mViewSeparator3.setBackgroundColor(accentColor);

        //TODO does not work
        //mNoteTitle.setHighlightColor(accentColor);
        //mNoteDescription.setHighlightColor(accentColor);
    }

    //Formats tags to display in TextView
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

    //Displays a message on screen
    public void displayToast(Activity activity, String s) {
        Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
    }

    public void addSingleAnimatedImage(ImageContainer imageContainer) {

        AlphaAnimation anim = new AlphaAnimation(0,  1);
        anim.setDuration(500);
        anim.setRepeatMode(Animation.REVERSE);
        int position = mNoteImageContainer.getChildCount()-1;
        mNoteImageContainer.addView(imageContainer.getImageContainer(), position);
        imageContainer.getImageView().startAnimation(anim);

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
