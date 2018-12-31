package com.example.robin.angrynerds_wip.activities.note;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.data.models.tens.Note;

import java.util.ArrayList;

class NoteData {

    private Init mActivity;
    private Note mNote;
    private ArrayList<IContainer> mNoteImageContainers;

    NoteData(Init activity){
        mActivity = activity;
        mNoteImageContainers = new ArrayList<>();

        mNoteImageContainers.add(new ImageContainer(mActivity,1, BitmapFactory.decodeResource(mActivity.getResources(), R.drawable.note_sample_image1)));
        mNoteImageContainers.add(new ImageContainer(mActivity,2, BitmapFactory.decodeResource(mActivity.getResources(), R.drawable.note_sample_image2)));

        Drawable drawable = ContextCompat.getDrawable(mActivity,R.drawable.ic_add_a_photo_grey_24dp);
        mNoteImageContainers.add(new IconContainer(mActivity, 0, drawable));
    }

    ArrayList<IContainer> getmNoteImageContainers() { return mNoteImageContainers; }
    IContainer getImageContainer(int id){ return mNoteImageContainers.get(id);}
    Init getActivity(){ return mActivity;}
    Note getmNote(){return mNote;}

    Bitmap getImage(int id) {
        //TODO get original sized image from database
        if(id == 1)
            return BitmapFactory.decodeResource(mActivity.getResources(), R.drawable.note_sample_image1);
        else if(id == 2)
            return BitmapFactory.decodeResource(mActivity.getResources(), R.drawable.note_sample_image2);
        return mNoteImageContainers.get(id).getImage();
    }

    boolean checkImageID(int id){
        for(IContainer mImage : mNoteImageContainers){
            if(mImage.getImageContainer().getId()==id)
                return true;
        }
        return false;
    }

    void importMedia(){
        MediaImport mediaImport = new MediaImport(mActivity);
    }

    void copyImage(Uri selectedImage) {
        //copy image to path with uri
        String path = "";
        ImageContainer imageContainer = new ImageContainer(mActivity, mNoteImageContainers.size(), path);
        mNoteImageContainers.add(mNoteImageContainers.size()-2, imageContainer);
    }

    void saveImage(Bitmap image) {
        ImageContainer imageContainer = new ImageContainer(mActivity, mNoteImageContainers.size(), image);
        mNoteImageContainers.add(mNoteImageContainers.size()-1, imageContainer);
    }

   void deleteImage(int id) {
        mNoteImageContainers.remove(id-1);
        for(int i = 1; i<mNoteImageContainers.size(); i++){
            mNoteImageContainers.get(i-1).setImageContainerId(i++);
        }
    }
}
