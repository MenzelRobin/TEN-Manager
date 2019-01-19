package com.example.robin.angrynerds_wip.activities.note.note.data.gui_oriented;

import android.graphics.Bitmap;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.robin.angrynerds_wip.activities.note.note.NoteActivity;
import com.example.robin.angrynerds_wip.activities.note.note.data.NoteConstants;
import com.example.robin.angrynerds_wip.data.models.utils.Image;

public class ImageContainer extends IContainer {

    private ImageView mImageView;
    private Image image;
    private PreviewImageCreator previewImageCreator;

    public ImageContainer(NoteActivity activity, int id, Image originalImage) {
        super(activity, id);
        this.image = originalImage;
        previewImageCreator = new PreviewImageCreator();

        Bitmap originalBitmap = originalImage.getBitmap();
        Bitmap scaledBitmap;

        scaledBitmap = previewImageCreator.getPreviewImage(originalBitmap);

        initiateView(scaledBitmap);
        activity.registerForContextMenu(mImageContainer);
    }

    //Initiates Image in ImageView
    private void initiateView(Bitmap scaledImage) {
        mImageContainer.setLayoutParams(new LinearLayout.LayoutParams(NoteConstants.IMAGE_PREVIEW_CONTAINER_WIDTH, NoteConstants.IMAGE_PREVIEW_CONTAINER_HEIGHT));
        mImageContainer.setGravity(Gravity.CENTER);

        ImageView imageView = new ImageView(mActivity.getApplicationContext());
        imageView.setLayoutParams(new LinearLayout.LayoutParams(NoteConstants.IMAGE_PREVIEW_CONTAINER_WIDTH - 50, NoteConstants.IMAGE_PREVIEW_CONTAINER_HEIGHT - 50));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mImageView = imageView;
        imageView.setImageBitmap(scaledImage);

        mImageContainer.addView(imageView);
    }

    public ImageView getImageView() {
        return this.mImageView;
    }
}
