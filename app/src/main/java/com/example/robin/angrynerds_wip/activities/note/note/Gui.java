package com.example.robin.angrynerds_wip.activities.note.note;

import android.app.Activity;
import android.content.res.Configuration;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.View;
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
    private Toolbar mToolbar;

    //TODO implement separators?
    //private View mViewSeparator1;
    //private View mViewSeparator2;
    //private View mViewSeparator3;

    Gui(NoteActivity activity) {

        activity.setContentView(R.layout.activity_note);

        mBackground = activity.findViewById(R.id.id_note_background);
        mNoteTitle = activity.findViewById(R.id.id_note_title);

        //Initiates according to screen orientation
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
        mToolbar = activity.findViewById(R.id.id_note_toolbar);

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
    EditText getNoteTitle() {
        return mNoteTitle;
    }
    LinearLayout getNoteImageContainer() {
        return mNoteImageContainer;
    }
    EditText getNoteDescription() {
        return mNoteDescription;
    }
    TextView getNoteTags() {
        return mNoteTags;
    }
    Toolbar getToolbar() {return mToolbar;}
    //Setters
    void setNoteTitle(String mNoteTitle) {
        this.mNoteTitle.setText(mNoteTitle);
    }
    void setNoteImageContainer(ArrayList<IContainer> imageContainers) {
        //Adds ImageViews to NoteData
        for(IContainer mImage : imageContainers){
            mNoteImageContainer.addView(mImage.getImageContainer());
        }
    }
    void setNoteDescription(String mNoteDescription) { this.mNoteDescription.setText(mNoteDescription); }
    void setNoteTags(ArrayList<String> mNoteTags) { this.mNoteTags.setText(formatTags(mNoteTags)); }
    void setColors(int color, int accentColor){
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
    void displayToast(Activity activity, String s) {
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
}
