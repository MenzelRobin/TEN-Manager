package com.example.robin.angrynerds_wip.activities.note.note.logic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.robin.angrynerds_wip.MainActivity;
import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.note.note.data.IContainer;
import com.example.robin.angrynerds_wip.activities.note.note.data.ImageContainer;
import com.example.robin.angrynerds_wip.activities.note.note.data.ImageImport;
import com.example.robin.angrynerds_wip.activities.note.note.data.NoteData;
import com.example.robin.angrynerds_wip.activities.note.note.data.NoteLoader;
import com.example.robin.angrynerds_wip.activities.note.note.gui.ImageOverlay;
import com.example.robin.angrynerds_wip.activities.note.note.gui.NoteGui;
import com.example.robin.angrynerds_wip.activities.note.note.logic.event_handler.ClickListener;
import com.example.robin.angrynerds_wip.activities.note.note.logic.event_handler.MenuItemClickListener;
import com.example.robin.angrynerds_wip.activities.note.note.logic.event_handler.NoteTextWatcher;
import com.example.robin.angrynerds_wip.activities.note.tageditor.NoteTagActivity;
import com.example.robin.angrynerds_wip.data.models.utils.Image;

import java.io.IOException;


public class NoteApplicationLogic {

    private NoteGui mNoteGui;
    private NoteData mNoteData;
    private ClickListener mClickListener;
    private ImageImport mImageImport;
    private ImageOverlay mImageOverlay;

    public NoteApplicationLogic(NoteGui gui, NoteData noteData) {
        mNoteGui = gui;
        mNoteData = noteData;
        initData();
        initGui();
        initListener();
    }

    private void initData(){
        mNoteData.setmNoteApplicationLogic(this);
        String noteId = "0686f41e-097f-4c1c-b2c4-f68071cb97fd";
        mNoteData.setColors(noteId);
        NoteLoader noteLoader = new NoteLoader(this, mNoteData);
        noteLoader.loadNote(noteId);
    }

    private void initGui() {
        dataToGui();
    }

    //Initialise Click- and Text-Listeners
    public void initListener() {
        Log.i("Clicklistener1", "initListener was called");
        mClickListener = new ClickListener(this);
        MenuItemClickListener menuItemClickListener = new MenuItemClickListener(this);

        mNoteGui.getNoteTitle().setOnClickListener(mClickListener);
        mNoteGui.getNoteImageContainer().setOnClickListener(mClickListener);
        mNoteGui.getNoteTags().setOnClickListener(mClickListener);
        for(IContainer mImage: mNoteData.getNoteImageContainers()){
            setImageClickListener(mImage);
        }
        mNoteGui.getNoteTitle().addTextChangedListener(new NoteTextWatcher(this, mNoteGui.getNoteTitle()));
        mNoteGui.getNoteDescription().addTextChangedListener(new NoteTextWatcher(this, mNoteGui.getNoteDescription()));
        mNoteGui.getToolbar().setNavigationOnClickListener(mClickListener);
        mNoteGui.getToolbar().setOnMenuItemClickListener(menuItemClickListener);
    }

    public void addAsyncPreviewImage(Image image){
        Log.i("Clicklistener1", "addSingleImage was called");
        mNoteData.addImageContainer(image);
        ImageContainer imageContainer = (ImageContainer) mNoteData.getNoteImageContainers().get(mNoteData.getNoteImageContainers().size()-2);
        mNoteGui.addSingleAnimatedImage(imageContainer);
        initListener();
    }

    //Return Number of ImageContainers in NoteData
    public int getImageCount(){
        return mNoteData.getNoteImageContainers().size();
    }

    //Checks ImageContainer for specific ID
    public boolean checkImageID(int id){
        return mNoteData.checkImageID(id);
    }

    //Deletes Image from NoteData
    public void deleteImage(int id) {
        mNoteData.deleteImage(id);
        initListener();
        refreshImages();
    }

    //Displays a message on screen
    void displayToast(String s){
        mNoteGui.displayToast(mNoteData.getActivity(), s);
    }

    //Sets ClickListener on mImageContainer
    private void setImageClickListener(IContainer imageContainer){
        imageContainer.getImageContainer().setOnClickListener(mClickListener);
    }

    //sends data to gui elements
    public void dataToGui() {

        //Set colors
        mNoteGui.setColors(mNoteData.getNote().getColor(), mNoteData.getNote().getAccentColor());

        //Set content
        mNoteGui.setNoteTitle(mNoteData.getNote().getTitle());
        mNoteGui.setNoteDescription(mNoteData.getNote().getDescription());
        mNoteGui.setNoteTags(mNoteData.getNote().getTags());

        //set images
        refreshImages();
    }

    //Refreshes ScrollView with Images
    private void refreshImages(){
        mNoteGui.getNoteImageContainer().removeAllViews();
        mNoteGui.setNoteImageContainer(mNoteData.getNoteImageContainers());
    }

