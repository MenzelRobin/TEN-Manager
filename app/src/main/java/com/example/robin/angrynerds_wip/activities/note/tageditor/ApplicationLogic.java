package com.example.robin.angrynerds_wip.activities.note.tageditor;

import android.app.Activity;
import android.content.Intent;
import com.example.robin.angrynerds_wip.data.models.tens.Note;


class ApplicationLogic {

    private Note mNote;
    private Gui mGui;
    private RowViewAdapter mAdapter;
    private ClickListener clickListener;

    ApplicationLogic(Note note, Gui gui, Activity activity) {
        mNote = note;
        mGui = gui;
        mAdapter = new RowViewAdapter(activity,mNote.getTags(), this);
        initGui();
        initListener();
    }
    //Getter
    public Note getmNote() {
        return mNote;
    }
    public RowViewAdapter getmAdapter() {
        return mAdapter;
    }
    public ClickListener getClickListener() {
        return clickListener;
    }
    //Setter
    public void setmNote(Note mNote) {
        this.mNote = mNote;
    }
    public void setmAdapter(RowViewAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }
    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    private void initGui(){
        mGui.getmBackground().setBackgroundColor(mNote.getColor());
        mGui.initiateListView(mAdapter);
    }

    private void initListener() {
        clickListener = new ClickListener(this);

        mGui.getmAddButton().setOnClickListener(clickListener);
    }

    void onActivityReturned(int requestCode, int resultCode, Intent data) { }

    void onBackPressed() {

    }

    void onTagsClicked(){
        //TODO hier neue Activity aufrufen
    }

    void onAddButtonClicked() {
        mNote.getTags().add("");
        mAdapter.notifyDataSetChanged();
    }

    void onDeleteButtonClicked(int id){
        mNote.getTags().remove(id);
        mAdapter.notifyDataSetChanged();
    }

    int getListViewItemCount(){
        return mGui.getListViewItemCount();
    }
}
