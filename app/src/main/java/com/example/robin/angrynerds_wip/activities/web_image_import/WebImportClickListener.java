package com.example.robin.angrynerds_wip.activities.web_image_import;

import android.util.Log;
import android.view.View;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.note.note.logic.NoteApplicationLogic;

// Authored by Joscha Nassenstein
public class WebImportClickListener implements View.OnClickListener  {

    private WebImportLogic mWebApplicationLogic;

    public WebImportClickListener(WebImportLogic pWebApplicationLogic) {
        mWebApplicationLogic = pWebApplicationLogic;
    }

    @Override
    public void onClick(View pView) {


        switch ( pView.getId() ) {
            case R.id.id_web_import_search_button:
                mWebApplicationLogic.searchImages();
                break;
            case -1:
                mWebApplicationLogic.closeActivity();
                break;
            default:
                break;
        }

    }
}
