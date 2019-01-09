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
        mGui = gui;
        mActivity = activity;
        mAdapter = new RowViewAdapter(activity,mTagList, this);
        mColor = color;
        initGui();
        initListener();
    }
    //Getter
    public RowViewAdapter getmAdapter() {
        return mAdapter;
    }
    public ClickListener getClickListener() {
        return clickListener;
    }
    //Setter
    public void setmAdapter(RowViewAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }
    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    private void initGui(){
        mGui.getmBackground().setBackgroundColor(mColor);
        mGui.initiateListView(mAdapter);
    }

    private void initListener() {
        clickListener = new ClickListener(this);

        mGui.getmAddButton().setOnClickListener(clickListener);
    }

    void onActivityReturned(int requestCode, int resultCode, Intent data) { }

    void onBackPressed() {
        Intent resultIntent = new Intent();
        // TODO Add extras or a data URI to this intent as appropriate.
        for(int i = 0; i<mTagList.size();i++){
            if(mTagList.get(i).equals("")){
                mTagList.remove(i--);
            }
        }
        resultIntent.putExtra("taglist", mTagList);
        mActivity.setResult(Activity.RESULT_OK, resultIntent);
        mActivity.finish();
    }


    void onAddButtonClicked() {
        mTagList.add("");
        mAdapter.notifyDataSetChanged();
        mGui.getmListView().post(new ListViewBottomSelector(mGui.getmListView(), mAdapter));
    }

    void onDeleteButtonClicked(int id){
        mTagList.remove(id);
        mAdapter.notifyDataSetChanged();
    }

    int getListViewItemCount(){
        return mGui.getListViewItemCount();
    }

    void onTextChanged(String s, View mView) {
        mTagList.set(mView.getId(),s);
    }
}
