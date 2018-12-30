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
            /*case R.id.id_note_linearImageContainer:
                mApplicationLogic.onImageClicked();
                break;*/
            case R.id.id_note_tags:
                //mApplicationLogic.onTagsClicked();
                break;
            case R.id.id_note_title:
                mApplicationLogic.onTitleClicked();
                break;
            default:
                break;
        }

        for(int id = 0; id<mApplicationLogic.getImageCount()-1; id++){
            if(view.getId() == id)
                mApplicationLogic.onImageClicked(id);
        }

        if(view.getId() == (int)100)
            mApplicationLogic.onImageClicked(100);
    }
}
