package com.example.robin.angrynerds_wip.activities.note;

import android.content.Intent;
import android.net.Uri;

import com.example.robin.angrynerds_wip.data.models.tens.Note;


class ApplicationLogic {

    private Note mNote;
    private Gui mGui;

    ApplicationLogic(Note note, Gui gui) {
        mNote = note;
        mGui = gui;
        initGui();
        initListener();
    }

    int getImageCount(){
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
        for(IContainer mImage:mGui.getmNoteImages()){
            mImage.getImageContainer().setOnClickListener(clickListener);
        }
    }

    private void dataToGui() {
        mGui.setmNoteTitle(mNote.getTitle());
        //TODO images
        mGui.setmNoteDescription(mNote.getDescription());
        mGui.setmNoteTags(mNote.getTags());
        mGui.setBackgroundColor(mNote.getColor());
    }

    void onActivityReturned(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case 0:
                if(resultCode == -1){
                    Uri selectedImage = data.getData();
                    //TODO return uri somewhere
                }
                break;
            case 1:
                if(resultCode == -1){
                    Uri selectedImage = data.getData();
                    //TODO return uri somewhere imageview.setImageURI(selectedImage);
                }
                break;
        }
    }

    public void onBackPressed() {}

    void onTitleClicked() {
        mGui.getmNoteTitle().setCursorVisible(true);
    }

    void onImageClicked(int id){
        if(id==0)
            mGui.importMedia();
        else
            mGui.displayImage(id);
    }

    void onTagsClicked(){
        //Code
    }

    boolean checkImageID(int id){
        return mGui.checkImageID(id);
    }

    public void deleteImage(int id) {
        mGui.displayToast(String.valueOf(id));
        //TODO delete image from source
        //dataToGui();
    }
}
