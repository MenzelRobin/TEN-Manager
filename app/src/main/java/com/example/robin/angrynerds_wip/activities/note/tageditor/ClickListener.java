package com.example.robin.angrynerds_wip.activities.note.tageditor;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.robin.angrynerds_wip.R;

class ClickListener implements View.OnClickListener  {

    private ApplicationLogic mApplicationLogic;

    ClickListener(ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        if(id == R.id.id_note_tagOverview_addButton)
            mApplicationLogic.OnAddButtonClicked();
        else if(id == -1)
            mApplicationLogic.returnToNoteActivity();
        else {
            for (int tagId = 0; tagId < mApplicationLogic.getListViewItemCount(); tagId++) {
                if (id == tagId) {
                    mApplicationLogic.onDeleteButtonClicked(id);
                }
            }
        }
    }
}
