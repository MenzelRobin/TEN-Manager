package com.example.robin.angrynerds_wip.activities.web_image_import;

public class WebImportLogic {
    private WebImportData mWebImportData;
    private WebImportGui mWebImportGui;


    public WebImportLogic(WebImportData pWebImportData, WebImportGui pWebImportGui) {
        this.mWebImportData = pWebImportData;
        this.mWebImportGui = pWebImportGui;
        dataToGui();
    }

    private void dataToGui(){
        this.mWebImportGui.setSearchTerm(this.mWebImportData.getSearchterm());
    }
}
