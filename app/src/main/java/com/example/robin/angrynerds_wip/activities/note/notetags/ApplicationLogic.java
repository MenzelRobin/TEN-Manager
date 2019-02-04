package com.example.robin.angrynerds_wip.activities.note.notetags;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.MenuItem;
import android.view.View;

import com.example.robin.angrynerds_wip.R;

import java.util.ArrayList;


class ApplicationLogic {

    private int mColor;
    private int mAccentColor;
    private ArrayList<String> mTagList;
    private Gui mGui;
    private ListViewAdapter mAdapter;
    private ClickListener mClickListener;
    private NoteTagActivity mActivity;

    ApplicationLogic(ArrayList<String> tagList, Gui gui, NoteTagActivity activity, int color, int accentColor) {
        mTagList = tagList;
        if(mTagList.isEmpty())
            mTagList.add("");

        mGui = gui;
        mActivity = activity;
        mAdapter = new ListViewAdapter(activity,mTagList, this);
        mColor = color;
        mAccentColor = accentColor;
        initGui();
        initListener();
    }

    ClickListener getClickListener() {
        return mClickListener;
    }

    private void initGui(){
        mGui.initiateListView(mAdapter);
        initToolbar();
        initColors();
    }

    private void initToolbar() {
        mActivity.setSupportActionBar(mGui.getToolbar());
        mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        mActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initColors(){
        mGui.getBackground().setBackgroundColor(mColor);
        mGui.getToolbar().setBackground(new ColorDrawable(mAccentColor));
        mGui.getSeparator().setBackground(new ColorDrawable(mAccentColor));
        mGui.getSeparator().setAlpha((float)0.5);
    }

    private void initListener() {
        mClickListener = new ClickListener(this);
        mGui.getAddButton().setOnClickListener(mClickListener);

        mGui.getToolbar().setNavigationOnClickListener(mClickListener);
        mGui.getToolbar().setOnMenuItemClickListener(new MenuItemClickListener(this));
    }

    void onActivityReturned(int requestCode, int resultCode, Intent data) { }

    //Return TagList to NoteActivity
    void onBackPressed() {
        returnToNoteActivity();
    }

    void returnToNoteActivity() {
        Intent resultIntent = new Intent();
        for(int i = 0; i<mTagList.size();i++){
            if(mTagList.get(i).equals("")){
                mTagList.remove(i--);
            }
        }
        resultIntent.putExtra("taglist", mTagList);
        mActivity.setResult(Activity.RESULT_OK, resultIntent);
        mGui.hideKeyboard(mActivity);
        mActivity.finish();
    }

    //Remove String from TagList and notify adapter, add new empty string if it was the last one
    void onDeleteButtonClicked(int id){
        mTagList.remove(id);
        mAdapter.notifyDataSetChanged();
        mGui.hideKeyboard(mActivity);
    }

    //Return number of Strings in TagList
    int getListViewItemCount(){
        return mGui.getListViewItemCount();
    }

    //Insert user input into TagList
    void onTextChanged(String s, View mView) {
        mTagList.set(mView.getId(),s);
    }

    private void addInputTagField() {
        mTagList.add("");
        mAdapter.notifyDataSetChanged();
    }

    void OnAddButtonClicked() {
        addInputTagField();
    }

    void onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.note_tagOverview_action_delete) {
            mTagList.clear();
            returnToNoteActivity();
        }
    }
}
