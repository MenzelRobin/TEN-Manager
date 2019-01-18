package com.example.robin.angrynerds_wip.activities.note.note.logic.event_handler;

import android.view.View;
import android.widget.Toast;

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
                return;
            case R.id.id_note_title:
                mNoteApplicationLogic.onTitleClicked();
                return;
            case -1:
                mNoteApplicationLogic.saveAndReturnToOverview();
                return;
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
