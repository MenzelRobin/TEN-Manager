package com.example.robin.angrynerds_wip.activities.note.tageditor;

import android.view.View;

import com.example.robin.angrynerds_wip.R;

class ClickListener implements View.OnClickListener  {

    ApplicationLogic mApplicationLogic;

    ClickListener(ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        if(id == R.id.id_note_tagOverview_addButton){
            mApplicationLogic.onAddButtonClicked();
        }
        else{
            for(int tagId = 0; tagId<=mApplicationLogic.getListViewItemCount(); tagId++){
                if(id == tagId){
                    mApplicationLogic.onDeleteButtonClicked(id);
                }
            }
        }
    }
}
