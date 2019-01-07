package com.example.robin.angrynerds_wip.activities.note.tageditor;

import android.widget.ListView;

class ListViewBottomSelector implements Runnable {

    private ListView mListView;
    private RowViewAdapter mAdapter;

    ListViewBottomSelector(ListView listView, RowViewAdapter rowViewAdapter){
        mListView = listView;
        mAdapter = rowViewAdapter;
    }

    @Override
    public void run() {
        // Select the last row to make it visible
        mListView.setSelection(mAdapter.getCount() - 1);
    }
}
