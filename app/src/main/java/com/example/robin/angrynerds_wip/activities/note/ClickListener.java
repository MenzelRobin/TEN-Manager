package com.example.robin.angrynerds_wip.activities.note;

import android.view.View;

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
