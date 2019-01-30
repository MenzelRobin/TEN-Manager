package com.example.robin.angrynerds_wip.activities.event;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.robin.angrynerds_wip.R;

import java.util.Date;

public class Gui {

    private EditText mEditTextTitle;
    private RelativeLayout mRelativeLayoutMain;
    private EditText mEditTextDate;
    private EditText mEditTextTime;
    private EditText mEditTextLocation;
    private ImageView mIconCloseReminder1;
    private ImageView mIconCloseReminder2;
    private ImageView mIconCloseReminder3;
    private ImageView mIconCloseReminder4;
    private EditText mEditTextNewReminder;
    private EditText mEditTextReminder1;
    private EditText mEditTextReminder2;
    private EditText mEditTextReminder3;
    private EditText mEditTextReminder4;
    private Toolbar mToolbar;
    private View mSeperateView1;
    private View mSeperateView2;
    private View mSeperateView3;

    public Gui(Init activity) {
        activity.setContentView(R.layout.activity_event);
        mToolbar = activity.findViewById(R.id.id_event_toolbar);
        mRelativeLayoutMain = activity.findViewById(R.id.id_relativeLayout_main);
        mEditTextTitle = activity.findViewById(R.id.id_event_editText_title);
        mEditTextDate = activity.findViewById(R.id.id_event_editText_date);
        mEditTextTime = activity.findViewById(R.id.id_event_editText_time);
        mEditTextLocation = activity.findViewById(R.id.id_event_editText_location);
        mEditTextNewReminder = activity.findViewById(R.id.id_event_editText_newReminder);
        mIconCloseReminder1 = activity.findViewById(R.id.id_event_imageView_iconCloseReminder1);
        mIconCloseReminder2 = activity.findViewById(R.id.id_event_imageView_iconCloseReminder2);
        mIconCloseReminder3 = activity.findViewById(R.id.id_event_imageView_iconCloseReminder3);
        mIconCloseReminder4 = activity.findViewById(R.id.id_event_imageView_iconCloseReminder4);
        mEditTextReminder1 = activity.findViewById(R.id.id_event_editText_reminder1);
        mEditTextReminder2 = activity.findViewById(R.id.id_event_editText_reminder2);
        mEditTextReminder3 = activity.findViewById(R.id.id_event_editText_reminder3);
        mEditTextReminder4 = activity.findViewById(R.id.id_event_editText_reminder4);
        mSeperateView1 = activity.findViewById(R.id.id_event_view_seperate1);
        mSeperateView2 = activity.findViewById(R.id.id_event_view_seperate2);
        mSeperateView3 = activity.findViewById(R.id.id_event_view_seperate3);
    }

    // getter to access views
    public Toolbar getToolbar() {return mToolbar;}
    public EditText getEditTextTitle() {return mEditTextTitle;}
    public EditText getEditTextDate() {return mEditTextDate;}
    public EditText getEditTextTime() {return mEditTextTime;}
    public EditText getEditTextLocation() {return mEditTextLocation;}
    public EditText getEditTextNewReminder() {return mEditTextNewReminder;}
    public EditText getEditTextReminder1() {return mEditTextReminder1;}
    public EditText getEditTextReminder2() {return mEditTextReminder2;}
    public EditText getEditTextReminder3() {return mEditTextReminder3;}
    public EditText getEditTextReminder4() {return mEditTextReminder4;}
    public ImageView getIconCloseReminder1() {return mIconCloseReminder1;}
    public ImageView getIconCloseReminder2() {return mIconCloseReminder2;}
    public ImageView getIconCloseReminder3() {return mIconCloseReminder3;}
    public ImageView getIconCloseReminder4() {return mIconCloseReminder4;}

    // methods to change view attributes
    public void setTitle(String text) {mEditTextTitle.setText(text);}
    public void setDate(String text) {mEditTextDate.setText(text);}
    public void setTime(String text) {mEditTextTime.setText(text);}
    public void setLocation(String text) {mEditTextLocation.setText(text);}
    public void setColor(int color, int darkColor) {
        mRelativeLayoutMain.setBackground(new ColorDrawable(color));
        mToolbar.setBackground(new ColorDrawable(darkColor));
        mSeperateView1.setBackground(new ColorDrawable(darkColor));
        mSeperateView2.setBackground(new ColorDrawable(darkColor));
        mSeperateView3.setBackground(new ColorDrawable(darkColor));
        mSeperateView1.setAlpha((float) 0.5);
        mSeperateView2.setAlpha((float) 0.5);
        mSeperateView3.setAlpha((float) 0.5);
    }

    public void setReminder(Reminder pReminder, Date eventDate) {
        //Reset reminder
        mEditTextReminder1.setText(""); mEditTextReminder2.setText(""); mEditTextReminder3.setText(""); mEditTextReminder4.setText("");
        mIconCloseReminder1.setAlpha((float) 0.0);mIconCloseReminder2.setAlpha((float) 0.0);mIconCloseReminder3.setAlpha((float) 0.0);mIconCloseReminder4.setAlpha((float) 0.0);

        //Set reminder
        for (int i = 0; i < pReminder.getReminder().size(); i++) {
            String label = pReminder.getLabelFromReminder(i, eventDate);
            pReminder.removeReminderLable(label);
            switch (i) {
                case 0: setReminderText(mEditTextReminder1, mIconCloseReminder1, label);
                    break;
                case 1: setReminderText(mEditTextReminder2, mIconCloseReminder2, label);
                    break;
                case 2: setReminderText(mEditTextReminder3, mIconCloseReminder3, label);
                    break;
                case 3: setReminderText(mEditTextReminder4, mIconCloseReminder4, label);
                    break;
            }
        }
    }

    private void setReminderText(EditText mEditTextReminder, ImageView mIconCloseReminder, String label) {
        mEditTextReminder.setText(label);
        mIconCloseReminder.setAlpha((float) 0.5);
    }
}
