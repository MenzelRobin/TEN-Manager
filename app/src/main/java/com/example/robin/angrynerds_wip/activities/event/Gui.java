package com.example.robin.angrynerds_wip.activities.event;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.example.robin.angrynerds_wip.R;

public class Gui {

    private EditText mEditTextTitle;
    private RelativeLayout mRelativeLayoutBG;
    private EditText mEditTextDate;
    private EditText mEditTextTime;
    private EditText mEditTextLocation;
    private LinearLayout mLinearLayoutReminder;
    private RelativeLayout mRelativeLayoutReminder;
    private EditText mEditTextReminder1;
    private EditText mEditTextReminder2;
    private ImageView mIconCloseReminder1;
    private ImageView mIconCloseReminder2;
    private EditText mEditTextNewReminder;

    public Gui(Init activity) {
        activity.setContentView(R.layout.activity_event);

        mEditTextTitle = activity.findViewById(R.id.editTextTitle);
        mRelativeLayoutBG = activity.findViewById(R.id.relativeLayoutBG);
        mEditTextDate = activity.findViewById(R.id.editTextDate);
        mEditTextTime = activity.findViewById(R.id.editTextTime);
        mEditTextLocation = activity.findViewById(R.id.editTextLocation);
        mLinearLayoutReminder = activity.findViewById(R.id.linearLayoutReminder);
        mRelativeLayoutReminder = activity.findViewById(R.id.relativeLayoutReminder);
        mEditTextReminder1 = activity.findViewById(R.id.editTextReminder1);
        mEditTextReminder2 = activity.findViewById(R.id.editTextReminder2);
        mIconCloseReminder1 = activity.findViewById(R.id.iconCloseReminder1);
        mIconCloseReminder2 = activity.findViewById(R.id.iconCloseReminder2);
        mEditTextNewReminder = activity.findViewById(R.id.editTextNewReminder);
    }

    // getter to access views
    public EditText getmEditTextTitle() {return mEditTextTitle;}
    public RelativeLayout getmRelativeLayoutBG() { return mRelativeLayoutBG; }
    public EditText getmEditTextDate() {return mEditTextDate;}
    public EditText getmEditTextTime() {return mEditTextTime;}
    public EditText getmEditTextLocation() {return mEditTextLocation; }
    public LinearLayout getmLinearLayoutReminder() {return mLinearLayoutReminder; }
    public RelativeLayout getmRelativeLayoutReminder() {return mRelativeLayoutReminder; }
    public EditText getmEditTextReminder1() {return mEditTextReminder1; }
    public ImageView getmIconCloseReminder1() {return mIconCloseReminder1; }
    public EditText getmEditTextReminder2() {return mEditTextReminder2; }
    public ImageView getmIconCloseReminder2() {return mIconCloseReminder2; }
    public EditText getmEditTextNewReminder() {return mEditTextNewReminder; }

    // methods to change view attributes
    public void setColorInBG(int color) { mRelativeLayoutBG.setBackground(new ColorDrawable(color));}
    public void setTitle(String text) { mEditTextTitle.setText(text);}
    public void setDate(String text) {mEditTextTitle.setText(text);}
    public void setTime(String text) {mEditTextTime.setText(text);}
    public void setReminder1(String text) {mEditTextReminder1.setText(text);}
    public void setReminder2(String text) {mEditTextReminder2.setText(text);}
}
