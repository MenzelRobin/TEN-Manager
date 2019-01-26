package com.example.robin.angrynerds_wip.overview.imageFragment;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.robin.angrynerds_wip.data.models.utils.BundleKeys;
import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.overview.superClasses.OverviewFragmentData;

import static com.example.robin.angrynerds_wip.data.services.ImageService.getPreviewImage;

public class OverviewImageData extends OverviewFragmentData {

    Bitmap mPreviewImage;

    // Adds the Note data to this Object
    public void addData(Bundle pData){
        super.addData(pData);
        mPreviewImage = getPreviewImage(new Image(pData.getString(BundleKeys.keyNotePictures))).getBitmap();
    }

    // Returns the Image
    public Bitmap getImage(){
        return mPreviewImage;
    }
}
