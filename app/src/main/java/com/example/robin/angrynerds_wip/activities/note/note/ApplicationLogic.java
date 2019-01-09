package com.example.robin.angrynerds_wip.activities.note.note;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.note.tageditor.NoteTagActivity;

import java.io.IOException;


class ApplicationLogic {

    private Gui mGui;
    private NoteData mNoteData;
    private ClickListener mClickListener;
    private ImageImport mImageImport;
    private ImageOverlay mImageOverlay;

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
        mGui.getmNoteTitle().addTextChangedListener(new TextWatcher(this, mGui.getmNoteTitle()));
        mGui.getmNoteDescription().addTextChangedListener(new TextWatcher(this, mGui.getmNoteDescription()));
    }

    private void setImageClickListener(IContainer imageContainer){
        imageContainer.getImageContainer().setOnClickListener(mClickListener);
    }

    private void dataToGui() {
        mGui.setmNoteTitle(mNoteData.getmNote().getTitle());
        mGui.setmNoteDescription(mNoteData.getmNote().getDescription());
        mGui.setmNoteTags(mNoteData.getmNote().getTags());
        mGui.setBackgroundColor(mNoteData.getmNote().getColor());
        refreshImages();
    }

    void onActivityReturned(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case 0:
                if(resultCode == -1){
                    //TODO
                    Bitmap bm = BitmapFactory.decodeFile(mImageImport.getmCurrentPhotoPath());
                    mNoteData.saveImage(bm);
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
            case 2:
                if(resultCode == -1){
                    Bundle extras = data.getExtras();
                    try{
                        mNoteData.getmNote().setTags(extras.getStringArrayList("taglist"));
                        mGui.setmNoteTags(mNoteData.getmNote().getTags());
                    }
                    catch(NullPointerException e){
                        Log.e("Error", e.getMessage());
                        displayToast("Error getting TagList from NoteTagActivity.");
                    }
                }
        }
    }

    void onBackPressed() {}

    void onTitleClicked() {
        mGui.getmNoteTitle().setCursorVisible(true);
    }

    void onImageClicked(int id){
        if(id==0){
            mImageImport = new ImageImport(mNoteData.getActivity());
        }
        else{
            View displayMetrics = mNoteData.getActivity().getWindow().findViewById(Window.ID_ANDROID_CONTENT);
            mImageOverlay = new ImageOverlay(mNoteData.getImage(id), displayMetrics.getWidth(), displayMetrics.getHeight());
            mImageOverlay.display(mNoteData.getActivity());
        }
    }

    void onTagsClicked(){
        Intent intent = new Intent(mNoteData.getActivity(), NoteTagActivity.class);
        intent.putExtra("taglist", mNoteData.getmNote().getTags());
        intent.putExtra("color", mNoteData.getmNote().getColor());
        mNoteData.getActivity().startActivityForResult(intent, 2); // Activity Starten
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

    void onConfigurationChanged(Gui gui) {
        this.mGui = gui;
        mNoteData.addImageButton();
        initListener();
        dataToGui();
        //setImageClickListener(mNoteData.getmNoteImageContainers().get(mNoteData.getmNoteImageContainers().size()-1));
        if(mImageOverlay != null && mImageOverlay.isDisplayed()){
            mImageOverlay.changeOrientation((mNoteData.getActivity()));
        }
    }

    void onTextChanged(String text, View view) {
        if(view.getId() == R.id.id_note_title){ mNoteData.setTitle(text);} //R.id.id_event_editText_title
        else if(view.getId() == R.id.id_note_description){
            mNoteData.setDescription(text);} //R.id.id_event_editText_title
    }
}
