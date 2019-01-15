package com.example.robin.angrynerds_wip.activities.note.note.logic.event_handler;

import android.view.View;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.note.note.logic.NoteApplicationLogic;

public class ClickListener implements View.OnClickListener  {

    private NoteApplicationLogic mNoteApplicationLogic;

    public ClickListener(NoteApplicationLogic noteApplicationLogic) {
        mNoteApplicationLogic = noteApplicationLogic;
    }

    @Override
    public void onClick(View view) {
        
        switch ( view.getId() ) {
            case R.id.id_note_tags:
                mNoteApplicationLogic.onTagsClicked();
                break;
            case R.id.id_note_title:
                mNoteApplicationLogic.onTitleClicked();
                break;
            case -1:
                mNoteApplicationLogic.returnToOverview();
                break;
            default:
                break;
        }

        //Checks if image was clicked
        for(int id = 0; id<= mNoteApplicationLogic.getImageCount(); id++){
            if(view.getId() == id){
                mNoteApplicationLogic.onImageClicked(id);
                return;
            }
        }
    }
}
