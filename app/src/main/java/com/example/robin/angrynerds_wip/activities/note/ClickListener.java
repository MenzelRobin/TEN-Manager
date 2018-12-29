package com.example.robin.angrynerds_wip.activities.note;

import android.view.View;

import com.example.robin.angrynerds_wip.R;

public class ClickListener implements View.OnClickListener  {

    ApplicationLogic mApplicationLogic;

    public ClickListener(ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
    }

    @Override
    public void onClick(View view) {

        switch ( view.getId() ) {
            case R.id.id_note_linearImageContainer:
                //mApplicationLogic.onImageClicked();
                break;
            case R.id.id_note_tags:
                //mApplicationLogic.onTagsClicked();
                break;
            default:
                break;
        }

    }

}
