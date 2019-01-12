package com.example.robin.angrynerds_wip.activities.note.note;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.note.tageditor.NoteTagActivity;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.repository.DatabaseConstants;
import com.example.robin.angrynerds_wip.data.services.Read;

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
        initData();
        //initGui();
        initListener();
    }

    private void initData(){
        String noteId = "029682c2-cf76-4db0-b52e-c21be9f4b41e";
        mNoteData.setColors(noteId);
        NoteLoader noteLoader = new NoteLoader(this, mNoteData);
        noteLoader.loadNote(noteId);
        // try{
        //     Thread.sleep(200);
        // } catch (InterruptedException e){
        //     Log.e("Sleep", "Sleep error");
        // }

    }

    private void initGui() {
        dataToGui();
    }

    //Initialise Click- and Text-Listeners
    public void initListener() {
        mClickListener = new ClickListener(this);
        MenuItemClickListener menuItemClickListener = new MenuItemClickListener(this);

        mGui.getNoteTitle().setOnClickListener(mClickListener);
        mGui.getNoteImageContainer().setOnClickListener(mClickListener);
        mGui.getNoteTags().setOnClickListener(mClickListener);
        for(IContainer mImage: mNoteData.getNoteImageContainers()){
            setImageClickListener(mImage);
        }
        mGui.getNoteTitle().addTextChangedListener(new TextWatcher(this, mGui.getNoteTitle()));
        mGui.getNoteDescription().addTextChangedListener(new TextWatcher(this, mGui.getNoteDescription()));
        mGui.getToolbar().setNavigationOnClickListener(mClickListener);
        mGui.getToolbar().setOnMenuItemClickListener(menuItemClickListener);
    }

    void addSingleImage(Bitmap bitmap){

        mNoteData.addImageContainer(bitmap);
        ImageContainer imageContainer = (ImageContainer) mNoteData.getNoteImageContainers().get(mNoteData.getNoteImageContainers().size()-2);
        mGui.addSingleAnimatedImage(imageContainer);
        initListener();
    }

    //Return Number of ImageContainers in NoteData
    int getImageCount(){
        return mNoteData.getNoteImageContainers().size();
    }

    //Checks ImageContainer for specific ID
    boolean checkImageID(int id){
        return mNoteData.checkImageID(id);
    }

    //Deletes Image from NoteData
    void deleteImage(int id) {
        mNoteData.deleteImage(id);
        refreshImages();
    }

    //Displays a message on screen
    void displayToast(String s){
        mGui.displayToast(mNoteData.getActivity(), s);
    }

    //Sets ClickListener on mImageContainer
    private void setImageClickListener(IContainer imageContainer){
        imageContainer.getImageContainer().setOnClickListener(mClickListener);
    }

    //sends data to gui elements
    private void dataToGui() {

        //Set colors
        mGui.setColors(mNoteData.getNote().getColor(), mNoteData.getNote().getAccentColor());

        //Set content
        mGui.setNoteTitle(mNoteData.getNote().getTitle());
        mGui.setNoteDescription(mNoteData.getNote().getDescription());
        mGui.setNoteTags(mNoteData.getNote().getTags());

        //set images
        refreshImages();
    }

    //Refreshes ScrollView with Images
    private void refreshImages(){
        mGui.getNoteImageContainer().removeAllViews();
        mGui.setNoteImageContainer(mNoteData.getNoteImageContainers());
    }

    //Returns to OverViewActivity
    void returnToOverview() {
        //Intent intent = new Intent(mNoteData.getActivity(), OverviewInit.class);
        //intent.putExtra("ID", 5);
        //mActivity.startActivity(intent); // Activity Starten
        //mActivity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.slide_out_right);
    }

    //Get result from external Activity (Camera, Gallery, TagEditor)
    void onActivityReturned(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case 0:
                if(resultCode == -1){
                    //TODO
                    Bitmap bm = BitmapFactory.decodeFile(mImageImport.getCurrentPhotoPath());
                    mNoteData.saveImage(bm);
                    setImageClickListener(mNoteData.getImageContainer(mNoteData.getNoteImageContainers().size()-2));
                    refreshImages();
                }
                break;
            case 1:
                if(resultCode == -1){
                    Uri selectedImage = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(mNoteData.getActivity().getContentResolver(), selectedImage);
                        mNoteData.saveImage(bitmap);
                        setImageClickListener(mNoteData.getImageContainer(mNoteData.getNoteImageContainers().size()-2));
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
                        mNoteData.getNote().setTags(extras.getStringArrayList("taglist"));
                        mGui.setNoteTags(mNoteData.getNote().getTags());
                    }
                    catch(NullPointerException e){
                        Log.e("Error", e.getMessage());
                        displayToast("Error getting TagList from NoteTagActivity.");
                    }
                }
        }
    }

    void onBackPressed() {}

    //Set cursor of Title visible (invisible when Activity is started)
    void onTitleClicked() {
        mGui.getNoteTitle().setCursorVisible(true);
    }

    //Displays image if ImageContainer is clicked, starts ImageImport functionality if IconContainer is clicked
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

    //Starts TagEditor Activity
    void onTagsClicked(){
        Intent intent = new Intent(mNoteData.getActivity(), NoteTagActivity.class);
        intent.putExtra("taglist", mNoteData.getNote().getTags());
        intent.putExtra("color", mNoteData.getNote().getColor());
        mNoteData.getActivity().startActivityForResult(intent, 2);
    }

    //Initialises Landscape or Portrait Activity with NoteData, rescales ImageOverlay if displayed
    void onConfigurationChanged(Gui gui) {
        this.mGui = gui;
        mNoteData.addImageButton();
        initListener();
        dataToGui();
        //setImageClickListener(mNoteData.getNoteImageContainers().get(mNoteData.getNoteImageContainers().size()-1));
        if(mImageOverlay != null && mImageOverlay.isDisplayed()){
            mImageOverlay.changeOrientation((mNoteData.getActivity()));
        }
    }

    //Inserts User Input into NoteData
    void onTextChanged(String text, View view) {
        if(view.getId() == R.id.id_note_title){ mNoteData.setTitle(text);} //R.id.id_event_editText_title
        else if(view.getId() == R.id.id_note_description){
            mNoteData.setDescription(text);} //R.id.id_event_editText_title
    }

    //Handles Toolbar Selection
    void onMenuItemClicked(MenuItem item) {
        if(item.getItemId() == R.id.note_action_settings){
            //TODO delete note;
            returnToOverview();}
    }

    public NoteData getNoteData() {
        return mNoteData;
    }


    //Async task that loads the Note

}
