package com.example.robin.angrynerds_wip.activities.note.note;

import android.view.View;

import com.example.robin.angrynerds_wip.R;

class ClickListener implements View.OnClickListener  {

    private ApplicationLogic mApplicationLogic;

    ClickListener(ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
    }

    @Override
    public void onClick(View view) {
        
        switch ( view.getId() ) {
            case R.id.id_note_tags:
                mApplicationLogic.onTagsClicked();
                break;
            case R.id.id_note_title:
                mApplicationLogic.onTitleClicked();
                break;
            case -1:
                mApplicationLogic.returnToOverview();
                break;
            default:
                break;
        }

        //Checks if image was clicked
        for(int id = 0; id<=mApplicationLogic.getImageCount(); id++){
            if(view.getId() == id){
                mApplicationLogic.onImageClicked(id);
                return;
            }
        }
    }
}
