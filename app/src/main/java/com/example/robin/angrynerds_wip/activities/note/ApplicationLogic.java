package com.example.robin.angrynerds_wip.activities.note;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.example.robin.angrynerds_wip.data.models.tens.Note;


class ApplicationLogic {

    private Note mNote;
    private Gui mGui;
    private Data mData;

    ApplicationLogic(Note note, Gui gui, Data data) {
        mNote = note;
        mGui = gui;
        mData = data;
        initGui();
        initListener();
    }

    int getImageCount(){
        return mData.getmNoteImageContainers().size();
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
        for(IContainer mImage:mData.getmNoteImageContainers()){
            mImage.getImageContainer().setOnClickListener(clickListener);
        }
    }

    private void dataToGui() {
        mGui.setmNoteTitle(mNote.getTitle());
        mGui.setmNoteImageContainer(mData.getmNoteImageContainers());
        mGui.setmNoteDescription(mNote.getDescription());
        mGui.setmNoteTags(mNote.getTags());
        mGui.setBackgroundColor(mNote.getColor());
    }

    void onActivityReturned(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case 0:
                if(resultCode == -1){

                    Bundle extras = data.getExtras();
                    mData.saveImage((Bitmap) extras.get("data"));
                    //TODO update image view
                }
                break;
            case 1:
                if(resultCode == -1){
                    Uri selectedImage = data.getData();
                    if(selectedImage == null)
                        mGui.displayToast(mData.getActivity(), "Image Path Error");
                    else {
                        mData.copyImage(selectedImage);
                        //TODO does not work atm
                    }
                    //TODO update image view
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
            mData.importMedia();
        else{
            ImageAlertDialog imageAlertDialog = new ImageAlertDialog(mData.getImage(id-1));
            imageAlertDialog.display(mData.getActivity());
        }
    }

    void onTagsClicked(){
        TagsAlertDialog tagsAlertDialog = new TagsAlertDialog(mNote.getTags());
        tagsAlertDialog.display(mData.getActivity());
    }

    boolean checkImageID(int id){
        return mData.checkImageID(id);
    }

    public void deleteImage(int id) {
        mGui.displayToast(mData.getActivity(), String.valueOf(id));
        //TODO delete image from source
        //ChangeListener
    }
}
