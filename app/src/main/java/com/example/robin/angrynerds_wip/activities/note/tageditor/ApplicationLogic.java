package com.example.robin.angrynerds_wip.activities.note.tageditor;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import java.util.ArrayList;


class ApplicationLogic {

    private ArrayList<String> mTagList;
    private int mColor;
    private Gui mGui;
    private RowViewAdapter mAdapter;
    private ClickListener clickListener;
    private Activity mActivity;

    ApplicationLogic(ArrayList<String> tagList, Gui gui, Activity activity, int color) {
        mTagList = tagList;
        mTagList.add("");
        mGui = gui;
        mActivity = activity;
        mAdapter = new RowViewAdapter(activity,mTagList, this);
        mColor = color;
        initGui();
        initListener();
    }

    ClickListener getClickListener() {
        return clickListener;
    }

    private void initGui(){
        mGui.getBackground().setBackgroundColor(mColor);
        mGui.initiateListView(mAdapter);
    }

    private void initListener() {
        clickListener = new ClickListener(this);
        //mGui.getAddButton().setOnClickListener(clickListener);
    }

    void onActivityReturned(int requestCode, int resultCode, Intent data) { }

    //Return TagList to NoteActivity
    void onBackPressed() {
        Intent resultIntent = new Intent();
        for(int i = 0; i<mTagList.size();i++){
            if(mTagList.get(i).equals("")){
                mTagList.remove(i--);
            }
        }
        resultIntent.putExtra("taglist", mTagList);
        mActivity.setResult(Activity.RESULT_OK, resultIntent);
        mActivity.finish();
    }

    //Remove String from TagList and notify adapter
    void onDeleteButtonClicked(int id){
        mTagList.remove(id);
        mAdapter.notifyDataSetChanged();
        addTagForInput();
    }

    //Return number of Strings in TagList
    int getListViewItemCount(){
        return mGui.getListViewItemCount();
    }

    //Insert user input into TagList
    void onTextChanged(String s, View mView) {
        mTagList.set(mView.getId(),s);
        addTagForInput();
    }

    //Check if tag for new user input is present and add an empty string if it is not
    private void addTagForInput(){
        boolean emptyLine = false;
        for(String tag : mTagList){
            if(tag.equals(""))
                emptyLine = true;
        }
        if(!emptyLine) {
            mTagList.add("");
            mAdapter.notifyDataSetChanged();
        }
    }
}
