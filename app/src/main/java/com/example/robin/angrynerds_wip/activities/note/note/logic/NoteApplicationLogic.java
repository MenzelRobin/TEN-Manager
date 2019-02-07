package com.example.robin.angrynerds_wip.activities.note.note.logic;

import android.content.res.Configuration;

import com.example.robin.angrynerds_wip.activities.note.note.data.NoteData;
import com.example.robin.angrynerds_wip.activities.note.note.gui.NoteGui;
import com.example.robin.angrynerds_wip.activities.note.note.logic.listener_watcher.NoteListenerHandler;
import com.example.robin.angrynerds_wip.activities.note.note.logic.listener_watcher.NoteListenerInitializer;

// Authored by Joscha Nassenstein
public class NoteApplicationLogic {

    private NoteGui mNoteGui;
    private NoteData mNoteData;
    private NoteImageImportLogic mImageImport;
    private NoteNavigationLogic mNoteNavigationLogic;
    private NoteAsyncLoadingLogic mNoteAsyncLoadingLogic;
    private NoteListenerInitializer mNoteListenerInitializer;
    private NoteGuiRefresherLogic mNoteGuiRefresherLogic;
    private NoteListenerHandler mNoteClickHandler;
    private NoteImagePopupLogic mNoteImagePopupLogic;


    public NoteApplicationLogic(NoteGui pGui, NoteData pNoteData) {
        mNoteGui = pGui;
        mNoteData = pNoteData;
        initData();
        mNoteGuiRefresherLogic = new NoteGuiRefresherLogic(this);
        mNoteNavigationLogic = new NoteNavigationLogic(this);
        mNoteAsyncLoadingLogic = new NoteAsyncLoadingLogic(this);
        mNoteListenerInitializer = new NoteListenerInitializer(this);
        mNoteClickHandler = new NoteListenerHandler(this);
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

    public NoteImageImportLogic getImageImport() {
        return mImageImport;
    }

    public NoteImagePopupLogic getNoteImagePopupLogic() {
        return mNoteImagePopupLogic;
    }

    public NoteGuiRefresherLogic getNoteGuiRefresherLogic() {
        return mNoteGuiRefresherLogic;
    }

    public NoteListenerHandler getNoteClickHandler() {
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
        return mNoteData.getNotePreviewImages().size();
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

    public void saveAndReturnToOverview() {
        mNoteNavigationLogic.saveAndReturnToOverview();
    }

    public void onBackPressed() {
        saveAndReturnToOverview();
    }


    //Initialises Landscape or Portrait Activity with NoteData, rescales ImageOverlay if displayed
    public void onConfigurationChanged(NoteGui pGui, Configuration pConfiguration) {
        this.mNoteGui = pGui;
        mNoteListenerInitializer.initListener();
        mNoteGuiRefresherLogic.dataToGui();
        mNoteImagePopupLogic.changeConfiguration(pConfiguration);
    }

    public void initImageImportObject() {
        mImageImport = new NoteImageImportLogic(mNoteData.getActivity());
    }
}
