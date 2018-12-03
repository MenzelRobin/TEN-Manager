package com.example.robin.angrynerds_wip.activities.event;

import android.view.View;

import com.example.robin.angrynerds_wip.R;

public class ClickListener implements View.OnClickListener  {

    ApplicationLogic mApplicationLogic;

    public ClickListener(ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
    }

    @Override
    public void onClick(View view) {
        /*
        switch ( view.getId() ) {
            case R.id.idOkButton:
                mApplicationLogic.onOkButtonClicked();
                break;
            default:
                break;
        }
        */
    }

}
