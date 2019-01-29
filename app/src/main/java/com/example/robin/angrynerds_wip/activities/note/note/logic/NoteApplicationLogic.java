package com.example.robin.angrynerds_wip.activities.note.note.logic;

import com.example.robin.angrynerds_wip.activities.note.note.data.NoteData;
import com.example.robin.angrynerds_wip.activities.note.note.data.gui_oriented.ImageImport;
import com.example.robin.angrynerds_wip.activities.note.note.gui.NoteGui;

public class NoteApplicationLogic {

    private NoteGui mNoteGui;
    private NoteData mNoteData;
    private ImageImport mImageImport;
    private NoteNavigationLogic mNoteNavigationLogic;
    private NoteAsyncLoadingLogic mNoteAsyncLoadingLogic;
    private NoteListenerInitializer mNoteListenerInitializer;
    private NoteGuiRefresherLogic mNoteGuiRefresherLogic;
    private NoteClickHandler mNoteClickHandler;
    private NoteImagePopupLogic mNoteImagePopupLogic;


    public NoteApplicationLogic(NoteGui pGui, NoteData pNoteData) {
        mNoteGui = pGui;
        mNoteData = pNoteData;
        initData();
        mNoteGuiRefresherLogic = new NoteGuiRefresherLogic(this);
        mNoteNavigationLogic = new NoteNavigationLogic(this);
        mNoteAsyncLoadingLogic = new NoteAsyncLoadingLogic(this);
        mNoteListenerInitializer = new NoteListenerInitializer(this);
        mNoteClickHandler = new NoteClickHandler(this);
        mNoteImagePopupLogic = new NoteImagePopupLogic(this);
        initGui();

        mNoteListenerInitializer.initListener();
    }

    public NoteNavigationLogic getNoteNavigationLogic() {
        return mNoteNavigationLogic;
    }

    public NoteAsyncLoadingLogic getNoteAsyncLoadingLogic() {
        return mNoteAsyncLoadingLogic;
    }

    public NoteListenerInitializer getNoteListenerInitializer() {
        return mNoteListenerInitializer;
    }

    public NoteGui getNoteGui() {
        return mNoteGui;
    }

    public NoteData getNoteData() {
        return mNoteData;
    }

    public ImageImport getImageImport() {
        return mImageImport;
    }

    public NoteImagePopupLogic getNoteImagePopupLogic() {
        return mNoteImagePopupLogic;
    }

    public NoteGuiRefresherLogic getNoteGuiRefresherLogic() {
        return mNoteGuiRefresherLogic;
    }

    public NoteClickHandler getNoteClickHandler() {
        return mNoteClickHandler;
    }

    private void initData() {
        mNoteData.setNoteApplicationLogic(this);
    }

    private void initGui() {
        mNoteGuiRefresherLogic.dataToGui();
    }

    //Return Number of ImageContainers in NoteData
    public int getImageCount() {
        return mNoteData.getNoteImageContainers().size();
    }

    //Checks ImageContainer for specific ID
    public boolean checkImageID(int pId) {
        return mNoteData.checkImageID(pId);
    }

    //Deletes Image from NoteData
    public void deleteImage(int pId) {
        mNoteData.deleteImage(pId);
        mNoteListenerInitializer.initListener();
        mNoteGuiRefresherLogic.refreshImages();
    }

    //sends data to gui elements
    public void dataToGui() {
        mNoteGuiRefresherLogic.dataToGui();
    }

    //NoteNavigationLogic
    public void saveAndReturnToOverview() {
        mNoteNavigationLogic.saveAndReturnToOverview();
    }

    //NoteNavigationLogic
    public void onBackPressed() {
        mNoteNavigationLogic.saveAndReturnToOverview();
    }

    //Initialises Landscape or Portrait Activity with NoteData, rescales ImageOverlay if displayed
    public void onConfigurationChanged(NoteGui pGui) {
        this.mNoteGui = pGui;
        mNoteGuiRefresherLogic.setNoteGui(pGui);

        mNoteData.getNoteDataGui().addImageButton();
        mNoteListenerInitializer.initListener();
        mNoteGuiRefresherLogic.dataToGui();
        mNoteImagePopupLogic.changeConfiguration();
    }

    public void initImageImportObject() {
        mImageImport = new ImageImport(mNoteData.getActivity());
    }
}
