package com.example.robin.angrynerds_wip.modules.image;

import android.graphics.Bitmap;

public class ImageToolsModule {

    ImageRotationCorrectionModule mImageRotationCorrectionModule;
    ImageCompressionModule mImageCompressionModule;

    public ImageToolsModule() {
        this.mImageRotationCorrectionModule = new ImageRotationCorrectionModule();
        this.mImageCompressionModule = new ImageCompressionModule();
    }

    public Bitmap importCompressedImage(String pPath) {
        return this.mImageCompressionModule.importCompressedImage(pPath);
    }

    public Bitmap correctImageRotation(String pPhotoPath, Bitmap pImage) {
        return this.mImageRotationCorrectionModule.correctImageRotation(pPhotoPath, pImage);
    }
}
