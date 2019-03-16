package com.example.robin.angrynerds_wip.activities.web_image_import;

import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.robin.angrynerds_wip.R;

import java.util.ArrayList;

public class WebImportGui {

    private Toolbar mToolbar;
    private AppCompatEditText mSearchtermEditText;
    private AppCompatButton mSearchTriggerButton;
    private ArrayList<String> mImageUrls1 = new ArrayList<>();
    private WebImportActivity mWebImportActivity;

    public WebImportGui(WebImportActivity pWebImportActivity) {
        this.mWebImportActivity = pWebImportActivity;
        pWebImportActivity.setContentView(R.layout.activity_web_image_import);
        this.mSearchtermEditText = pWebImportActivity.findViewById(R.id.id_web_import_search_term);
        this.mSearchTriggerButton = pWebImportActivity.findViewById(R.id.id_web_import_search_button);
        initiateToolbar(pWebImportActivity);
        initBitmaps();
        initRecyclerView(pWebImportActivity);
    }

    public void initRecyclerView(WebImportActivity pWebImportActivity) {
        RecyclerView recyclerView = pWebImportActivity.findViewById(R.id.id_web_import_recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mImageUrls1, pWebImportActivity.getApplicationContext(), this.mWebImportActivity);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(pWebImportActivity.getApplicationContext()));
    }

    private void initiateToolbar(WebImportActivity pActivity) {
        mToolbar = pActivity.findViewById(R.id.id_web_import_toolbar);
        pActivity.setSupportActionBar(mToolbar);
        pActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        pActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initBitmaps() {
        //  mImageUrls1.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");

        //  mImageUrls1.add("https://i.redd.it/tpsnoz5bzo501.jpg");

        //  mImageUrls2.add("https://i.redd.it/qn7f9oqu7o501.jpg");

        //  mImageUrls2.add("https://i.redd.it/j6myfqglup501.jpg");

        //  mImageUrls3.add("https://i.redd.it/0h2gm1ix6p501.jpg");

        //  mImageUrls3.add("https://i.redd.it/k98uzl68eh501.jpg");

        //  mImageUrls4.add("https://i.redd.it/glin0nwndo501.jpg");

        //  mImageUrls4.add("https://i.redd.it/obx4zydshg601.jpg");
    }

    public void setSearchTerm(String pSearchterm) {
        this.mSearchtermEditText.setText(pSearchterm);
    }

    public AppCompatEditText getSearchTermBar() {
        return mSearchtermEditText;
    }

    public AppCompatButton getSearchTriggerButton(){
        return mSearchTriggerButton;
    }

    public void addUrl(String thumbnailUrl) {
        mImageUrls1.add(thumbnailUrl);
    }

    public void resetURLs() {
        mImageUrls1 = new ArrayList<String>();
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public void setTopBarColor(int color) {
        this.mToolbar.setBackgroundColor(color);
    }
}