    //Returns to OverViewActivity
    public void returnToOverview() {
        mNoteData.executeSaveRoutine();
        mNoteData.finallyDeleteImages();
        //TODO das ist noch falsch, dadurch werden neue Instanzen von der Overview erstellt, obwohl die Alte noch vorhanden ist
        Intent intent = new Intent(this.mNoteData.getActivity().getApplicationContext(), MainActivity.class);
        this.mNoteData.getActivity().getApplicationContext().startActivity(intent); // Activity Starten*/

        //intent.putExtra("ID", 5); // Hier können eigene Parameter hinzugefügt werden.
        //Intent intent = new Intent(mNoteData.getActivity(), OverviewInit.class);
        //intent.putExtra("ID", 5);
        //mActivity.startActivity(intent); // Activity Starten
        //mActivity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.slide_out_right);
    }

    //Get result from external Activity (Camera, Gallery, TagEditor)
    public void onActivityReturned(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case 0:
                if(resultCode == -1){
                    //TODO
                    String path = mImageImport.getCurrentPhotoPath();
                    Bitmap cameraImage = BitmapFactory.decodeFile(path);
                    mNoteData.addImageFromCamera(cameraImage, path);
                    setImageClickListener(mNoteData.getImageContainer(mNoteData.getNoteImageContainers().size()-2));
                    refreshImages();
                }
                break;
            case 1:
                if(resultCode == -1){
                    Uri selectedImage = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(mNoteData.getActivity().getContentResolver(), selectedImage);
                        mNoteData.addImageFromGallery(bitmap);
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
                        mNoteGui.setNoteTags(mNoteData.getNote().getTags());
                    }
                    catch(NullPointerException e){
                        Log.e("Error", e.getMessage());
                        displayToast("Error getting TagList from NoteTagActivity.");
                    }
                }
        }
    }

    public void onBackPressed() {

        returnToOverview();
    }

    //Set cursor of Title visible (invisible when Activity is started)
    public void onTitleClicked() {
        mNoteGui.getNoteTitle().setCursorVisible(true);
    }

    //Displays image if ImageContainer is clicked, starts ImageImport functionality if IconContainer is clicked
    public void onImageClicked(int id){
        if(id==0){
            mImageImport = new ImageImport(mNoteData.getActivity());
        }
        else{
            Bitmap bitmap = mNoteData.getImage(id);
            if(bitmap!=null){
                openImagePopup(bitmap);
            } else{
            }

            //Interessant für Loading Spinner: https://stackoverflow.com/questions/18021148/display-a-loading-overlay-on-android-screen
        }
    }

    public void openImagePopup(Bitmap bitmap) {
        Log.i("NoteRemake", "Activity: " + mNoteData.getActivity());
        if(mNoteData.getActivity().isActive()){
            View displayMetrics = mNoteData.getActivity().getWindow().findViewById(Window.ID_ANDROID_CONTENT);
            mImageOverlay = new ImageOverlay(bitmap, displayMetrics.getWidth(), displayMetrics.getHeight());
            mImageOverlay.display(mNoteData.getActivity());
        }
    }

    //Starts TagEditor Activity
    public void onTagsClicked(){
        Intent intent = new Intent(mNoteData.getActivity(), NoteTagActivity.class);
        intent.putExtra("taglist", mNoteData.getNote().getTags());
        intent.putExtra("color", mNoteData.getNote().getColor());
        mNoteData.getActivity().startActivityForResult(intent, 2);
    }

    //Initialises Landscape or Portrait Activity with NoteData, rescales ImageOverlay if displayed
    public void onConfigurationChanged(NoteGui gui) {
        this.mNoteGui = gui;
        mNoteData.addImageButton();
        initListener();
        dataToGui();
        //setImageClickListener(mNoteData.getNoteImageContainers().get(mNoteData.getNoteImageContainers().size()-1));
        if(mImageOverlay != null && mImageOverlay.isDisplayed()){
            mImageOverlay.changeOrientation((mNoteData.getActivity()));
        }
    }

    //Inserts User Input into NoteData
    public void onTextChanged(String text, View view) {
        if(view.getId() == R.id.id_note_title){ mNoteData.setTitle(text);} //R.id.id_event_editText_title
        else if(view.getId() == R.id.id_note_description){
            mNoteData.setDescription(text);} //R.id.id_event_editText_title
    }

    //Handles Toolbar Selection
    public void onMenuItemClicked(MenuItem item) {
        if(item.getItemId() == R.id.note_action_settings){
            //TODO delete note;
            returnToOverview();}
    }

    public NoteData getNoteData() {
        return mNoteData;
    }

    public void startLoadingSpinner(){
        mNoteGui.disableAll();
        mNoteGui.startLoadingSpinner();
    }
    public void stopLoadingSpinner(){
        mNoteGui.stopLoadingSpinner();
        mNoteGui.enableAll();
    }

    //Async task that loads the Note
}
