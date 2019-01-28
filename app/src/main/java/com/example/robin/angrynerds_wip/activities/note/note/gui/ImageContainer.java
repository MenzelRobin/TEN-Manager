package com.example.robin.angrynerds_wip.activities.note.note.gui;

import android.graphics.Bitmap;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.robin.angrynerds_wip.activities.note.note.NoteActivity;
import com.example.robin.angrynerds_wip.activities.note.note.data.NoteConstants;
import com.example.robin.angrynerds_wip.activities.note.note.data.gui_oriented.PreviewImageCreator;
import com.example.robin.angrynerds_wip.data.models.utils.Image;

public class ImageContainer extends GraphicsContainer {

    private ImageView mImageView;
    private PreviewImageCreator mPreviewImageCreator;

    public ImageContainer(NoteActivity pActivity, int pId, Image pPreviewImage) {
        super(pActivity, pId);
        mPreviewImageCreator = new PreviewImageCreator();

        initiateView(pPreviewImage.getBitmap());
        pActivity.registerForContextMenu(mImageContainer);
    }

    //Initiates Image in ImageView
    private void initiateView(Bitmap pScaledImage) {
        mImageContainer.setLayoutParams(new LinearLayout.LayoutParams(NoteConstants.IMAGE_PREVIEW_CONTAINER_WIDTH,
                NoteConstants.IMAGE_PREVIEW_CONTAINER_HEIGHT));
        mImageContainer.setGravity(Gravity.CENTER);

        ImageView imageView = new ImageView(mActivity.getApplicationContext());
        imageView.setLayoutParams(new LinearLayout.LayoutParams(NoteConstants.IMAGE_PREVIEW_CONTAINER_WIDTH - 50,
                NoteConstants.IMAGE_PREVIEW_CONTAINER_HEIGHT - 50));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mImageView = imageView;
        imageView.setImageBitmap(pScaledImage);

        mImageContainer.addView(imageView);
    }

    public ImageView getImageView() {
        return this.mImageView;
    }
}