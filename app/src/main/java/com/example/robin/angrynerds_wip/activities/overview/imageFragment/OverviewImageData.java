package com.example.robin.angrynerds_wip.activities.overview.imageFragment;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.robin.angrynerds_wip.data.models.utils.BundleKeys;
import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.activities.overview.superClasses.OverviewFragmentData;

import static com.example.robin.angrynerds_wip.data.services.ImageService.getPreviewImage;

public class OverviewImageData extends OverviewFragmentData {
    /* Yannick-Luca Rüttgers
    Contains the Data for the Image Fragment. Loads the Previewimage.
     */

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
