package com.example.robin.angrynerds_wip.activities.note;

import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.utils.MockData;


public class ApplicationLogic {

    private Note mNote;
    private Gui mGui;

    public ApplicationLogic(Note note, Gui gui) {
        mNote = note;
        mGui = gui;
        initGui();
        initListener();
    }

    public int getImageCount(){
        return mGui.getmNoteImages().size();
    }

    private void initGui() {
        dataToGui();
    }

    private void initListener() {
        ClickListener clickListener;

        clickListener = new ClickListener(this);
        mGui.getmNoteTitle().setOnClickListener(clickListener);
        mGui.getmNoteImageContainer().setOnClickListener(clickListener);
        mGui.getmNoteTags().setOnClickListener(clickListener);
        for(LinearLayout mImage:mGui.getmNoteImages()){
            mImage.setOnClickListener(clickListener);
        }
    }

    public void dataToGui() {
        mGui.setmNoteTitle(mNote.getTitle());
        //TODO images
        mGui.setmNoteDescription(mNote.getDescription());
        mGui.setmNoteTags(mNote.getTags());
        mGui.setBackgroundColor(mNote.getColor());
    }

    public void onActivityReturned(int requestCode, int resultCode, Intent data) {
    }

    public void onBackPressed() {}

    public void onTitleClicked() {
        mGui.getmNoteTitle().setCursorVisible(true);
    }

    public void onImageClicked(int id){
        //String id = String.valueOf(mGui.getmNoteImageContainer().getId());
        mGui.displayToast("Image with ID " + id + " Clicked!");
    }
}
