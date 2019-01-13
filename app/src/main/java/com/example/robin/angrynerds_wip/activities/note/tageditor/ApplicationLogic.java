package com.example.robin.angrynerds_wip.activities.note.tageditor;

import android.app.Activity;
import android.content.Intent;
import android.text.method.Touch;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;


class ApplicationLogic {

    private ArrayList<String> mTagList;
    private int mColor;
    private Gui mGui;
    private RowViewAdapter mAdapter;
    private ClickListener mClickListener;
    private TouchListener mTouchListener;
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
        return mClickListener;
    }
    TouchListener getTouchListener() { return mTouchListener; }

    private void initGui(){
        mGui.getBackground().setBackgroundColor(mColor);
        mGui.initiateListView(mAdapter);
    }

    private void initListener() {
        mClickListener = new ClickListener(this);
        mTouchListener = new TouchListener(this);
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

        if(id==mTagList.size()-1)
            mTagList.add("");
        mAdapter.notifyDataSetChanged();
    }

    void onTagTextClicked() {
        addInputTagField();
    }

    //Return number of Strings in TagList
    int getListViewItemCount(){
        return mGui.getListViewItemCount();
    }

    //Insert user input into TagList
    void onTextChanged(String s, View mView) {
        mTagList.set(mView.getId(),s);
        if(mView.getId() == mTagList.size()-1){
            addInputTagField();
        }
    }

    private void addInputTagField() {
        mTagList.add("");
        mAdapter.notifyDataSetChanged();
        mGui.getListView().post(new Runnable() {
            @Override
            public void run() {
                mGui.getListView().setSelection(mAdapter.getCount() - 1);
            }
        });
    }
}
