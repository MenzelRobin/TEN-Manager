package com.example.robin.angrynerds_wip.activities.web_image_import;

import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;

import com.example.robin.angrynerds_wip.R;

public class WebImportGui {

    private Toolbar mToolbar;


    public WebImportGui(WebImportActivity pWebImportActivity){

        ImageView imageView = pWebImportActivity.findViewById(R.id.webDefaultImage);
        imageView.setImageBitmap(BitmapFactory.decodeResource(pWebImportActivity.getResources(), R.drawable.yelowbird_scaled_transparent));
        initiateToolbar(pWebImportActivity);
    }

    private void initiateToolbar(WebImportActivity pActivity){
        mToolbar = pActivity.findViewById(R.id.id_web_import_toolbar);
        pActivity.setSupportActionBar(mToolbar);
        pActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        pActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}
