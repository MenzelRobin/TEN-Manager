package com.example.robin.angrynerds_wip.activities.note;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.robin.angrynerds_wip.R;

import java.util.ArrayList;

class Gui {

    private RelativeLayout mBackground;
    private EditText mNoteTitle;
    private HorizontalScrollView mNoteImageView;
    private LinearLayout mNoteImageContainer;
    private EditText mNoteDescription;
    private TextView mNoteTags;

    Gui(Init activity) {

        activity.setContentView(R.layout.activity_note);

        mBackground = activity.findViewById(R.id.id_note_background);
        mNoteTitle = activity.findViewById(R.id.id_note_title);
        mNoteImageView = activity.findViewById(R.id.id_note_horizontalScrollView);
        mNoteImageContainer = activity.findViewById(R.id.id_note_linearImageContainer);
        mNoteDescription = activity.findViewById(R.id.id_note_description);
        mNoteTags = activity.findViewById(R.id.id_note_tags);
    }

    //Getters
    EditText getmNoteTitle() {
        return mNoteTitle;
    }
    HorizontalScrollView getmNoteImageView() {
        return mNoteImageView;
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
    void setmNoteImageView(HorizontalScrollView mNoteImageView) {
        this.mNoteImageView = mNoteImageView;
    }
    /*void setmNoteImageContainer(LinearLayout mNoteImageContainer) {
        this.mNoteImageContainer = mNoteImageContainer;
    }*/
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
