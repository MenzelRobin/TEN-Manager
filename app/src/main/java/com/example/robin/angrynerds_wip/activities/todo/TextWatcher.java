package com.example.robin.angrynerds_wip.activities.todo;

import android.text.Editable;
import android.view.View;
import android.widget.EditText;

public class TextWatcher implements android.text.TextWatcher {
    private TodoApplicationLogic mTodoApplicationLogic;
    private View mView;

    TextWatcher(TodoApplicationLogic applicationLogic, View view){
        mTodoApplicationLogic = applicationLogic;
        mView = view;
    }

    @Override
    public void onTextChanged(CharSequence c, int start, int count, int after){
        mTodoApplicationLogic.onTextChanged(c.toString(), mView);
    }


    @Override
    public void afterTextChanged(Editable s){

        //String result = s.toString().replaceAll(" ", "");
        String result = s.toString();
        if (!s.toString().equals(result)) {
            ((EditText)mView).setText(result);
            ((EditText)mView).setSelection(result.length());
        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after){

    }
}
