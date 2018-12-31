package com.example.robin.angrynerds_wip.activities.note;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.example.robin.angrynerds_wip.data.models.tens.Note;


class ApplicationLogic {

    private Note mNote;
    private Gui mGui;
    private NoteData mNoteData;

    ApplicationLogic(Note note, Gui gui, NoteData noteData) {
        mNote = note;
        mGui = gui;
        mNoteData = noteData;
        initGui();
        initListener();
    }

    int getImageCount(){
        return mNoteData.getmNoteImageContainers().size();
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
        for(IContainer mImage: mNoteData.getmNoteImageContainers()){
            mImage.getImageContainer().setOnClickListener(clickListener);
        }
    }

    private void dataToGui() {
        mGui.setmNoteTitle(mNote.getTitle());
        mGui.setmNoteImageContainer(mNoteData.getmNoteImageContainers());
        mGui.setmNoteDescription(mNote.getDescription());
        mGui.setmNoteTags(mNote.getTags());
        mGui.setBackgroundColor(mNote.getColor());
    }

    void onActivityReturned(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case 0:
                if(resultCode == -1){

                    Bundle extras = data.getExtras();
                    mGui.displayToast(mNoteData.getActivity(), String.valueOf(mNoteData.getmNoteImageContainers().size()));
                    mNoteData.saveImage((Bitmap) extras.get("data"));
                    refreshImages();
                    //TODO update image view
                }
                break;
            case 1:
                if(resultCode == -1){
                    Uri selectedImage = data.getData();
                    if(selectedImage == null)
                        mGui.displayToast(mNoteData.getActivity(), "Image Path Error");
                    else {
                        mNoteData.copyImage(selectedImage);

                        //TODO does not work atm
                    }
                    //TODO update image view
                }
                break;
        }
    }

    void onBackPressed() {}

    void onTitleClicked() {
        mGui.getmNoteTitle().setCursorVisible(true);
    }

    void onImageClicked(int id){
        if(id==0)
            mNoteData.importMedia();
        else{
            ImageAlertDialog imageAlertDialog = new ImageAlertDialog(mNoteData.getImage(id));
            imageAlertDialog.display(mNoteData.getActivity());
        }
    }

    void onTagsClicked(){
        Intent intent = new Intent(mNoteData.getActivity(), com.example.robin.angrynerds_wip.activities.note.tageditor.TagActivity.class);
        mNoteData.getActivity().startActivity(intent); // Activity Starten
    }

    boolean checkImageID(int id){
        return mNoteData.checkImageID(id);
    }

    void deleteImage(int id) {
        //mGui.displayToast(mNoteData.getActivity(), String.valueOf(id));
        mNoteData.deleteImage(id);
        refreshImages();
    }

    private void refreshImages(){
        mGui.getmNoteImageContainer().removeAllViews();
        mGui.setmNoteImageContainer(mNoteData.getmNoteImageContainers());
    }

    void displayToast(String s){
        mGui.displayToast(mNoteData.getActivity(), s);
    }
}
