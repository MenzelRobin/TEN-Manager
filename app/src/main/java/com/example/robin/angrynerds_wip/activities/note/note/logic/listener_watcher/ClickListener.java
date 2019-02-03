package com.example.robin.angrynerds_wip.activities.note.note.logic.listener_watcher;

import android.view.View;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.note.note.logic.NoteApplicationLogic;

public class ClickListener implements View.OnClickListener  {

    private NoteApplicationLogic mNoteApplicationLogic;

    public ClickListener(NoteApplicationLogic pNoteApplicationLogic) {
        mNoteApplicationLogic = pNoteApplicationLogic;
    }

    @Override
    public void onClick(View pView) {

        switch ( pView.getId() ) {
            case R.id.id_note_tags:
                mNoteApplicationLogic.getNoteClickHandler().onTagsClicked();
                return;
            case -1:
                mNoteApplicationLogic.saveAndReturnToOverview();
                return;
            default:
                break;
        }

        //Checks if image was clicked
        for(int id = 0; id<= mNoteApplicationLogic.getImageCount(); id++){
            if(pView.getId() == id){
                mNoteApplicationLogic.getNoteClickHandler().onImageClicked(id);
                return;
            }
        }
    }
}
