package com.example.robin.angrynerds_wip.activities.note.note;

import android.text.Editable;
import android.view.View;

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
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after){

    }
}
