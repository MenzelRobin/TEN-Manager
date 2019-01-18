package com.example.robin.angrynerds_wip.activities.note.tageditor;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

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

    //Remove String from TagList and notify adapter, add new empty string if it was the last one
    void onDeleteButtonClicked(int id){

        /*if(mTagList.get(id).equals("") && mTagList.size()-2 == id)
            mGui.displayToast(mActivity, "Die letzte Zeile kann nicht gelöscht werden");
        else if (mTagList.size()-2 == id){
            mTagList.remove(id);
            mTagList.add("");
            mAdapter.notifyDataSetChanged();
        }
        else{
            mTagList.remove(id);
            mAdapter.notifyDataSetChanged();
        }*/
        mTagList.remove(id);
        mAdapter.notifyDataSetChanged();
        mGui.hideKeyboard(mActivity);
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
