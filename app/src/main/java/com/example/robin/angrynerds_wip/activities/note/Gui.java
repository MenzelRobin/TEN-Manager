package com.example.robin.angrynerds_wip.activities.note;

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

    private Init mActivity;

    private RelativeLayout mBackground;
    private EditText mNoteTitle;
    private HorizontalScrollView mNoteImageView;
    private LinearLayout mNoteImageContainer;
    private EditText mNoteDescription;
    private TextView mNoteTags;
    private ArrayList<IContainer> mNoteImages;

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
    ArrayList<IContainer> getmNoteImages() { return mNoteImages; }
    //Setters
    void setBackgroundColor(int bgColor) { mBackground.setBackgroundColor(bgColor);}
    void setmNoteTitle(String mNoteTitle) {
        this.mNoteTitle.setText(mNoteTitle);
    }
    void setmNoteImageView(HorizontalScrollView mNoteImageView) {
        this.mNoteImageView = mNoteImageView;
    }
    void setmNoteImageContainer(LinearLayout mNoteImageContainer) {
        this.mNoteImageContainer = mNoteImageContainer;
    }
    void setmNoteDescription(String mNoteDescription) {
        this.mNoteDescription.setText(mNoteDescription);
    }
    void setmNoteTags(ArrayList<String> mNoteTags) {
        // TODO method for string insertion
        String displayText = "";
        int size = mNoteTags.size();
        for(int i = 0; i < size; i++){
            displayText += "#" + mNoteTags.get(i);
            if(i<size-1)
                displayText += "              ";
        }
        this.mNoteTags.setText(displayText);
    }

    Gui(Init activity) {

        activity.setContentView(R.layout.activity_note);

        mActivity = activity;
        mBackground = activity.findViewById(R.id.id_note_background);
        mNoteTitle = activity.findViewById(R.id.id_note_title);
        mNoteImageView = activity.findViewById(R.id.id_note_horizontalScrollView);
        mNoteImageContainer = activity.findViewById(R.id.id_note_linearImageContainer);
        mNoteDescription = activity.findViewById(R.id.id_note_description);
        mNoteTags = activity.findViewById(R.id.id_note_tags);
        mNoteImages = new ArrayList<>();

        for(int i = 1; i < 3; i++){
            mNoteImages.add(new ImageContainer(mActivity,i));
        }
        Drawable drawable = ContextCompat.getDrawable(mActivity,R.drawable.ic_add_a_photo_grey_24dp);
        mNoteImages.add(new IconContainer(mActivity, 0, drawable));

        for(IContainer mImage : mNoteImages){
            mNoteImageContainer.addView(mImage.getImageContainer());
        }
    }

    void displayToast(String s) {
        Toast.makeText(mActivity, s, Toast.LENGTH_SHORT).show();
    }

    void displayImage(int id) {
        Bitmap image = mNoteImages.get(id).getImage();

        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        AlertDialog dialog = builder.create();
        LayoutInflater inflater = mActivity.getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.activity_note_image_overlay, null);
        dialog.setView(dialogLayout);
        ImageView imageView = dialogLayout.findViewById(R.id.id_note_imageContainer);
        imageView.setImageBitmap(image);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.show();
    }

    void importMedia(){
        MediaImport mediaImport = new MediaImport(mActivity);
    }

    boolean checkImageID(int id){
        for(IContainer mImage : mNoteImages){
            if(mImage.getImageContainer().getId()==id)
                return true;
        }
        return false;
    }
}
