package com.example.robin.angrynerds_wip.activities.event;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.example.robin.angrynerds_wip.R;

public class Gui {

    private View mView;
    private SeekBar mRedSeekBar;
    private SeekBar mGreenSeekBar;
    private SeekBar mBlueSeekBar;
    private Button mOkButton;

    public Gui(Init activity) {
        /*
        activity.setContentView(R.layout.selectrgbcolor);

        mView = activity.findViewById(R.id.idView);
        mRedSeekBar = activity.findViewById(R.id.idRedSeekBar);
        mGreenSeekBar = activity.findViewById(R.id.idGreenSeekBar);
        mBlueSeekBar = activity.findViewById(R.id.idBlueSeekBar);
        mOkButton = activity.findViewById(R.id.idOkButton);
        */
    }


    // getter to access views

    public View getView() {
        return mView;
    }

    public SeekBar getRedSeekBar() {
        return mRedSeekBar;
    }

    public SeekBar getGreenSeekBar() {
        return mGreenSeekBar;
    }

    public SeekBar getBlueSeekBar() {
        return mBlueSeekBar;
    }

    public Button getOkButton() {
        return mOkButton;
    }


    // methods to change view attributes

    public void setColorInView(int color) {
        mView.setBackground(new ColorDrawable(color));
    }

    public void setRedSeekBarProgress(int progress) {
        mRedSeekBar.setProgress(progress);
    }

    public void setGreenSeekBarProgress(int progress) {
        mGreenSeekBar.setProgress(progress);
    }

    public void setBlueSeekBarProgress(int progress) {
        mBlueSeekBar.setProgress(progress);
    }

}
