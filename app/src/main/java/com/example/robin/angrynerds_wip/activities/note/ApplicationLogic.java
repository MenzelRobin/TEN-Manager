package com.example.robin.angrynerds_wip.activities.note;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import java.io.IOException;


class ApplicationLogic {

    private Gui mGui;
    private NoteData mNoteData;
    private ClickListener mClickListener;

    ApplicationLogic(Gui gui, NoteData noteData) {
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
        mClickListener = new ClickListener(this);

        mGui.getmNoteTitle().setOnClickListener(mClickListener);
        mGui.getmNoteImageContainer().setOnClickListener(mClickListener);
        mGui.getmNoteTags().setOnClickListener(mClickListener);
        for(IContainer mImage: mNoteData.getmNoteImageContainers()){
            setImageClickListener(mImage);
        }
    }

    private void setImageClickListener(IContainer imageContainer){
        imageContainer.getImageContainer().setOnClickListener(mClickListener);
    }

    private void dataToGui() {
        mGui.setmNoteTitle(mNoteData.getmNote().getTitle());
        mGui.setmNoteImageContainer(mNoteData.getmNoteImageContainers());
        mGui.setmNoteDescription(mNoteData.getmNote().getDescription());
        mGui.setmNoteTags(mNoteData.getmNote().getTags());
        mGui.setBackgroundColor(mNoteData.getmNote().getColor());
    }

    void onActivityReturned(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case 0:
                if(resultCode == -1){

                    Bundle extras = data.getExtras();
                    mNoteData.saveImage((Bitmap) extras.get("data"));
                    setImageClickListener(mNoteData.getImageContainer(mNoteData.getmNoteImageContainers().size()-2));
                    refreshImages();
                }
                break;
            case 1:
                if(resultCode == -1){
                    Uri selectedImage = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(mNoteData.getActivity().getContentResolver(), selectedImage);
                        mNoteData.saveImage(bitmap);
                        setImageClickListener(mNoteData.getImageContainer(mNoteData.getmNoteImageContainers().size()-2));
                        refreshImages();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
