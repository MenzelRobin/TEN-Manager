package com.example.robin.angrynerds_wip.activities.note;

import android.content.Intent;

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

    private void initGui() {
        dataToGui();
    }

    private void initListener() {
        ClickListener clickListener;

        clickListener = new ClickListener(this);
        mGui.getmNoteImageContainer().setOnClickListener(clickListener);
        mGui.getmNoteTags().setOnClickListener(clickListener);
    }

    public void dataToGui() {
        mGui.setmNoteTitle(mNote.getTitle());
        //TODO images
        mGui.setmNoteDescription(mNote.getDescription());
        mGui.setmNoteTags(mNote.getTags());
    }

    public void onActivityReturned(int requestCode, int resultCode, Intent data) {
    }

    public void onOkButtonClicked() {}

    public void onBackPressed() {    }

}
