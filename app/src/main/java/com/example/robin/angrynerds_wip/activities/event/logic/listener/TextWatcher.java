package com.example.robin.angrynerds_wip.activities.event.logic.listener;

import android.text.Editable;
import android.view.View;

import com.example.robin.angrynerds_wip.activities.event.logic.EventApplicationLogic;

public class TextWatcher implements android.text.TextWatcher {
    /* Robin Menzel
     handels changes on any EditText Object
     */

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
