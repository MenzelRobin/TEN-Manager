package com.example.robin.angrynerds_wip.activities.note.tageditor;

import android.widget.EditText;
import android.widget.ListView;

class EditTextEndSelector implements Runnable {

    private ListView mListView;
    private EditText mEditText;

    EditTextEndSelector(ListView listView, EditText editText){
        mListView = listView;
        mEditText = editText;
    }

    @Override
    public void run() {
        // Select the focused edit text
        mListView.clearFocus();
        mEditText.setSelection(mEditText.getText().length());
    }
}
