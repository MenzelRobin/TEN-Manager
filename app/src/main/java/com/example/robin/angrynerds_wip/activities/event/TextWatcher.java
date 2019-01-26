package com.example.robin.angrynerds_wip.activities.event;

import android.text.Editable;
import android.view.View;

public class TextWatcher implements android.text.TextWatcher {
    private EventApplicationLogic mEventApplicationLogic;
    private View mView;

    public TextWatcher(EventApplicationLogic pEventApplicationLogic, View view){
        mEventApplicationLogic = pEventApplicationLogic;
        mView = view;
    }

    @Override
    public void onTextChanged(CharSequence c, int start, int count, int after){
        mEventApplicationLogic.onTextChanged(c.toString(), mView);
    }

    @Override
    public void afterTextChanged(Editable s){
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after){

    }
}