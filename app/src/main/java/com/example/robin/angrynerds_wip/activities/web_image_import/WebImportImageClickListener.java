package com.example.robin.angrynerds_wip.activities.web_image_import;

import android.view.View;

import com.example.robin.angrynerds_wip.R;

// Authored by Jan Beilfuß
public class WebImportImageClickListener implements View.OnClickListener  {

    private WebImportLogic mWebApplicationLogic;

    public WebImportImageClickListener(WebImportLogic pWebApplicationLogic) {
        mWebApplicationLogic = pWebApplicationLogic;
    }

    @Override
    public void onClick(View pView) {
        mWebApplicationLogic.loadImage(pView.getId());
    }
}
