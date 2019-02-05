package com.example.robin.angrynerds_wip.activities.note.notetags;

import android.text.Editable;
import android.view.View;
import android.widget.EditText;

// Authored by Joscha Nassenstein
class TextWatcher implements android.text.TextWatcher {
    private ApplicationLogic mApplicationLogic;
    private View mView;

    TextWatcher(ApplicationLogic applicationLogic, View view){
        mApplicationLogic = applicationLogic;
        mView = view;
    }

    @Override
    public void onTextChanged(CharSequence c, int start, int count, int after){
        mApplicationLogic.onTextChanged(c.toString(), mView);
    }

    @Override
    public void afterTextChanged(Editable s){
        String result = s.toString().replaceAll(" ", "");
        if (!s.toString().equals(result)) {
            ((EditText)mView).setText(result);
            ((EditText)mView).setSelection(result.length());
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after){

    }
}
