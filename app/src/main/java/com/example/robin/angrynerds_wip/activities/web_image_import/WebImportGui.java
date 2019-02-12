package com.example.robin.angrynerds_wip.activities.web_image_import;

import android.graphics.BitmapFactory;
import android.support.v7.widget.AppCompatEditText;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;

import com.example.robin.angrynerds_wip.R;

public class WebImportGui {

    private Toolbar mToolbar;
    private AppCompatEditText mSearchtermEditText;


    public WebImportGui(WebImportActivity pWebImportActivity) {
        pWebImportActivity.setContentView(R.layout.activity_web_image_import);
        this.mSearchtermEditText = pWebImportActivity.findViewById(R.id.id_web_import_search_term);

        ImageView imageView = pWebImportActivity.findViewById(R.id.webDefaultImage);
        imageView.setImageBitmap(BitmapFactory.decodeResource(pWebImportActivity.getResources(), R.drawable.yelowbird_scaled_transparent));
        initiateToolbar(pWebImportActivity);
    }

    private void initiateToolbar(WebImportActivity pActivity) {
        mToolbar = pActivity.findViewById(R.id.id_web_import_toolbar);
        pActivity.setSupportActionBar(mToolbar);
        pActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        pActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public void setSearchTerm(String pSearchterm){
        this.mSearchtermEditText.setText(pSearchterm);
    }
}
