package com.example.robin.angrynerds_wip.activities.note;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.robin.angrynerds_wip.R;

import java.util.ArrayList;

public class Gui {

    private Init mActivity;
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
            displayText += mNoteTags.get(i);
            if(i<size-1)
                displayText += "              ";
        }

        //displayText = displayText.substring(0, displayText.length() - 1);
        this.mNoteTags.setText(displayText);
    }


    
    public Gui(Init activity) {

        activity.setContentView(R.layout.activity_note);

        mActivity = activity;
        mNoteTitle = activity.findViewById(R.id.id_note_title);
        mNoteImageView = activity.findViewById(R.id.id_note_horizontalScrollView);
        mNoteImageContainer = activity.findViewById(R.id.id_note_linearImageContainer);
        mNoteDescription = activity.findViewById(R.id.id_note_description);
        mNoteTags = activity.findViewById(R.id.id_note_tags);

        /*
        String ExternalStorageDirectoryPath = Environment
                .getExternalStorageDirectory()
                .getAbsolutePath();

        String targetPath = ExternalStorageDirectoryPath + "/test/";

        Toast.makeText(getApplicationContext(), targetPath, Toast.LENGTH_LONG).show();
        File targetDirector = new File(targetPath);

        File[] files = targetDirector.listFiles();
        for (File file : files){
            myGallery.addView(insertPhoto(file.getAbsolutePath()));
        }
        */
        for(int i = 0; i < 5; i++){
            mNoteImageContainer.addView(insertPhoto());
        }
        /*for (int i = 0; i < 5; i++) {
            ImageView imageView = new ImageView(activity);
            imageView.setId(i);
            imageView.setPadding(2, 2, 2, 2);
            imageView.setImageBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_launcher_foreground));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            mNoteImageContainer.addView(imageView);
        }*/
    }

    //TODO eigene Klasse erstellen
    private View insertPhoto(){
        Bitmap bm = BitmapFactory.decodeResource(mActivity.getResources(), R.drawable.note_sample_image1);

        LinearLayout layout = new LinearLayout(mActivity.getApplicationContext());
        layout.setLayoutParams(new LinearLayout.LayoutParams(250, 250));
        layout.setGravity(Gravity.CENTER);

        ImageView imageView = new ImageView(mActivity.getApplicationContext());
        imageView.setLayoutParams(new LinearLayout.LayoutParams(220, 220));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageBitmap(bm);

        layout.addView(imageView);
        return layout;
    }
/*
    // methods to change view attributes

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
